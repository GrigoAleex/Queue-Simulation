package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.runners;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.CashRegister;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Simulation;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewClients;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesRandomNumber;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SimulationRunner implements Runnable {
    Integer clientsToGenerate;
    final ArrayList<CashRegister> registers;
    final Integer simulationTime;
    final Integer serviceTimeMin;
    final Integer serviceTimeMax;
    final Integer arrivalTimeMin;
    final Integer arrivalTimeMax;

    public SimulationRunner(Integer clientsToGenerate, ArrayList<CashRegister> registers, Integer simulationTime, Integer serviceTimeMin, Integer serviceTimeMax, Integer arrivalTimeMax, Integer arrivalTimeMin) {
        this.clientsToGenerate = clientsToGenerate;
        this.registers = registers;
        this.simulationTime = simulationTime;
        this.serviceTimeMax = serviceTimeMax;
        this.serviceTimeMin = serviceTimeMin;
        this.arrivalTimeMax = arrivalTimeMax;
        this.arrivalTimeMin = arrivalTimeMin;
    }

    @Override
    public void run() {
        for (int i = 0; i <= simulationTime; i++) {
            int time = i;

            int clientsArrivedNumber = getClientsArrivedNumber(time, AppProvider.get(GeneratesRandomNumber.class));
            clientsToGenerate -= clientsArrivedNumber;

            List<Client> waitingClients = Collections.synchronizedList(new ArrayList<>());
            HashMap<Integer, Pair<Integer, Client>> registersLogs = new HashMap<>();

            try {
                CompletableFuture
                    .supplyAsync(() -> AppProvider.get(GeneratesNewClients.class).handle(clientsArrivedNumber, time, serviceTimeMin, serviceTimeMax))
                    .thenAcceptAsync(clients -> {
                        dispatchClients(clients, registers);
                        Simulation.getStatisticManager().addNewClients(clients).computeAverageServiceTime().computeAverageWaitingTime();

                        List<CompletableFuture<Void>> futures = registers.stream()
                            .map(register -> CompletableFuture.runAsync(() -> {
                                register.processClient();
                                registerLog(register, registersLogs, waitingClients);
                            }))
                            .toList();

                        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

                        Simulation.addLog(time, waitingClients, registersLogs);
                        Simulation.setLatestProcessedTime(time);
                    }).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int getClientsArrivedNumber(int time, GeneratesRandomNumber randomNumberGenerator) {
        if (time < arrivalTimeMin) return 0;

        if (time < arrivalTimeMax) return randomNumberGenerator.handle(clientsToGenerate, 0);

        return clientsToGenerate;
    }

    private void dispatchClients(ArrayList<Client> clients, ArrayList<CashRegister> registers) {
        for (Client client : clients) {
            CashRegister selectedRegister = registers.getFirst();
            int minWaitingTime = Integer.MAX_VALUE;

            for (CashRegister register : registers) {
                int waitingTime = register.getWaitingTime();

                if (waitingTime < minWaitingTime) {
                    minWaitingTime = waitingTime;
                    selectedRegister = register;
                } else if (waitingTime == minWaitingTime && register.getQueue().size() < selectedRegister.getQueue().size()) {
                    selectedRegister = register;
                }
            }

            selectedRegister.addClient(client);
        }
    }

    private void registerLog(CashRegister register, HashMap<Integer, Pair<Integer, Client>> registersLogs, List<Client> arrivedClients) {
        int serviceTime;
        if (register.getCurrentClient() != null) serviceTime = register.getCurrentClient().getServiceTime();
        else  serviceTime = -1;

        arrivedClients.addAll(register.getQueue());
        registersLogs.put(register.getId(), new Pair<>(serviceTime, register.getCurrentClient()));
    }
}

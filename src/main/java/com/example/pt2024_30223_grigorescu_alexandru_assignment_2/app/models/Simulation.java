package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.runners.SimulationRunner;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewCashRegisters;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.Logger;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.StatisticsManager;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Simulation {
    private static final Simulation instance = new Simulation();
    private long id;
    private Integer clientsToGenerate;
    private Integer queues;
    private Integer simulationTime;
    private Integer arrivalTimeMin;
    private Integer arrivalTimeMax;
    private Integer serviceTimeMin;
    private Integer serviceTimeMax;
    private Logger logger;
    private StatisticsManager statisticsManager;
    private volatile Integer latestProcessedTime = 0;

    public static void setState(Integer clients, Integer queues, Integer simulationTime, Integer arrivalTimeMin, Integer arrivalTimeMax, Integer serviceTimeMin, Integer serviceTimeMax) {
        instance.id = System.currentTimeMillis();
        instance.logger = new Logger();
        instance.statisticsManager = new StatisticsManager();
        instance.queues = queues;
        instance.clientsToGenerate = clients;
        instance.simulationTime = simulationTime;
        instance.arrivalTimeMin = arrivalTimeMin;
        instance.arrivalTimeMax = arrivalTimeMax;
        instance.serviceTimeMin = serviceTimeMin;
        instance.serviceTimeMax = serviceTimeMax;
        Client.resetId();
    }

    public static void start() {
        GeneratesNewCashRegisters registerGenerator = AppProvider.get(GeneratesNewCashRegisters.class);

        CompletableFuture.supplyAsync(() -> registerGenerator.handle(instance.queues))
            .thenAcceptAsync((ArrayList<CashRegister> registers) -> new SimulationRunner(
                    instance.clientsToGenerate,
                    registers,
                    instance.simulationTime,
                    instance.serviceTimeMin,
                    instance.serviceTimeMax,
                    instance.arrivalTimeMax,
                    instance.arrivalTimeMin
            ).run()
        );
    }

    public static Log getFrame(int time) {
        return instance.logger.getLogFromTime(time);
    }

    public static String getOutputFile() {
        return "simulations/output_" + instance.id + ".txt";
    }

    public static Integer getSimulationTotalTime() {
        return instance.simulationTime;
    }

    public static Integer getLatestProcessedTime() {
        return instance.latestProcessedTime;
    }

    public static synchronized void setLatestProcessedTime(int time) {
        instance.latestProcessedTime = time;
    }

    public static void addLog(int time, List<Client> arrivedClients, HashMap<Integer, Pair<Integer, Client>> registersLogs) {
        instance.logger.addLog(time, arrivedClients, registersLogs);
    }

    public static ArrayList<Log> getLogs() {
        return instance.logger.getLogs();
    }

    public static StatisticsManager getStatisticManager() {
        return instance.statisticsManager;
    }
}

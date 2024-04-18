package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Log;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class StatisticsManager {
    private Double averageWaitingTime = 0.0; // starting_service_time - arrival_time
    private volatile Integer peakHour = 0;
    private volatile Integer peakHourClientsCount = 0;
    private volatile Double averageServiceTime = 0.0;
    private final ArrayList<Client> clients = new ArrayList<>();

    public StatisticsManager () {}

    public synchronized StatisticsManager computePeakHour(Integer time, Log log) {
        int clientsCount = log.waitingClients().size();
        for (Map.Entry<Integer, Pair<Integer, Client>> register: log.registers().entrySet()) {
            clientsCount += register.getValue().getValue() != null ? 1 : 0;
        }

        if (clientsCount > peakHourClientsCount) {
            peakHour = time;
            peakHourClientsCount = clientsCount;
        }

        return this;
    }

    public synchronized StatisticsManager addNewClients(ArrayList<Client> newClients) {
        clients.addAll(newClients);
        return this;
    }

    public synchronized StatisticsManager computeAverageServiceTime() {
        double totalServiceTime = 0;
        for (Client client: clients) totalServiceTime += client.getInitialServiceTime();
        averageServiceTime =  totalServiceTime / clients.size();
        return this;
    }

    public synchronized StatisticsManager computeAverageWaitingTime() {
        double totalWaitingTime = 0;
        for (Client client: clients) totalWaitingTime += client.getWaitingTimeUntilService();
        averageWaitingTime =  totalWaitingTime / clients.size();
        return this;
    }

    public Integer getPeakHour() {
        return peakHour;
    }

    public Double getAverageServiceTime() {
        return averageServiceTime;
    }

    public Double getAverageWaitingTime() {
        return averageWaitingTime;
    }
}

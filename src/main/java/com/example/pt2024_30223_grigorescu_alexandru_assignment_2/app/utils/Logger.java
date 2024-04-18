package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Log;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Simulation;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.lang.Thread.sleep;

public class Logger {
    private final ArrayList<Log> logs = new ArrayList<>();

    public Logger() {}

    @SuppressWarnings("BusyWait")
    public Log getLogFromTime(int time) {
        Optional<Log> log = logs.stream().filter(l -> l.time() == time).findFirst();

        while (log.isEmpty()) {
            try { sleep(300); }
            catch (InterruptedException e) { e.printStackTrace(); }
            log = logs.stream().filter(l -> l.time() == time).findFirst();
        }

        return log.get();
    }

    public void addLog(Integer time, List<Client> waitingClients, HashMap<Integer, Pair<Integer, Client>> registers) {
        Log log = new Log(time, waitingClients, registers);
        logs.add(log);
        new IOManager().setFile(Simulation.getOutputFile()).writeLog(log);
        Simulation.getStatisticManager().computePeakHour(time, log);
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }
}

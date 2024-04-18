package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Log;
import javafx.util.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class IOManager {
    private String file = null;

    public IOManager() {}

    public IOManager setFile(String fileName) {
        file = fileName;
        return this;
    }

    public void writeLog(Log log) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println("Time " + log.time());
            writer.print("Waiting clients: ");
            for (Client client : log.waitingClients()) {
                if (client != null)
                    writer.print("(" + client.getId() + ", " + client.getArrivalTime() + ", " +client.getServiceTime() + "), ");
            }

            writer.println("");
            for (Map.Entry<Integer, Pair<Integer, Client>> entry : log.registers().entrySet()) {
                Client client = entry.getValue().getValue();
                writer.print("Cash register " + entry.getKey() + ": ");
                if (client != null) {
                    writer.println("(" + client.getId() + ", " + client.getArrivalTime() + ", " +client.getServiceTime() + ")");
                } else {
                    writer.println("empty");
                }
            }

            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to " + file);
            e.printStackTrace();
        }
    }
}

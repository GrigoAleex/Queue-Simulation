package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

public record Log(Integer time, List<Client> waitingClients, HashMap<Integer, Pair<Integer, Client>> registers) {

// --Commented out by Inspection START (13.04.2024, 18:36):
//    public void addWaitingClient(Collection<? extends Client> clients) {
//        waitingClients.addAll(clients);
//    }
// --Commented out by Inspection STOP (13.04.2024, 18:36)


// --Commented out by Inspection START (13.04.2024, 18:36):
//    public void addRegister(Integer registerId, Client client) {
//        registers.put(registerId, new Pair<>(client.getServiceTime(), client));
//    }
// --Commented out by Inspection STOP (13.04.2024, 18:36)
}

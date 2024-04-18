package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.actions;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesNewClients;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.GeneratesRandomNumber;

import java.util.ArrayList;

public class GenerateRandomClient implements GeneratesNewClients {
    public ArrayList<Client> handle(Integer count, Integer arrivalTime, Integer serviceTimeMin, Integer serviceTimeMax) {
        ArrayList<Client> clients = new ArrayList<>();
        GeneratesRandomNumber generator = AppProvider.get(GeneratesRandomNumber.class);

        for (int i = 1; i <= count; i++) {
            clients.add(new Client(
                    arrivalTime,
                    generator.handle(serviceTimeMax, serviceTimeMin)
            ));
        }

        return clients;
    }
}

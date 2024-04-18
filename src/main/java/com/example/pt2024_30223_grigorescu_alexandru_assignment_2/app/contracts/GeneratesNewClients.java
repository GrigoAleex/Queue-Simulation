package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;

import java.util.ArrayList;

public interface GeneratesNewClients {
    ArrayList<Client> handle(Integer count, Integer arrivalTime, Integer serviceTimeMin, Integer serviceTimeMax);
}

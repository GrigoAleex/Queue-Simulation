package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.actions.*;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.contracts.*;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers.LoadingController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers.LogController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers.SimulationController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers.StartController;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Route;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;

public class ConfigLoader {
    public static void handle() {
        Router.add(new Route("start", StartController.class, "index"));
        Router.add(new Route("loading", LoadingController.class, "index"));
        Router.add(new Route("simulation", SimulationController.class, "index"));
        Router.add(new Route("logs", LogController.class, "index"));
        Router.add(new Route("simulation/create", SimulationController.class, "store"));

        AppProvider.set(GeneratesRandomNumber.class, new GenerateRandomNumber());
        AppProvider.set(GeneratesNewClients.class, new GenerateRandomClient());
        AppProvider.set(GeneratesNewCashRegisters.class, new GenerateNewCashRegister());
    }
}

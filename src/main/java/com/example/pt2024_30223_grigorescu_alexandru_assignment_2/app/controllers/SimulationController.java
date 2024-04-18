package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Simulation;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.requests.SimulationCreateRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.exceptions.ValidationException;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.LoaderManager;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.exceptions.ViewNotFoundException;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.exceptions.WindowUninitializedException;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Session;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.contracts.IController;

import java.util.concurrent.CompletableFuture;

public class SimulationController implements IController {
    public static void index() {
        try {
            Window.setView("simulation");
        } catch (WindowUninitializedException e) {
            System.err.println(e.getMessage());
            System.exit(11);
        } catch (ViewNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(12);
        }
    }

    public static void store(SimulationCreateRequest request) {
        try {
            validate(request);
        } catch (ValidationException e) {
            Session.set("error", e.getMessage());
            Router.go("start");
            return;
        }

        AppProvider.set(LoaderManager.class, new LoaderManager(request.queues()));

        Simulation.setState(
            request.clients(),
            request.queues(),
            request.simulationTime(),
            request.arrivalTimeMin(),
            request.arrivalTimeMax(),
            request.serviceTimeMin(),
            request.serviceTimeMax()
        );

        CompletableFuture.runAsync(Simulation::start);

        Router.go("loading");
    }

    private static void validate(SimulationCreateRequest request) throws ValidationException {
        if (request.clients() <= 0) throw new ValidationException("Number of clients must be bigger or equal to 1");
        if (request.queues() <= 0) throw new ValidationException("Number of queues must be bigger or equal to 1");
        if (request.simulationTime() <= 0) throw new ValidationException("Simulation time must be bigger or equal to 1");
        if (request.arrivalTimeMin() < 0) throw new ValidationException("Minimum arrival time must be bigger or equal to 0");
        if (request.arrivalTimeMax() <= 0) throw new ValidationException("Maximum arrival time must be bigger or equal to 1");
        if (request.arrivalTimeMin() > request.arrivalTimeMax()) throw new ValidationException("Maximum arrival time must be bigger or equal to minimum arrival time");
        if (request.serviceTimeMin() <= 0) throw new ValidationException("Minimum service time must be bigger or equal to 1");
        if (request.serviceTimeMax() <= 0) throw new ValidationException("Maximum service time must be bigger or equal to 1");
        if (request.serviceTimeMin() > request.serviceTimeMax()) throw new ValidationException("Maximum service time must be bigger or equal to minimum service time");
    }
}

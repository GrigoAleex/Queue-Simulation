package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.requests.SimulationCreateRequest;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class StartController {

    @FXML
    private TextField arrival_time_max;

    @FXML
    private TextField arrival_time_min;

    @FXML
    private TextField clients;

    @FXML
    private TextField queues;

    @FXML
    private TextField service_time_max;

    @FXML
    private TextField service_time_min;

    @FXML
    private TextField simulation_time;

    @FXML
    private Text error;

    @FXML
    void submit(ActionEvent event) {
        Session.set("clients", clients.getText());
        Session.set("queues", queues.getText());
        Session.set("simulationTime", simulation_time.getText());
        Session.set("arrivalTimeMin", arrival_time_min.getText());
        Session.set("arrivalTimeMax", arrival_time_max.getText());
        Session.set("serviceTimeMin", service_time_min.getText());
        Session.set("serviceTimeMax", service_time_max.getText());

        try {
            SimulationCreateRequest request = new SimulationCreateRequest(
                Integer.parseInt(clients.getText()),
                Integer.parseInt(queues.getText()),
                Integer.parseInt(simulation_time.getText()),
                Integer.parseInt(arrival_time_min.getText()),
                Integer.parseInt(arrival_time_max.getText()),
                Integer.parseInt(service_time_min.getText()),
                Integer.parseInt(service_time_max.getText())
            );

            Router.go("simulation/create", request);
        } catch (Exception e) {
            this.error.setText("Error! All fields must be filled with numbers.");
        }
    }

    public void initialize() {
        clients.setText(Session.get("clients"));
        queues.setText(Session.get("queues"));
        simulation_time.setText(Session.get("simulationTime"));
        arrival_time_min.setText(Session.get("arrivalTimeMin"));
        arrival_time_max.setText(Session.get("arrivalTimeMax"));
        service_time_min.setText(Session.get("serviceTimeMin"));
        service_time_max.setText(Session.get("serviceTimeMax"));
        if (Session.get("error") != null) error.setText("Error! " + Session.get("error"));
    }
}

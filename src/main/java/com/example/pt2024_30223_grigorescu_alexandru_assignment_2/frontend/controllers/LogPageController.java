package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Simulation;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class LogPageController {

    @FXML private TextFlow logs;
    @FXML private Text avg_waiting_time;
    @FXML private Text avg_service_time;
    @FXML private Text peak_hour;

    @FXML void submit(ActionEvent ignoredEvent) {
        Router.go("start");
    }

    public void initialize() {
        avg_waiting_time.setText(String.format("%.2f", Simulation.getStatisticManager().getAverageWaitingTime()));
        avg_service_time.setText(String.format("%.2f", Simulation.getStatisticManager().getAverageServiceTime()));
        peak_hour.setText(Simulation.getStatisticManager().getPeakHour().toString());
    }
}

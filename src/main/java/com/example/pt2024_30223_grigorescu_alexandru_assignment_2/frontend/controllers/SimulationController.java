package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Log;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Simulation;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.components.CashRegisterComponent;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationController {
    private final AtomicInteger time = new AtomicInteger(1);
    private Boolean isAutoPlaying = false;

    @FXML private Text timeText;
    @FXML private HBox simulationFrame;
    @FXML private Button nextButton;
    @FXML private Button previousButton;
    @FXML private Button playButton;
    @FXML private Button pauseButton;
    @FXML private ScrollPane scrollPane;

    @FXML void previous(ActionEvent event) {
        renderFrame(time.decrementAndGet());
    }

    @FXML void next(ActionEvent event) {
        renderFrame(time.incrementAndGet());
    }

    Timer autoPlaytimer = new Timer();

    @FXML
    void play(ActionEvent event) {
        isAutoPlaying = true;

        autoPlaytimer = new Timer();

        TimerTask autoPlay = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> renderFrame(time.incrementAndGet()));

                if (time.get() > Simulation.getSimulationTotalTime() || time.get() == Simulation.getLatestProcessedTime()) {
                    autoPlaytimer.cancel();
                }
            }
        };

        autoPlaytimer.scheduleAtFixedRate(autoPlay, 0, 300);
    }

    @FXML
    void pause(ActionEvent ignoredEvent) {
        isAutoPlaying = false;
        autoPlaytimer.cancel();
    }

    public void initialize() {
        simulationFrame.prefWidthProperty().bind(scrollPane.widthProperty());
        renderFrame(time.get());

        new Thread(() -> {
            Timer timer = new Timer();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                Platform.runLater(() -> {
                    if (isAutoPlaying) {
                        pauseButton.setDisable(false);
                        playButton.setDisable(true);
                        nextButton.setDisable(true);
                        previousButton.setDisable(true);
                        return;
                    } else {
                        pauseButton.setDisable(true);
                    }

                    if (time.get() >= Simulation.getLatestProcessedTime() && !nextButton.isDisabled() && !Objects.equals(Simulation.getLatestProcessedTime(), Simulation.getSimulationTotalTime())) {
                        nextButton.setDisable(true);
                        playButton.setDisable(true);
                    } else if (time.get() < Simulation.getLatestProcessedTime() && nextButton.isDisabled())  {
                        nextButton.setDisable(false);
                        playButton.setDisable(false);
                    }

                    if (time.get() == 1 && !previousButton.isDisabled())  {
                        previousButton.setDisable(true);
                    }
                    else if (time.get() > 1 && previousButton.isDisabled()) {
                        previousButton.setDisable(false);
                    }
                });

                if(time.get() > Simulation.getSimulationTotalTime()) {
                    timer.cancel();
                    Router.go("logs");
                }
                }
            };

            timer.scheduleAtFixedRate(task, 0, 300);
        }).start();
    }

    private void renderFrame(int time) {
        if (time > Simulation.getSimulationTotalTime()) return;
        Log log = getLog(time); if (log == null) return;

        timeText.setText(log.time().toString());
        Platform.runLater(() -> {
            simulationFrame.getChildren().clear();

            for (Map.Entry<Integer, Pair<Integer, Client>> register: log.registers().entrySet()) {
                addRegisterComponentToGUI(register, log);
            }
        });

    }

    private void addRegisterComponentToGUI(Map.Entry<Integer, Pair<Integer, Client>> register, Log log) {
        Node registerComponent = new CashRegisterComponent(register.getValue(), log.waitingClients().stream().filter(client -> client != null && Objects.equals(client.getRegister().getId(), register.getKey())).toList());
        simulationFrame.getChildren().add(registerComponent);
        HBox.setMargin(registerComponent, new Insets(8.0));
    }

    private Log getLog(int time) {
        Log log = null;

        try {
            log = Simulation.getFrame(time);
        } catch (Exception ignored) {}

        return log;
    }
}

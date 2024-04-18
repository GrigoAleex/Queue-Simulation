package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.controllers;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.AppProvider;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils.LoaderManager;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingController {

    @FXML
    private ProgressBar progress_bar;

    public void initialize() {
        Timer timer = new Timer();
        LoaderManager loaderManager = AppProvider.get(LoaderManager.class);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Double loadAmount = loaderManager.getLoadingProgress();

                Platform.runLater(() -> progress_bar.setProgress(loadAmount));

                if (loadAmount >= 1.0)  {
                    timer.cancel();
                    Router.go("simulation");
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 300);
    }
}

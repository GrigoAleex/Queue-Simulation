package com.example.pt2024_30223_grigorescu_alexandru_assignment_2;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.config.ConfigLoader;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.Window;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.routing.Router;

public class Main extends Application {
    public void start(Stage primaryStage) {
        new Window(primaryStage);

        Router.go("start");
    }

    public static void main(String[] args) {
        ConfigLoader.handle();
        launch(args);
    }
}
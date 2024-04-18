package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.exceptions.ViewNotFoundException;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.exceptions.WindowUninitializedException;

import java.io.IOException;
import java.net.URL;

public class Window {
    private final Stage stage;
    private static Window instance;

    public Window(Stage stage) {
        stage.setTitle("Marketplace Simulator x2000");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.getIcons().add(new Image("woman.png"));
        stage.show();
        this.stage = stage;
        instance = this;
    }

    public static Window getInstance() throws WindowUninitializedException {
        if (instance == null) throw new WindowUninitializedException();
        return instance;
    }

    public static void setView(String view) throws ViewNotFoundException, WindowUninitializedException {
        Window window = getInstance();

        URL viewPath = window.getClass().getResource(view + ".fxml");

        if (viewPath == null) throw new ViewNotFoundException();

        Platform.runLater(() -> {

            try {
                window.stage.setScene(new Scene(FXMLLoader.load(viewPath), 800, 600));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

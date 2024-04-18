package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.components;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;

public class ClientComponent extends VBox {
    public ClientComponent(Client client) {
        StackPane pane = new StackPane();

        Text id = new Text(client.getId().toString());
        id.setFont(Font.font("Poppins", FontWeight.BOLD, 18));
        id.setFill(Color.WHITE);

        try {
            pane.getChildren().add(new ImageComponent("/man.png", 48, 48));
            pane.getChildren().add(id);
        } catch (FileNotFoundException ignored) {
        }

        this.getChildren().add(pane);
    }

    public ClientComponent(Client client, Double progressValue) {
        this(client);
        this.getChildren().add( new ProgressBar(progressValue));
    }
}

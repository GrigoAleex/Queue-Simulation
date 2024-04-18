package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.frontend.components;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.models.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.List;

public class CashRegisterComponent extends VBox {
    public CashRegisterComponent(Pair<Integer, Client> currentClient, List<Client> clients) {
        this.setPrefWidth(121);
        this.setPrefHeight(200);
        this.setAlignment(Pos.TOP_CENTER);

        try {
            this.getChildren().add(new ImageComponent("/cash_registter.png", 121, 93));
        } catch (FileNotFoundException ignored) {
        }

        if (currentClient.getValue() != null) {
            VBox component = new ClientComponent(currentClient.getValue(), getClientProgression(currentClient));
            VBox.setMargin(component, new Insets(14));
            this.getChildren().add(component);
        }

        for (Client client : clients.stream().limit(3).toList()) {
            ClientComponent component = new ClientComponent(client);
            this.getChildren().add(component);
            VBox.setMargin(component, new Insets(8));
        }
    }

    private double getClientProgression(Pair<Integer, Client> currentClient) {
        return (double) (currentClient.getValue().getInitialServiceTime() - currentClient.getKey()) /
                        currentClient.getValue().getInitialServiceTime();
    }
}

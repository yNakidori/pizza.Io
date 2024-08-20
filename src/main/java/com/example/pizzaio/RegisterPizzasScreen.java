package com.example.pizzaio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPizzasScreen {

    private static final String MENU_FILE_PATH = "menu.txt";

    public void showRegisterPizzasScreen() {
        Stage stage = new Stage();
        stage.setTitle("Menu registration");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

// Campos de entrada
        Label flavorLabel = new Label("Pizza flavor:");
        TextField flavorField = new TextField();
        flavorField.setPromptText("Type the pizza flavor");

        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        priceField.setPromptText("Type the pizza price");

        Label sizeLabel = new Label("Tipo:");
        ComboBox<String> sizeComboBox = new ComboBox<>();
        sizeComboBox.getItems().addAll("Full size", "Half size");
        sizeComboBox.setValue("Full size");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> savePizza(flavorField.getText(), priceField.getText(), sizeComboBox.getValue()));

        vbox.getChildren().addAll(flavorLabel, flavorField, priceLabel, priceField, sizeLabel, sizeComboBox, saveButton);

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void savePizza(String flavor, String price, String size) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE_PATH, true))) {
            writer.write(flavor + ";" + price + ";" + size);
            writer.newLine();
            System.out.println("Pizza saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving pizza: " + e.getMessage());
        }
    }
}

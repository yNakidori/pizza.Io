package com.example.pizzaio;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen {

    public void showMainScreen(Stage stage) {
        VBox vbox = new VBox(20);

        Button registerPizzasButton = new Button("Cadastrar Pizzas");
        registerPizzasButton.setMinWidth(200);
        registerPizzasButton.setOnAction(e -> {
            RegisterPizzasScreen registerPizzasScreen = new RegisterPizzasScreen();
            registerPizzasScreen.showRegisterPizzasScreen();
        });

        Button startOrderButton = new Button("Iniciar Pedido");
        startOrderButton.setMinWidth(200);
        startOrderButton.setOnAction(e -> {
            StartOrderScreen startOrderScreen = new StartOrderScreen();
            startOrderScreen.showStartOrderScreen();
        });

        vbox.getChildren().addAll(registerPizzasButton, startOrderButton);

        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setTitle("Sistema de Pizzaria");
        stage.setScene(scene);
        stage.show();
    }
}

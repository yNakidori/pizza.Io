package com.example.pizzaio;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {

    public void showLoginScreen(Stage stage) {
        stage.setTitle("Login - Sistema de Pizzaria");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label userLabel = new Label("Usuário:");
        TextField userField = new TextField();
        userField.setPromptText("Digite seu usuário");

        Label passwordLabel = new Label("Senha:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Digite sua senha");

        Button loginButton = new Button("Login");
        loginButton.setMinWidth(200);
        loginButton.setOnAction(e -> handleLogin(stage, userField.getText(), passwordField.getText()));

        vbox.getChildren().addAll(userLabel, userField, passwordLabel, passwordField, loginButton);

        Scene scene = new Scene(vbox, 400, 250);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void handleLogin(Stage stage, String username, String password) {
        if (isValidCredentials(username, password)) {
            MainScreen mainScreen = new MainScreen();
            mainScreen.showMainScreen(stage);
        } else {
            System.out.println("Usuário ou senha incorretos.");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return "admin".equals(username) && "1234".equals(password);
    }
}

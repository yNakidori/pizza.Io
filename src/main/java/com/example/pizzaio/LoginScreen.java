package com.example.pizzaio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreen {

    private Stage stage;
    private static final String CREDENTIALS_FILE = "users.txt";

    public LoginScreen(Stage stage) {
        this.stage = stage;
    }

    public void showLoginScreen() {
        try {
            // Carregar o layout do FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScreenLayout.fxml"));
            Parent root = loader.load();

            // Configurar a cena
            Scene scene = new Scene(root, 640, 400);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            stage.setTitle("Login - pizza.Io");
            stage.setScene(scene);
            stage.show();

            // Referências aos componentes do FXML
            TextField userField = (TextField) scene.lookup("#usuarioTextField");
            PasswordField passwordField = (PasswordField) scene.lookup("#senhaTextField");
            Button loginButton = (Button) scene.lookup("#entrarButton");

            // Configurar a ação do botão de login
            loginButton.setOnAction(e -> handleLogin(stage, userField.getText(), passwordField.getText()));

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createNewUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

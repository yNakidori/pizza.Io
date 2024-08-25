package com.example.pizzaio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateUserScreen {

    @FXML
    private Button criarUsuarioButton;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button voltarButton;

    private Stage stage;
    private static final String CREDENTIALS_FILE = "users.txt";

    public CreateUserScreen(Stage stage) {
        this.stage = stage;
    }

    public void showCreateUserScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateUserScreenLayout.fxml"));
            loader.setController(this); // Define esta classe como o controlador
            Parent root = loader.load();

            // Configurar a cena
            Scene scene = new Scene(root, 640, 400);
            stage.setTitle("Criar Usuário - pizza.Io");
            stage.setScene(scene);
            stage.show();

            // Configurar as ações dos botões
            criarUsuarioButton.setOnAction(e -> handleCreate(userField.getText(), passwordField.getText()));
            voltarButton.setOnAction(e -> voltarButtonAction());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCreate(String username, String password) {
        if (isValidCredentials(username, password)) {
            if (isUsernameDuplicate(username)) {
                JOptionPane.showMessageDialog(null, "Nome de usuário já existe. Escolha outro.");
            } else {
                createNewUser(username, password);
                JOptionPane.showMessageDialog(null, "Usuário criado com sucesso.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }

    private void createNewUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isUsernameDuplicate(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(":");
                if (credentials[0].equals(username)) {
                    return true; // Retorna true se encontrar um nome de usuário duplicado
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Retorna false se o nome de usuário não for duplicado
    }

    private void voltarButtonAction() {
        LoginScreen loginScreen = new LoginScreen(stage);
        loginScreen.showLoginScreen();
    }
}

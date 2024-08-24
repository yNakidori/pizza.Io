package com.example.pizzaio;

import java.io.BufferedWriter;
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
    private Button createButton;

    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField senhaTextField;

    private Stage stage;
    private static final String CREDENTIALS_FILE = "users.txt";

    public CreateUserScreen(Stage stage) {
        this.stage = stage;
    }

    public void showCreateUserScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateUserScreenLayout.fxml"));
            loader.setController(this); // Define this class as the controller
            Parent root = loader.load();

            // Configurar a cena
            Scene scene = new Scene(root, 640, 400);
            stage.setTitle("Criar Usuário - pizza.Io");
            stage.setScene(scene);
            stage.show();

            //Configurar a ação do botão de criar
            createButton.setOnAction(e -> handleCreate(usuarioTextField.getText(), senhaTextField.getText()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleCreate(String username, String password) {
        if (isValidCredentials(username, password)) {
            createNewUser(username, password);
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso.");
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
}

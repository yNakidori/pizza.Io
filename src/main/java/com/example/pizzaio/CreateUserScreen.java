package com.example.pizzaio;

import javax.swing.JOptionPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private static final String CREDENTIALS_FILE = "users.txt";

    public CreateUserScreen(Stage stage) {
        this.stage = stage;
    }

    public void showCreteUserScreen() {
        try {
            //Carregar o layout FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateUserScreenLayout.fxml"));
            Parent root = loader.load();

            //Configurar a cena
            Scene scene = new Scene(root, 640, 400);
            stage.setTitle("Criar Usuário - pizza.Io");
            stage.setScene(scene);
            stage.show();

            //Refetencias aos componentes doFXML
            TextField userField = (TextField) scene.lookup("#usuarioTextField");
            PasswordField passwordField = (PasswordField) scene.lookup("#senhatextField");
            Button createButton = (Button) scene.lookup("#criarButton");

            //Configurar a ação do botão de criar
            createButton.setOnAction(e -> handleCreate(userField.getText(), passwordField.getText()));

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

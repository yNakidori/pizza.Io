package com.example.pizzaio;

import java.io.BufferedReader;
import java.io.FileReader;
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

public class LoginScreen {

    @FXML
    private Button entrarButton;

    @FXML
    private Button criarUsuarioButton;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private static final String CREDENTIALS_FILE = "users.txt";

    public LoginScreen(Stage stage) {
        this.stage = stage;
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginScreenLayout.fxml"));
            loader.setController(this); // Define this class as the controller
            Parent root = loader.load();

            // Configurar a cena
            Scene scene = new Scene(root, 640, 400);
            stage.setTitle("Login - pizza.Io");
            stage.setScene(scene);
            stage.show();

            // Configurar ações dos botões
            entrarButton.setOnAction(e -> handleLogin(userField.getText(), passwordField.getText()));
            criarUsuarioButton.setOnAction(e -> showCreateUserScreen());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLogin(String username, String password) {
        if (isValidCredentials(username, password)) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso.");
            // Redirecionar para a próxima tela da aplicação
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(":");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void showCreateUserScreen() {
        CreateUserScreen createUserScreen = new CreateUserScreen(stage);
        createUserScreen.showCreateUserScreen();
    }
}

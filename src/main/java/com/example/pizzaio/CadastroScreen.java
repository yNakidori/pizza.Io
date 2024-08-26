package com.example.pizzaio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroScreen {

    @FXML
    private TextField produtoTextField;

    @FXML
    private TextField valorTextField;

    @FXML
    private TextField codigoTextField;

    @FXML
    private Button limparButton;

    @FXML
    private Button adicionarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private TableView<?> cadastroTableView;

    private Stage stage;

    public CadastroScreen() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        limparButton.setOnAction(e -> limparCampos());
        adicionarButton.setOnAction(e -> adicionarProduto());
        voltarButton.setOnAction(e -> voltarParaTelaPrincipal());
    }

    private void limparCampos() {
        produtoTextField.clear();
        valorTextField.clear();
        codigoTextField.clear();
    }

    private void adicionarProduto() {
        String produto = produtoTextField.getText();
        String valor = valorTextField.getText();
        String codigo = codigoTextField.getText();

        if (produto.isEmpty() || valor.isEmpty() || codigo.isEmpty()) {
            System.out.println("Todos os campos precisam ser preenchidos!");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("menu.txt", true))) {
            writer.write(produto + ";" + valor + ";" + codigo);
            writer.newLine();
            System.out.println("Produto adicionado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
        limparCampos();
    }

    private void voltarParaTelaPrincipal() {
        try {
            if (stage != null) {
                MainScreen mainScreen = new MainScreen(stage);
                mainScreen.showMainScreen();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

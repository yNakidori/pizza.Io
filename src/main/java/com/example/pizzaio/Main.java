package com.example.pizzaio;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Layout vertical dos botões
        VBox vbox = new VBox(20);

        // Botão para cadastrar pizzas
        Button registerPizzasButton = new Button("Cadastrar Pizzas");
        registerPizzasButton.setMinWidth(200);

        // Botão para iniciar pedido
        Button startOrderButton = new Button("Iniciar Pedido");
        startOrderButton.setMinWidth(200);

        // Evento dos botões
        registerPizzasButton.setOnAction(e -> showRegisterPizzasScreen());
        startOrderButton.setOnAction(e -> showStartOrderScreen());

        // Exibir botões na tela
        vbox.getChildren().addAll(registerPizzasButton, startOrderButton);

        // Configurar a cena e o palco
        Scene scene = new Scene(vbox, 800, 600); // Tamanho da tela inicial
        // Aplica estilo
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        primaryStage.setTitle("Sistema de Pizzaria");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false); // Tela cheia
        primaryStage.show();
    }

    // Mostrar a tela de cadastro de pizzas
    private void showRegisterPizzasScreen() {
        Stage stage = new Stage();
        stage.setTitle("Cadastro de cardápio");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Mostrar a tela de pedidos
    private void showStartOrderScreen() {
        Stage stage = new Stage();
        stage.setTitle("Pedidos");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        // Área de texto para os detalhes do pedido
        TextArea orderDetailsArea = new TextArea();
        orderDetailsArea.setPromptText("Digite os detalhes do pedido aqui...");

        // Botão para imprimir o pedido
        Button printButton = new Button("Imprimir Pedido");
        printButton.setOnAction(e -> imprimirPedido(orderDetailsArea.getText()));

        // Adiciona os componentes ao layout
        vbox.getChildren().addAll(orderDetailsArea, printButton);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Método para imprimir o pedido
    private void imprimirPedido(String pedido) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null && printerJob.showPrintDialog(null)) {
            TextArea textArea = new TextArea(pedido);
            boolean success = printerJob.printPage(textArea);

            if (success) {
                printerJob.endJob();
                System.out.println("Pedido impresso com sucesso!");
            } else {
                System.out.println("Falha ao imprimir o pedido.");
            }
        } else {
            System.out.println("Impressão cancelada.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.example.pizzaio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartOrderScreen {

    private static final String MENU_FILE_PATH = "menu.txt";

    public void showStartOrderScreen() {
        Stage stage = new Stage();
        stage.setTitle("Pedidos");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        TextArea orderDetailsArea = new TextArea();
        orderDetailsArea.setPromptText("Detalhes do pedido...");

        VBox menuBox = new VBox(10);
        loadMenu(menuBox, orderDetailsArea);

        Button printButton = new Button("Imprimir Pedido");
        printButton.setOnAction(e -> imprimirPedido(orderDetailsArea.getText()));

        vbox.getChildren().addAll(menuBox, orderDetailsArea, printButton);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void loadMenu(VBox menuBox, TextArea orderDetailsArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] pizzaDetails = line.split(";");
                if (pizzaDetails.length == 3) {
                    String flavor = pizzaDetails[0];
                    String price = pizzaDetails[1];
                    String size = pizzaDetails[2];

                    CheckBox pizzaCheckBox = new CheckBox(flavor + " (" + size + ") - R$" + price);
                    pizzaCheckBox.setOnAction(e -> {
                        if (pizzaCheckBox.isSelected()) {
                            orderDetailsArea.appendText(pizzaCheckBox.getText() + "\n");
                        } else {
                            orderDetailsArea.setText(orderDetailsArea.getText().replace(pizzaCheckBox.getText() + "\n", ""));
                        }
                    });

                    menuBox.getChildren().add(pizzaCheckBox);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o cardápio: " + e.getMessage());
        }
    }

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
}

package com.example.pizzaio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MainScreen {

    @FXML
    private ListView<String> menuListView;

    @FXML
    private ListView<String> orderListView;

    @FXML
    private TextArea obsTextArea;

    @FXML
    private Button imprimirButton;

    @FXML
    private Button limparButton;

    @FXML
    private Button cardapioButton;

    @FXML
    private Button cadastrosButton;

    private Stage stage;
    private static final String MENU_FILE_PATH = "menu.txt";

    public MainScreen(Stage stage) {
        this.stage = stage;
    }

    public void showMainScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScreenLayout.fxml"));
            loader.setController(this); // Define este controlador para a tela
            Parent root = loader.load();

            // Configurar a cena
            Scene scene = new Scene(root, 640, 400);
            stage.setTitle("Tela Principal - pizza.Io");
            stage.setScene(scene);
            stage.show();

            // Configurar ações dos botões
            cardapioButton.setOnAction(e -> loadMenu());
            imprimirButton.setOnAction(e -> {
                StringBuilder pedido = new StringBuilder();
                for (String item : orderListView.getItems()) {
                    pedido.append(item).append("\n");
                }
                String observacoes = obsTextArea.getText();
                if (!observacoes.isEmpty()) {
                    pedido.append("\nObservações: ").append(observacoes);
                }
                imprimirPedido(pedido.toString());
            });
            limparButton.setOnAction(e -> limparPedido());
            cadastrosButton.setOnAction(e -> showCadastroScreen());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMenu() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE_PATH))) {
            String line;
            menuListView.getItems().clear(); // Limpa a lista antes de carregar
            while ((line = reader.readLine()) != null) {
                String[] pizzaDetails = line.split(";");
                if (pizzaDetails.length == 3) {
                    String flavor = pizzaDetails[0];
                    String price = pizzaDetails[1];
                    String size = pizzaDetails[2];

                    String item = flavor + " (" + size + ") - R$" + price;
                    menuListView.getItems().add(item);

                    menuListView.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) { // Duplo clique para adicionar ao pedido
                            String selectedItem = menuListView.getSelectionModel().getSelectedItem();
                            if (selectedItem != null) {
                                orderListView.getItems().add(selectedItem);
                            }
                        }
                    });
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o cardápio: " + e.getMessage());
        }
    }

    private void imprimirPedido(String pedido) {

        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null) {
            // Imprimir diretamente na impressora padrão sem mostrar o diálogo de impressão
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

    private void limparPedido() {
        orderListView.getItems().clear();
        obsTextArea.clear();
    }

    private void showCadastroScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CadastroScreenLayout.fxml"));
            Parent root = loader.load();
            CadastroScreen cadastroScreen = loader.getController();
            cadastroScreen.setStage(stage);
            Scene cadastroScene = new Scene(root, 640, 400);
            stage.setScene(cadastroScene);
            stage.setTitle("Cadastros - pizza.Io");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

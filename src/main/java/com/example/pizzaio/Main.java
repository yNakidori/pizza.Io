package com.example.pizzaio;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.showLoginScreen(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

module com.example.pizzaio {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pizzaio to javafx.fxml;
    exports com.example.pizzaio;
}
module com.example.prueba3000 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.prueba3000 to javafx.fxml;
    exports com.example.prueba3000;
    exports com.example.prueba3000.controllers;
    opens com.example.prueba3000.controllers to javafx.fxml;
}
package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @javafx.fxml.FXML
    private AnchorPane cambiarpagina;
    @javafx.fxml.FXML
    private TextField nombreiniciar;
    @javafx.fxml.FXML
    private TextField contraseña1;
    @javafx.fxml.FXML
    private Button iniciosesion;
    @javafx.fxml.FXML
    private Label incorrecte2;
    @javafx.fxml.FXML
    private TextField nombre;
    @javafx.fxml.FXML
    private TextField apellidos;
    @javafx.fxml.FXML
    private TextField email;
    @javafx.fxml.FXML
    private PasswordField contraseña;
    @javafx.fxml.FXML
    private PasswordField confcontraseña;
    @javafx.fxml.FXML
    private Button boto;
    @javafx.fxml.FXML
    private Label incorrecte;
    @javafx.fxml.FXML
    private TextField nomuser;
    @javafx.fxml.FXML
    private Label incorrecte1;
    @javafx.fxml.FXML
    private RadioButton masculino;
    @javafx.fxml.FXML
    private ToggleGroup sexo;
    @javafx.fxml.FXML
    private RadioButton femenino;

    @javafx.fxml.FXML
    public void iniciosesion(ActionEvent actionEvent) {

        try {
           Parent root = FXMLLoader.load(Main.class.getResource("vistas/VistaPrincipal.fxml"));

           Scene scene = new Scene(root);

           Stage stage = (Stage) iniciosesion.getScene().getWindow();

            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }

    @javafx.fxml.FXML
    public void registrarse(ActionEvent actionEvent) {
    }
}

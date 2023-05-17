package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VistaPrincipalController {
    @javafx.fxml.FXML
    private ImageView fotoHombre;
    @javafx.fxml.FXML
    private Button buttonInicio;
    @javafx.fxml.FXML
    private Button buttonApuntes;
    @javafx.fxml.FXML
    private Label labelBienvenida;
    @javafx.fxml.FXML
    private Label labelNombreUsuario;
    @javafx.fxml.FXML
    private Button buttonAmigos;
    @javafx.fxml.FXML
    private ImageView buttonCerrarSesion;
    @javafx.fxml.FXML
    private ImageView fotoMujer;
    @javafx.fxml.FXML
    private AnchorPane rootPane;

    @javafx.fxml.FXML
    public void cambiarVistaAmigos(ActionEvent actionEvent) {

        try {
            AnchorPane pane = FXMLLoader.load(Main.class.getResource("vistas/VistaAmigos.fxml"));

            rootPane.getChildren().setAll(pane);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

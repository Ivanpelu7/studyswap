package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VistaPrincipalController implements Initializable {

    @FXML
    private ImageView fotoHombre;
    @FXML
    private Button buttonInicio;
    @FXML
    private Button buttonApuntes;
    @FXML
    private Label labelBienvenida;
    @FXML
    private Label labelNombreUsuario;
    @FXML
    private Button buttonAmigos;
    @FXML
    private ImageView buttonCerrarSesion;
    @FXML
    private ImageView fotoMujer;
    @FXML
    private AnchorPane rootPane;
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUsuario(Usuario u) {

        this.usuario = u;

        labelNombreUsuario.setText(usuario.getNombreUsuario());
    }

    @FXML
    public void cambiarVistaAmigos(ActionEvent actionEvent) {

        try {
            AnchorPane pane = FXMLLoader.load(Main.class.getResource("vistas/VistaAmigos.fxml"));

            rootPane.getChildren().setAll(pane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Usuario;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaPerfil.fxml"));

            Parent root = loader.load();

            rootPane.getChildren().setAll(root);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void setUsuario(Usuario u) {

        this.usuario = u;

        labelNombreUsuario.setText(usuario.getNombreUsuario());

        if (usuario.getSexo().equals("M")) {
            fotoHombre.setVisible(true);
            fotoMujer.setVisible(false);
            labelBienvenida.setText("BIENVENIDO,");

        } else if (usuario.getSexo().equals("F")) {
            fotoMujer.setVisible(true);
            fotoHombre.setVisible(false);
            labelBienvenida.setText("BIENVENIDA,");
        }
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

    @FXML
    public void cerrarSesion(Event event) throws IOException {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("¿Seguro que quiere cerrar la sesión?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/Login.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) buttonAmigos.getScene().getWindow();

            stage.setScene(scene);
        }
    }

    @FXML
    public void cambiarVistaInicio(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaPerfil.fxml"));

            Parent root = loader.load();

            rootPane.getChildren().setAll(root);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

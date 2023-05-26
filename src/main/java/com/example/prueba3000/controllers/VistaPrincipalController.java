package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.SolicitudAmistadModel;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.model.UsuarioModel;
import com.example.prueba3000.util.UsuarioHolder;
import com.example.prueba3000.util.Validador;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class VistaPrincipalController implements Initializable {

    @FXML
    private ImageView fotoHombre;
    @FXML
    private Button buttonInicio;
    @FXML
    private Button buttonApuntes;
    @FXML
    private Label labelNombreUsuario;
    @FXML
    private Button buttonAmigos;
    @FXML
    private ImageView buttonCerrarSesion;
    @FXML
    private ImageView fotoMujer;
    @FXML
    public AnchorPane rootPane;
    public Usuario usuario;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelApellidos;
    @FXML
    private Circle circuloSolicitudes;
    @FXML
    private Label numeroSolicitudes;
    private Parent pane;
    private VistaPerfilController vpc;
    private FXMLLoader loader;
    @FXML
    private Button Administrador;
    @FXML
    private Button buttonAjustes;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Circle fotoPerfil;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            this.usuario = UsuarioHolder.getUsuario();

            if (this.usuario.getTipoUsuario() == 0) {
                Administrador.setVisible(false);
            }

            SolicitudAmistadModel sam = new SolicitudAmistadModel();
            UsuarioModel um = new UsuarioModel();

            HashMap<Integer, Usuario> usuarios = um.recuperarUsuarios();

            labelNombreUsuario.setText(usuario.getNombreUsuario());
            labelNombre.setText(usuario.getNombre());
            labelApellidos.setText(usuario.getApellidos());

            if (this.usuario.getFotoPerfil() != null) {
                fotoPerfil.setFill(new ImagePattern(this.usuario.getFotoPerfil()));
                fotoHombre.setVisible(false);
                fotoMujer.setVisible(false);

            } else {
                if (usuario.getSexo().equals("M")) {
                    fotoPerfil.setVisible(false);
                    fotoHombre.setVisible(true);
                    fotoMujer.setVisible(false);

                } else if (usuario.getSexo().equals("F")) {
                    fotoPerfil.setVisible(false);
                    fotoMujer.setVisible(true);
                    fotoHombre.setVisible(false);
                }
            }

            if (sam.peticionesAmistad(this.usuario, usuarios).size() > 0) {
                circuloSolicitudes.setVisible(true);
                numeroSolicitudes.setVisible(true);
                numeroSolicitudes.setText(String.valueOf(sam.peticionesAmistad(this.usuario, usuarios).size()));
            }

            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/VistaPerfil.fxml"));
            pane = loader.load();
            rootPane.getChildren().setAll(pane);

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFotoPerfil(Image image) {

        this.fotoPerfil.setFill(new ImagePattern(image));
    }


    @FXML
    public void cambiarVistaAmigos(ActionEvent actionEvent) throws IOException {

        try {
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/VistaAmigos.fxml"));
            pane = loader.load();

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
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/Login.fxml"));
            pane = loader.load();

            Scene scene = new Scene(pane);

            Stage stage = (Stage) buttonAmigos.getScene().getWindow();

            stage.setScene(scene);
        }
    }

    @FXML
    public void cambiarVistaInicio(ActionEvent actionEvent) throws IOException {

        loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("vistas/VistaPerfil.fxml"));
        pane = loader.load();

        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void cambiarVistaApuntes(ActionEvent actionEvent) throws SQLException {

        try {
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/VistaApuntes.fxml"));
            pane = loader.load();

            rootPane.getChildren().setAll(pane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void CambiarAdministracion(ActionEvent actionEvent) {
        try {
            FXMLLoader pane = new FXMLLoader(Main.class.getResource("vistas/Administracion.fxml"));
            Parent root = pane.load();
            rootPane.getChildren().setAll(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void cambiarAjustes(ActionEvent actionEvent) throws IOException {

        loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("vistas/VistaAjustes.fxml"));
        pane = loader.load();

        rootPane.getChildren().setAll(pane);
    }
}

package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.SolicitudAmistadModel;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.model.UsuarioModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("vistas/VistaPerfil.fxml"));

            pane = loader.load();

            rootPane.getChildren().setAll(pane);

            circuloSolicitudes.setVisible(false);
            numeroSolicitudes.setVisible(false);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDatos(Usuario u) throws SQLException, IOException {

        SolicitudAmistadModel sam = new SolicitudAmistadModel();
        UsuarioModel um = new UsuarioModel();

        HashMap<Integer, Usuario> usuarios = um.recuperarUsuarios();

        this.usuario = u;

        labelNombreUsuario.setText(usuario.getNombreUsuario());
        labelNombre.setText(usuario.getNombre());
        labelApellidos.setText(usuario.getApellidos());

        if (usuario.getSexo().equals("M")) {
            fotoHombre.setVisible(true);
            fotoMujer.setVisible(false);

        } else if (usuario.getSexo().equals("F")) {
            fotoMujer.setVisible(true);
            fotoHombre.setVisible(false);
        }

        if (sam.peticionesAmistad(u, usuarios).size() > 0) {
            circuloSolicitudes.setVisible(true);
            numeroSolicitudes.setVisible(true);
            numeroSolicitudes.setText(String.valueOf(sam.peticionesAmistad(u, usuarios).size()));
        }

        vpc = loader.getController();
        vpc.setApuntesSubidos(this.usuario);
        vpc.setApuntesDescargados(this.usuario);
    }

    @FXML
    public void cambiarVistaAmigos(ActionEvent actionEvent) {

        try {
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/VistaAmigos.fxml"));
            pane = loader.load();

            rootPane.getChildren().setAll(pane);

            VistaAmigosController vac = loader.getController();
            vac.setUsuario(usuario);

        } catch (IOException | SQLException e) {
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

        vpc = loader.getController();
        vpc.setApuntesSubidos(this.usuario);
        vpc.setApuntesDescargados(this.usuario);

        rootPane.getChildren().setAll(pane);
    }

    @FXML
    public void cambiarVistaApuntes(ActionEvent actionEvent) throws SQLException {

        try {
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vistas/VistaApuntes.fxml"));
            pane = loader.load();

            VistaApuntesController vac = loader.getController();
            vac.setUsuario(this.usuario);

            rootPane.getChildren().setAll(pane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

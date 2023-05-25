package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.model.UsuarioModel;
import com.example.prueba3000.util.UsuarioHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;

public class AjustesController implements Initializable {

    @javafx.fxml.FXML
    private ImageView fotoPerfil;
    @javafx.fxml.FXML
    private TextField labelNombre;
    @javafx.fxml.FXML
    private TextField labelApellidos;
    @javafx.fxml.FXML
    private TextField labelNombreUsuario;
    @javafx.fxml.FXML
    private TextField labelEmail;
    @javafx.fxml.FXML
    private PasswordField labelPassword;
    private Usuario usuario;
    @javafx.fxml.FXML
    private AnchorPane mainAnchor;
    @javafx.fxml.FXML
    private Button buttonCambiarFotoPerfil;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.usuario = UsuarioHolder.getUsuario();

        labelNombre.setText(usuario.getNombre());
        labelApellidos.setText(usuario.getApellidos());
        labelEmail.setText(usuario.getEmail());
        labelPassword.setText(usuario.getPassword());
        labelNombreUsuario.setText(usuario.getNombreUsuario());

        if (this.usuario.getFotoPerfil() != null) {
            fotoPerfil.setImage(this.usuario.getFotoPerfil());

        } else if (this.usuario.getSexo().equals("M")) {
            fotoPerfil.setImage(new Image(Main.class.getResourceAsStream("images/hombre.png")));

        } else if (this.usuario.getSexo().equals("F")) {
            fotoPerfil.setImage(new Image(Main.class.getResourceAsStream("images/mujer.png")));
        }
    }


    @javafx.fxml.FXML
    public void cambiarFotoPerfil(ActionEvent actionEvent) throws SQLException, IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png"));

        File imagenSeleccionada = fileChooser.showOpenDialog(null);

        if (imagenSeleccionada != null) {
            UsuarioModel usuarioModel = new UsuarioModel();
            int i = usuarioModel.anadirFotoPerfil(this.usuario, imagenSeleccionada);

            if (i != 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Foto de perfil cambiada correctamente");
                a.showAndWait();

                Image image = new Image(imagenSeleccionada.toURI().toString());
                this.usuario.setFotoPerfil(image);

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaAjustes.fxml"));
                Parent pane = loader.load();
                mainAnchor.getChildren().setAll(pane);
            }


        }


    }
}

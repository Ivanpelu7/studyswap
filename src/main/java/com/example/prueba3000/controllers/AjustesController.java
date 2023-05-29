package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.AmigosModel;
import com.example.prueba3000.model.ApunteModel;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.model.UsuarioModel;
import com.example.prueba3000.util.UsuarioHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private TextField textFieldNuevoNombre;
    @javafx.fxml.FXML
    private TextField textFieldNuevoNombreUsuario;
    @javafx.fxml.FXML
    private TextField textFieldNuevaContrasena;
    @javafx.fxml.FXML
    private TextField textFieldNuevoCorreo;
    @javafx.fxml.FXML
    private TextField textFieldNuevosApellidos;
    @javafx.fxml.FXML
    private Button buttonModificarApellidos;
    @javafx.fxml.FXML
    private Button buttonModificarNombre;
    @javafx.fxml.FXML
    private Button buttonModificarNombreUsuario;
    @javafx.fxml.FXML
    private Button buttonModificarContrasena;
    @javafx.fxml.FXML
    private Button buttonModificarCorreo;
    @javafx.fxml.FXML
    private Circle fotoCircle;
    @javafx.fxml.FXML
    private Button buttonEliminarCuenta;
    private UsuarioModel usuarioModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.usuario = UsuarioHolder.getUsuario();

        usuarioModel = new UsuarioModel();

        labelNombre.setText(usuario.getNombre());
        labelApellidos.setText(usuario.getApellidos());
        labelEmail.setText(usuario.getEmail());
        labelPassword.setText(usuario.getPassword());
        labelNombreUsuario.setText(usuario.getNombreUsuario());

        if (this.usuario.getFotoPerfil() != null) {
            fotoPerfil.setVisible(false);
            fotoCircle.setVisible(true);
            fotoCircle.setFill(new ImagePattern(this.usuario.getFotoPerfil()));

        } else if (this.usuario.getSexo().equals("M")) {
            fotoPerfil.setImage(new Image(Main.class.getResourceAsStream("images/hombre.png")));

        } else if (this.usuario.getSexo().equals("F")) {
            fotoPerfil.setImage(new Image(Main.class.getResourceAsStream("images/mujer.png")));
        }
    }


    @Deprecated
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

    @javafx.fxml.FXML
    public void modificarApellidos(ActionEvent actionEvent) throws SQLException, IOException {

        String apellidos = textFieldNuevosApellidos.getText();

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Va a modificar los apellidos, ¿estas seguro/a?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {
            int i = usuarioModel.modificarApellidos(this.usuario, apellidos);

            if (i > 0) {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Apellidos modificados correctamente");
                a.showAndWait();

                this.usuario.setApellidos(apellidos);

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaAjustes.fxml"));
                Parent pane = loader.load();
                mainAnchor.getChildren().setAll(pane);
            }
        }
    }

    @javafx.fxml.FXML
    public void modificarNombre(ActionEvent actionEvent) throws SQLException, IOException {

        String nombre = textFieldNuevoNombre.getText();

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Va a modificar el nombre, ¿estas seguro/a?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {
            int i = usuarioModel.modificarNombre(this.usuario, nombre);

            if (i > 0) {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Nombre modificado correctamente");
                a.showAndWait();

                this.usuario.setNombre(nombre);

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaAjustes.fxml"));
                Parent pane = loader.load();
                mainAnchor.getChildren().setAll(pane);
            }
        }
    }

    @javafx.fxml.FXML
    public void modificarNombreUsuario(ActionEvent actionEvent) throws SQLException, IOException {

        String nombreUsuario = textFieldNuevoNombreUsuario.getText();

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Va a modificar el nombre de usuario, ¿estas seguro/a?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {
            int i = usuarioModel.modificarNombreUsuario(this.usuario, nombreUsuario);

            if (i > 0) {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Nombre de usuario modificado correctamente");
                a.showAndWait();

                this.usuario.setNombre(nombreUsuario);

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaAjustes.fxml"));
                Parent pane = loader.load();
                mainAnchor.getChildren().setAll(pane);
            }
        }
    }

    @javafx.fxml.FXML
    public void cambiarContrasena(ActionEvent actionEvent) throws SQLException, IOException {

        String password = textFieldNuevaContrasena.getText();

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Va a modificar la contraseña, ¿estas seguro/a?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {
            int i = usuarioModel.modificarPassword(this.usuario, password);

            if (i > 0) {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Contraseña modificada correctamente");
                a.showAndWait();

                this.usuario.setNombre(password);

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaAjustes.fxml"));
                Parent pane = loader.load();
                mainAnchor.getChildren().setAll(pane);
            }
        }
    }

    @javafx.fxml.FXML
    public void modificarCorreoElectronico(ActionEvent actionEvent) throws SQLException, IOException {

        String email = textFieldNuevoNombre.getText();

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Va a modificar el correo electrónico, ¿estas seguro/a?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {
            int i = usuarioModel.modificarCorreoElectronico(this.usuario, email);

            if (i > 0) {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Correo electrónico modificado correctamente");
                a.showAndWait();

                this.usuario.setNombre(email);

                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaAjustes.fxml"));
                Parent pane = loader.load();
                mainAnchor.getChildren().setAll(pane);
            }
        }
    }

    @javafx.fxml.FXML
    public void eliminarCuenta(ActionEvent actionEvent) throws SQLException, IOException {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Va a eliminar la cuenta y todos los datos relacionados con este usuario, ¿estas seguro/a?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {
            AmigosModel amigosModel = new AmigosModel();
            ApunteModel apunteModel = new ApunteModel();
            amigosModel.eliminarAmistad(this.usuario);
            apunteModel.eliminarDescargasUsuario(this.usuario);
            apunteModel.eliminarApuntesUsuario(this.usuario);
            usuarioModel.eliminarUsuario(this.usuario);

            a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("Cuenta eliminada correctamente");
            a.showAndWait();

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/Login.fxml"));
            Parent pane = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("StudySwap");
            stage.getIcons().setAll(new Image(Main.class.getResourceAsStream("images/icono.png")));
            Stage oldStage = (Stage) buttonEliminarCuenta.getScene().getWindow();
            oldStage.close();
            stage.show();
        }
    }
}

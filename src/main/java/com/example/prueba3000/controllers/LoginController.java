package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.model.UsuarioModel;
import com.example.prueba3000.util.UsuarioHolder;
import com.example.prueba3000.util.Validador;
import javafx.animation.TranslateTransition;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button buttonCambiar;
    @FXML
    private Button buttonRegistrarse;
    @FXML
    private Label labelYaTienesCuenta;
    @FXML
    private RadioButton radioButtonFem;
    @FXML
    private RadioButton radioButtonMasc;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private TextField textFieldApellidos;
    @FXML
    private TextField textFieldCorreo;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldNombreUsuarioR;
    @FXML
    private Button buttonCambiarRegistro;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private Label labelAunNoTienesCuenta;
    @FXML
    private TextField textFieldNombreUsuarioI;
    @FXML
    private Button buttonAcceder;
    @FXML
    private ImageView imageRegistro;
    @FXML
    private ImageView imageInicioSesion;
    @FXML
    private Label labelDatosIncorrectos;
    @FXML
    private PasswordField textFieldPasswordI;
    @FXML
    private PasswordField textFieldPasswordR;
    @FXML
    private PasswordField textFieldConfPassword;
    @FXML
    private Label labelRegistroIncorrecto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textFieldPasswordR.setVisible(false);
        textFieldNombreUsuarioR.setVisible(false);
        textFieldApellidos.setVisible(false);
        textFieldNombre.setVisible(false);
        textFieldCorreo.setVisible(false);
        textFieldConfPassword.setVisible(false);
        radioButtonFem.setVisible(false);
        radioButtonMasc.setVisible(false);
        labelYaTienesCuenta.setVisible(false);
        buttonRegistrarse.setVisible(false);
        buttonCambiar.setVisible(false);
        imageRegistro.setVisible(false);

    }

    @FXML
    public void cambiarARegistro(ActionEvent actionEvent) {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(anchorPane1);

        slide.setToX(415);
        slide.play();

        labelRegistroIncorrecto.setText("");
        anchorPane2.setTranslateX(-272);
        buttonCambiar.setVisible(true);
        labelYaTienesCuenta.setVisible(true);
        imageRegistro.setVisible(true);

        textFieldPasswordR.setVisible(true);
        textFieldNombreUsuarioR.setVisible(true);
        textFieldApellidos.setVisible(true);
        textFieldNombre.setVisible(true);
        textFieldCorreo.setVisible(true);
        textFieldConfPassword.setVisible(true);
        radioButtonFem.setVisible(true);
        radioButtonMasc.setVisible(true);
        labelYaTienesCuenta.setVisible(true);
        buttonRegistrarse.setVisible(true);
        buttonCambiar.setVisible(true);
        labelRegistroIncorrecto.setVisible(true);

        textFieldNombreUsuarioI.setVisible(false);
        textFieldPasswordI.setVisible(false);
        buttonAcceder.setVisible(false);
        buttonCambiarRegistro.setVisible(false);
        labelAunNoTienesCuenta.setVisible(false);
        imageInicioSesion.setVisible(false);
        labelDatosIncorrectos.setText("");

        slide.setOnFinished((e -> {


        }));

    }

    @FXML
    public void registrarse(ActionEvent actionEvent) throws SQLException {

        UsuarioModel um = new UsuarioModel();

        Validador v = new Validador();

        HashMap<Integer, Usuario> usuarios = um.recuperarUsuarios();

        String name = textFieldNombre.getText();
        String apell = textFieldApellidos.getText();
        String mail = textFieldCorreo.getText();
        String nombreUsuario = textFieldNombreUsuarioR.getText();
        String contra = textFieldPasswordR.getText();
        String segundaContra = textFieldConfPassword.getText();
        String genero = null;


        if (radioButtonMasc.isSelected()) {
            genero = "M";

        } else if (radioButtonFem.isSelected()) {
            genero = "F";
        }

        if (!v.camposRellenados(textFieldNombre, textFieldApellidos, textFieldCorreo, radioButtonMasc, radioButtonFem, textFieldPasswordR, textFieldConfPassword, textFieldNombreUsuarioR)) {
            labelRegistroIncorrecto.setText("Tienes que rellenar todos los campos");

        } else if (!v.nombreUsuarioExiste(nombreUsuario, usuarios)) {
            labelRegistroIncorrecto.setText("El nombre de usuario ya existe");

        } else if (!v.validarPasswordIguales(contra, segundaContra)) {
            labelRegistroIncorrecto.setText("Las contraseñas no coinciden");

        } else if (!v.validarPasswordFormato(contra)) {
            labelRegistroIncorrecto.setText("La contraseña debe contener 5 caracteres máximo (solo números y letras)");

        } else {
            int i = um.addUsuario(new Usuario(nombreUsuario, contra, mail, name, apell, 0, genero));

            if (i != 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Se ha registrado correctamente");
                a.showAndWait();

                textFieldNombre.setText("");
                textFieldApellidos.setText("");
                textFieldCorreo.setText("");
                textFieldPasswordR.setText("");
                textFieldConfPassword.setText("");
                sexo.getSelectedToggle().setSelected(false);
                textFieldNombreUsuarioR.setText("");
            }
        }
    }

    @FXML
    public void cambiarIniciarSesion(ActionEvent actionEvent) {

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(anchorPane1);

        slide.setToX(0);
        slide.play();

        anchorPane2.setTranslateX(0);
        buttonCambiar.setVisible(false);
        labelYaTienesCuenta.setVisible(false);

        textFieldPasswordR.setVisible(false);
        textFieldNombreUsuarioR.setVisible(false);
        textFieldApellidos.setVisible(false);
        textFieldNombre.setVisible(false);
        textFieldCorreo.setVisible(false);
        textFieldConfPassword.setVisible(false);
        radioButtonFem.setVisible(false);
        radioButtonMasc.setVisible(false);
        labelYaTienesCuenta.setVisible(false);
        buttonRegistrarse.setVisible(false);
        buttonCambiar.setVisible(false);
        imageRegistro.setVisible(false);
        labelRegistroIncorrecto.setVisible(false);

        textFieldNombreUsuarioI.setVisible(true);
        textFieldPasswordI.setVisible(true);
        buttonAcceder.setVisible(true);
        buttonCambiarRegistro.setVisible(true);
        labelAunNoTienesCuenta.setVisible(true);
        imageInicioSesion.setVisible(true);

        slide.setOnFinished((e -> {


        }));
    }

    @FXML
    public void iniciarSesion(ActionEvent actionEvent) throws SQLException, IOException {

        UsuarioModel um = new UsuarioModel();

        Validador v = new Validador();

        HashMap<Integer, Usuario> usuarios = um.recuperarUsuarios();

        if (!v.comprobarInicioSesion(textFieldNombreUsuarioI.getText(), textFieldPasswordI.getText(), usuarios)) {
            labelDatosIncorrectos.setText("Datos introducidos incorrectos");

        } else {
            Usuario usuarioConectado = null;

            for (Usuario u : usuarios.values()) {

                if (u.getNombreUsuario().equals(textFieldNombreUsuarioI.getText())) {
                    usuarioConectado = u;
                }
            }

            UsuarioHolder.setUsuario(usuarioConectado);

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("Inicio de sesión correcto");
            a.showAndWait();

            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaPrincipal.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) buttonAcceder.getScene().getWindow();
                stage.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }
}

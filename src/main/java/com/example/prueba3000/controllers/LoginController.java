package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.util.Validador;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.fxml.FXML;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.model.UsuarioModel;

import java.sql.*;
import java.util.HashMap;

public class LoginController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField email;
    @FXML
    private PasswordField confcontraseña;
    @FXML
    private Label incorrecte;
    @FXML
    private TextField nomuser;
    @FXML
    private TextField nombreiniciar;
    @FXML
    private PasswordField contraseña;
    @FXML
    private Label incorrecte2;
    @FXML
    private RadioButton masculino;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private RadioButton femenino;
    @FXML
    private PasswordField passwordInicio;
    @FXML
    private Button buttonInicioSesion;
    @FXML
    private Button buttonRegistro;


    @FXML
    public void registrarse(ActionEvent actionEvent) throws SQLException {

        UsuarioModel um= new UsuarioModel();

        Validador v = new Validador();

        HashMap<Integer, Usuario> usuarios = um.recuperarUsuarios();

        String name = nombre.getText();
        String apell = apellidos.getText();
        String mail = email.getText();
        String nombreUsuario = nomuser.getText();
        String contra = contraseña.getText();
        String segundaContra = confcontraseña.getText();
        String genero = null;

        if (masculino.isSelected()) {
            genero = "M";

        } else if (femenino.isSelected()) {
            genero = "F";
        }

        if (!v.camposRellenados(nombre, apellidos, email, masculino, femenino, contraseña, confcontraseña)) {
            incorrecte.setText("Tienes que rellenar todos los campos");

        } else if (!v.nombreUsuarioExiste(nombreUsuario, usuarios)) {
            incorrecte.setText("El nombre de usuario ya existe");

        } else if (!v.validarPasswordRegistro(contra, segundaContra)) {
            incorrecte.setText("Las contraseñas no coinciden");

        } else {
            int i = um.addUsuario(new Usuario(nombreUsuario, contra, mail, name, apell, 0, genero));

            if (i != 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Se ha registrado correctamente");
                a.showAndWait();

                nombre.setText("");
                apellidos.setText("");
                email.setText("");
                contraseña.setText("");
                confcontraseña.setText("");
                sexo.getSelectedToggle().setSelected(false);
                nomuser.setText("");

            }
        }
    }

    @FXML
    public void inicioSesion(ActionEvent actionEvent) throws SQLException {

        UsuarioModel um = new UsuarioModel();

        Validador v = new Validador();

        HashMap<Integer, Usuario> usuarios = um.recuperarUsuarios();

        if (!v.comprobarInicioSesion(nombreiniciar.getText(), passwordInicio.getText(), usuarios)) {
            incorrecte2.setText("Datos introducidos incorrectos");

        } else {

            Usuario usuarioConectado = null;

            for (Usuario u : usuarios.values()) {

                if (u.getNombreUsuario().equals(nombreiniciar.getText())) {
                    usuarioConectado = u;
                }
            }

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("Inicio de sesión correcto");
            a.showAndWait();

            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaPrincipal.fxml"));

                Parent root = loader.load();

                VistaPrincipalController controller = loader.getController();
                controller.setUsuario(usuarioConectado);

                Scene scene = new Scene(root);

                Stage stage = (Stage) buttonInicioSesion.getScene().getWindow();

                stage.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
package com.example.prueba3000.controllers;

import com.example.prueba3000.model.Usuario;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AjustesController {
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
    @javafx.fxml.FXML
    private Button buttonCambiarFotoPerfil;
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;

        labelNombre.setText(usuario.getNombre());
        labelApellidos.setText(usuario.getApellidos());
        labelEmail.setText(usuario.getEmail());
        labelPassword.setText(usuario.getPassword());
        labelNombreUsuario.setText(usuario.getNombreUsuario());
    }

    @javafx.fxml.FXML
    public void cambiarFotoPerfil(ActionEvent actionEvent) {
    }
}

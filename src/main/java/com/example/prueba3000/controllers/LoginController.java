package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Usuario;
import model.UsuarioModel;

import java.io.IOException;
import java.sql.*;

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
    private Button boto;
    @FXML
    private Button iniciosesion;
    @FXML
    private TextField nombreiniciar;
    @FXML
    private TextField contraseña1;
    @FXML
    private PasswordField contraseña;
    @FXML
    private AnchorPane cambiarpagina;
    @FXML
    private Label incorrecte2;
    @FXML
    private Label incorrecte1;
    private Usuario usuario;
    @FXML
    private RadioButton masculino;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private RadioButton femenino;

    public Usuario getUsuario() {

        return usuario;
    }

    @FXML
    public void registrarse(ActionEvent actionEvent) throws SQLException {
        UsuarioModel um= new UsuarioModel();
        String sexo="";
        if (masculino.isSelected()){sexo="Masculino";}
        if (femenino.isSelected()){sexo="Femenino";}

        int comprovarusuari= um.comprovarusuario(nomuser.getText());
        if (comprovarusuari==0){
            System.out.println(contraseña.getText()+"   "+confcontraseña.getText());

            if(contraseña.getText().equals(confcontraseña.getText())){
                Usuario u=new Usuario(nomuser.getText(),contraseña.getText(),email.getText(),nombre.getText(),apellidos.getText(),0,sexo);
                System.out.println(sexo
                );
                um.añadirUsuario(u);
                incorrecte.setText("");
            }
            else{
                incorrecte.setText("Las contraseñas no coinciden");
            }

        }
        else{
            incorrecte.setText("El nombre de usuario esta en uso");
        }
    }

    @FXML
    public void iniciosesion(ActionEvent actionEvent) throws SQLException {
        UsuarioModel us= new UsuarioModel();
        int comprovar= us.comprovarusuario(nombreiniciar.getText());
        String contraseña = us.comprovarcontraseña(nombreiniciar.getText());

        if(comprovar==1){
            if(contraseña1.getText().equals(contraseña)){
                //ASO ES PA CARREGAR ELS AMICS
                UsuarioModel um= new UsuarioModel();
                usuario= um.recuperarusuario(nombreiniciar.getText());

                try {
                    Parent root = FXMLLoader.load(Main.class.getResource("vistas/VistaPrincipal.fxml"));

                    Scene scene = new Scene(root);

                    Stage stage = (Stage) iniciosesion.getScene().getWindow();

                    stage.setScene(scene);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                incorrecte2.setText("Contraseña incorrecta");
            }
        }
        else {
            incorrecte2.setText("usuario no registrado");
        }

    }

}
package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class VistaAmigosController implements Initializable {



    @javafx.fxml.FXML
    private VBox vbox_users;
    @javafx.fxml.FXML
    private ImageView imagenperfil;
    @javafx.fxml.FXML
    private Label nombre_usuario;
    @javafx.fxml.FXML
    private Label correo;
    @javafx.fxml.FXML
    private Label nombre;
    @javafx.fxml.FXML
    private Label apellidos;
    private Usuario usuario;
    @javafx.fxml.FXML
    private Label nombre_usuario1;
    @javafx.fxml.FXML
    private Label email;
    @javafx.fxml.FXML
    private ImageView botoneliminar;
    @javafx.fxml.FXML
    private ImageView fotohombre;
    @javafx.fxml.FXML
    private ImageView fotomujer;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario u) {

        this.usuario = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HashMap<Integer,Usuario> usuariosHashmap = new HashMap<>();
        UsuarioModel um= new UsuarioModel();
        try {
            usuariosHashmap.putAll(um.recuperarUsuarios());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        AmigosModel am= new AmigosModel();

        ArrayList<Usuario> amigos= new ArrayList<>();
        try {
            amigos.addAll(am.recuperarAmigos(getUsuario(), usuariosHashmap));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(amigos.size());
        for(Usuario u:amigos){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("vistas/AmigosItem.fxml"));
            try {
                HBox hBox=fxmlLoader.load();
                AmigosItemController aic=fxmlLoader.getController();
                aic.setData(u.getNombreUsuario(),u.getEmail(),u.getSexo());
                vbox_users.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i=0;i< amigos.size();i++){

        }

    }
    @Deprecated
    public void añadiramigo(ActionEvent actionEvent) {

    }

    @Deprecated
    public void aceptar(ActionEvent actionEvent) {
    }

    @Deprecated
    public void rechazar(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void eliminar_amigo(Event event) {
    }
}
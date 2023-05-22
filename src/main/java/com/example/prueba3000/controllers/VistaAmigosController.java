package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;
import com.example.prueba3000.util.MyListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private Usuario usuario;

    private ArrayList<Usuario> amigos=new ArrayList<>();
    @javafx.fxml.FXML
    private VBox VBoxInfoselccionat;
    @javafx.fxml.FXML
    private Pane paneAmigoSeleccionado;
    @javafx.fxml.FXML
    private Label Mostar_username;
    @javafx.fxml.FXML
    private Label Mostrar_Nombre;
    @javafx.fxml.FXML
    private Label Mostrar_Apellidos;
    @javafx.fxml.FXML
    private Label Mostrar_email;
    @javafx.fxml.FXML
    private ImageView boton_eliminar;
    private MyListener myListener;
    @javafx.fxml.FXML
    private ImageView fotohombre;
    @javafx.fxml.FXML
    private ImageView fotomujer;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario u) throws SQLException {

        this.usuario = u;


        UsuarioModel um= new UsuarioModel();
        AmigosModel am= new AmigosModel();

        HashMap<Integer,Usuario> usuariosHashmap = new HashMap<>();
        ArrayList<Usuario> amigoss= new ArrayList<>();

        usuariosHashmap.putAll(um.recuperarUsuarios());
        amigoss.addAll(am.recuperarAmigos(u, usuariosHashmap));

        this.amigos.addAll(amigoss);
        myListener = new MyListener() {
            @Override
            public void onClickListener(Apunte apunte) {

            }

            @Override
            public void onclicklistener(Usuario Usuario) throws SQLException {
                set_datos(Usuario,u);
            }
        };
        for(Usuario user:this.amigos){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("vistas/AmigosItem.fxml"));
            try {
                HBox hBox=fxmlLoader.load();
                AmigosItemController aic=fxmlLoader.getController();
                aic.setData(user,myListener);
                vbox_users.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    paneAmigoSeleccionado.setVisible(false);

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

    public void set_datos(Usuario usuario,Usuario userconect) throws SQLException {
        paneAmigoSeleccionado.setVisible(true);

           if (usuario.getSexo().equals("M")) {
               fotohombre.setVisible(true);
               fotomujer.setVisible(false);

           } else if (usuario.getSexo().equals("F")) {
               fotomujer.setVisible(true);
               fotohombre.setVisible(false);
           }

        Mostar_username.setText(usuario.getNombreUsuario());
        Mostrar_Nombre.setText(usuario.getNombre());
        Mostrar_Apellidos.setText(usuario.getApellidos());
        Mostrar_email.setText(usuario.getEmail());
        if(boton_eliminar.isPressed()){
            Eliminar_amigo(usuario,userconect);
        }
    }



    public void Eliminar_amigo(Usuario userselect, Usuario userconect) throws SQLException {
        AmigosModel am= new AmigosModel();
        am.eliminaramigo(userconect,userselect );
    }

    @javafx.fxml.FXML
    public void eliminar_amigo(Event event) {
    }
}
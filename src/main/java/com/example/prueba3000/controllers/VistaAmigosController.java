package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;
import com.example.prueba3000.util.MyListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Usuario usuarioaeliminar;

    private ArrayList<Usuario> amigos=new ArrayList<>();
    @javafx.fxml.FXML
    private VBox VBoxInfoselccionat;
    @javafx.fxml.FXML
    private Pane paneAmigoSeleccionado;
    @javafx.fxml.FXML
    private ImageView boton_eliminar;
    private MyListener myListener;
    @javafx.fxml.FXML
    private ImageView fotohombre;
    @javafx.fxml.FXML
    private ImageView fotomujer;
    @javafx.fxml.FXML
    private TextField Mostar_username;
    @javafx.fxml.FXML
    private TextField Mostrar_Nombre;
    @javafx.fxml.FXML
    private TextField Mostrar_Apellidos;
    @javafx.fxml.FXML
    private TextField Mostrar_email;
    @javafx.fxml.FXML
    private TextField usernameAmigoAñadir;
    @javafx.fxml.FXML
    private ImageView fotomujer1;
    @javafx.fxml.FXML
    private ImageView fotohombre1;
    @javafx.fxml.FXML
    private Label nomuserMostrarAñadir;
    @javafx.fxml.FXML
    private ImageView botonañadir;
    @javafx.fxml.FXML
    private ImageView botonAñadido;
    @javafx.fxml.FXML
    private Pane paneAmigobuscado;
    @javafx.fxml.FXML
    private ImageView buscador;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario u) throws SQLException {
        paneAmigobuscado.setVisible(false);
        botonAñadido.setVisible(false);

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
                set_datos(Usuario);
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

    public void set_datos(Usuario usuario) throws SQLException {
       this.usuarioaeliminar=usuario;
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



    }





    @javafx.fxml.FXML
    public void eliminarAmigo(Event event) throws SQLException {
        AmigosModel am= new AmigosModel();
        am.eliminaramigo(this.usuario,this.usuarioaeliminar );
        System.out.println("qq");
    }

    @javafx.fxml.FXML
    public void buscar_usuario(Event event) {

        usernameAmigoAñadir.getText();
        paneAmigobuscado.setVisible(true);
    }

    @javafx.fxml.FXML
    public void Añadir_usuario(Event event) {
        botonañadir.setVisible(false);
        botonAñadido.setVisible(true);
    }
}
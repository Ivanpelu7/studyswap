package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.AmigosModel;
import com.example.prueba3000.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private HBox hbox_info;
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

private ArrayList<Usuario> amigos = new ArrayList<>();
public void setUsuarios_usuario(Usuario usuario, HashMap<Integer, Usuario> usuarios) throws SQLException {
    AmigosModel am = new AmigosModel();
    amigos.addAll(am.recuperarAmigos(usuario, usuarios));
    System.out.println(this.amigos.size());
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Usuario> amigouser = new ArrayList<>();
        amigouser.addAll(this.amigos);
        System.out.println(amigouser.size());
        for (int i=0;i< amigos.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("vistas/AmigosItem.fxml"));
            try {
                HBox hBox=fxmlLoader.load();
                AmigosItemController aic=new AmigosItemController();
                aic.setData(amigos.get(i));
                hbox_info.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    public void datosusuarioregistrado(Usuario user){
        if(user.getSexo().equals("F")){
            imagenperfil.setImage(new Image(getClass().getResourceAsStream("src/main/resources/com/example/prueba3000/images/mujer.png")));
        }
        if(user.getSexo().equals("M")){
            imagenperfil.setImage(new Image(getClass().getResourceAsStream("src/main/resources/com/example/prueba3000/images/hombre.png")));
        }
        nombre_usuario.setText(user.getNombreUsuario());
        nombre.setText(user.getNombre());
        apellidos.setText(user.getApellidos());
        correo.setText(user.getApellidos());

    }






}
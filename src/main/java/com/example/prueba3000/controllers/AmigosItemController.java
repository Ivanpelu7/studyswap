package com.example.prueba3000.controllers;

import com.example.prueba3000.model.Usuario;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class AmigosItemController implements Initializable {
    @javafx.fxml.FXML
    private ImageView foto;
    @javafx.fxml.FXML
    private Label nombre_usuario;
    @javafx.fxml.FXML
    private Label email;
    @javafx.fxml.FXML
    private ImageView botoneliminar;
    private Usuario usuario;

    @javafx.fxml.FXML
    public void eliminar_amigo(Event event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

   public void setData(Usuario user){
        if(user.getSexo().equals("F")){
            foto.setImage(new Image(getClass().getResourceAsStream("src/main/resources/com/example/prueba3000/images/mujer.png")));
        }
       if(user.getSexo().equals("M")){
           foto.setImage(new Image(getClass().getResourceAsStream("src/main/resources/com/example/prueba3000/images/hombre.png")));
       }
       nombre_usuario.setText(user.getNombreUsuario());
       email.setText(user.getEmail());
   }
}

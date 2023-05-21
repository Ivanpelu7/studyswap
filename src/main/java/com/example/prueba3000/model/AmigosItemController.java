package com.example.prueba3000.model;

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
    private Label nombre_usuario;
    @javafx.fxml.FXML
    private Label email;
    @javafx.fxml.FXML
    private ImageView botoneliminar;
    private Usuario usuario;
    @javafx.fxml.FXML
    private ImageView fotohombre;
    @javafx.fxml.FXML
    private ImageView fotomujer;

    @javafx.fxml.FXML
    public void eliminar_amigo(Event event) {
        System.out.println("aaaaaaaaaaaaaaaa");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

   public void setData(String nombreusuario, String Email,String sexo){
        System.out.println(nombreusuario);
       if (sexo.equals("M")) {
           fotohombre.setVisible(true);
           fotomujer.setVisible(false);

       } else if (sexo.equals("F")) {
           fotomujer.setVisible(true);
           fotohombre.setVisible(false);
       }
       nombre_usuario.setText(nombreusuario);
       email.setText(Email);
   }
}

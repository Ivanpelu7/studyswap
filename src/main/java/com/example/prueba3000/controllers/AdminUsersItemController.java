package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.model.UsuarioModel;
import com.example.prueba3000.util.MyListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.sql.SQLException;

public class AdminUsersItemController {
    @javafx.fxml.FXML
    private Label nombre_usuario;
    @javafx.fxml.FXML
    private Label email;
    private Usuario usuario;
    @javafx.fxml.FXML
    private ImageView fotohombre;
    @javafx.fxml.FXML
    private HBox seleccionado;
    private MyListener myslistener;
    @javafx.fxml.FXML
    private ImageView fotomujer;
    @javafx.fxml.FXML
    private Button eliminar;
    @javafx.fxml.FXML
    private Circle circuloImagen;

    @Deprecated
    public void setData(Usuario user) throws SQLException {
        this.usuario = user;

        if (user.getFotoPerfil()==null) {
            circuloImagen.setVisible(false);
            if (user.getSexo().equals("M")) {
                fotohombre.setVisible(true);
                fotomujer.setVisible(false);
            }
            if (user.getSexo().equals("F")) {
                fotomujer.setVisible(true);
                fotohombre.setVisible(false);

            }

        }
        else {
            fotomujer.setVisible(false);
            fotohombre.setVisible(false);
            circuloImagen.setFill(new ImagePattern(user.getFotoPerfil()));
        }
        nombre_usuario.setText(user.getNombreUsuario());
        email.setText(user.getEmail());


    }

    @javafx.fxml.FXML
    public void click(Event event) {
    }

    @javafx.fxml.FXML
    public void eliminar_usuario(ActionEvent actionEvent) throws SQLException, IOException {
        UsuarioModel um= new UsuarioModel();
        Alert a= new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("¿Estas seguro que quieres eliminar este usuario?");
        a.showAndWait();


        if(a.getResult() == ButtonType.OK){
            um.eliminarUsuario(this.usuario);

        }
    }
}

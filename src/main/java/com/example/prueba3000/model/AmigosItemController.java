package com.example.prueba3000.model;

import com.example.prueba3000.controllers.VistaAmigosController;
import com.example.prueba3000.util.MyListener;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

public class AmigosItemController {
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
    private void click() throws SQLException {
        myslistener.onclicklistener(usuario);
    }

    public void setData(Usuario user, MyListener myslistener) throws SQLException {
        this.usuario = user;
        this.myslistener = myslistener;
        System.out.println(user.getNombreUsuario());
        if (user.getSexo().equals("M")) {
            fotohombre.setVisible(true);
            fotomujer.setVisible(false);

        } else if (user.getSexo().equals("F")) {
            fotomujer.setVisible(true);
            fotohombre.setVisible(false);
        }
        nombre_usuario.setText(user.getNombreUsuario());
        email.setText(user.getEmail());


    }
}

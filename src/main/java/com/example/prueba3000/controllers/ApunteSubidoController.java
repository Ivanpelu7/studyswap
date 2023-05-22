package com.example.prueba3000.controllers;

import com.example.prueba3000.model.Apunte;
import javafx.scene.control.Label;


public class ApunteSubidoController {

    @javafx.fxml.FXML
    private Label labelNombreUsuario;
    private Apunte apunte;

    public void setApunteSubido(Apunte a) {

        apunte = a;

        labelNombreUsuario.setText(apunte.getNombre());
    }
}

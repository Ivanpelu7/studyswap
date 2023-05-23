package com.example.prueba3000.controllers;

import com.example.prueba3000.model.Apunte;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ItemDescargadoController {

    @javafx.fxml.FXML
    private AnchorPane anchorPanePDF;
    @javafx.fxml.FXML
    private Label labelNombreUsuario;
    private Apunte apunte;

    public void setApunteDescargado(Apunte a) {

        apunte = a;

        labelNombreUsuario.setText(apunte.getNombre());
    }
}

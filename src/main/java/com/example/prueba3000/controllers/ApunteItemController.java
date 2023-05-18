package com.example.prueba3000.controllers;

import com.example.prueba3000.model.Apunte;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ApunteItemController {

    @javafx.fxml.FXML
    private AnchorPane anchorPanePDF;
    @javafx.fxml.FXML
    private Label labelNombreUsuario;

    public void setApunte(Apunte apunte) {

        labelNombreUsuario.setText(apunte.getNombre());
    }
}

package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApunteItemController {

    @javafx.fxml.FXML
    private AnchorPane anchorPanePDF;
    @javafx.fxml.FXML
    private Label labelNombreUsuario;
    private Apunte apunte;

    public void setApunte(Apunte a) {

        this.apunte = a;

        labelNombreUsuario.setText(apunte.getNombre());
    }

    @javafx.fxml.FXML
    public void seleccionarApunte(Event event) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaApuntes.fxml"));
        loader.load();
        VistaApuntesController vac = loader.getController();

        vac.setApunte(this.apunte);
    }

}

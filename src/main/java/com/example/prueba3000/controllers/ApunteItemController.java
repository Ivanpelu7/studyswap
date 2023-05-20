package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.util.MyListener;
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
    private MyListener myListener;

    public void setApunte(Apunte a, MyListener myListener) {

        this.apunte = a;

        this.myListener = myListener;

        labelNombreUsuario.setText(apunte.getNombre());
    }

    @javafx.fxml.FXML
    public void clickApunte(Event event) {

        myListener.onClickListener(apunte);
    }
}

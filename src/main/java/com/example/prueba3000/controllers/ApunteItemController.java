package com.example.prueba3000.controllers;

import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.util.MyListener;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ApunteItemController implements Initializable {

    @javafx.fxml.FXML
    private AnchorPane anchorPanePDF;

    @javafx.fxml.FXML
    private Label labelNombreUsuario;
    private Apunte apunte;
    private MyListener myListener;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setApunte(Apunte a, MyListener myListener) throws SQLException, IOException {

        this.apunte = a;

        this.myListener = myListener;

        labelNombreUsuario.setText(apunte.getNombre());
    }

    @javafx.fxml.FXML
    public void clickApunte(Event event) {

        myListener.onClickListener(apunte);
    }


}

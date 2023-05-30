package com.example.prueba3000.controllers;

import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.util.MyListener;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    @javafx.fxml.FXML
    private ImageView Estrella0;
    @javafx.fxml.FXML
    private ImageView Estrella1;
    @javafx.fxml.FXML
    private ImageView Estrella2;
    @javafx.fxml.FXML
    private ImageView Estrella3;
    @javafx.fxml.FXML
    private ImageView Estrella4;
    @javafx.fxml.FXML
    private ImageView Estrella5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setApunte(Apunte a, MyListener myListener) throws SQLException, IOException {

        this.apunte = a;

        this.myListener = myListener;

        labelNombreUsuario.setText(apunte.getNombre());
        if(apunte.getPuntuacion()==0){
            Estrella0.setVisible(true);
        }
        if(apunte.getPuntuacion()==1){
            Estrella1.setVisible(true);
        }
        if(apunte.getPuntuacion()==2){
            Estrella2.setVisible(true);
        }
        if(apunte.getPuntuacion()==3){
            Estrella3.setVisible(true);
        }
        if(apunte.getPuntuacion()==4){
            Estrella4.setVisible(true);
        }
        if(apunte.getPuntuacion()==5){
            Estrella5.setVisible(true);
        }
    }

    @javafx.fxml.FXML
    public void clickApunte(Event event) {

        myListener.onClickListener(apunte);
    }


}

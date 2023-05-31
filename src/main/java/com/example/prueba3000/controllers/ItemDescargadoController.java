package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ItemDescargadoController {

    @javafx.fxml.FXML
    private AnchorPane anchorPanePDF;
    @javafx.fxml.FXML
    private Label labelNombreUsuario;
    private Apunte apunte;
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

    public void setApunteDescargado(Apunte a) {

        apunte = a;

        labelNombreUsuario.setText(apunte.getNombre());

        if(apunte.getPuntuacion()==0){
            Estrella0.setVisible(true);
            Estrella1.setVisible(false);
            Estrella2.setVisible(false);
            Estrella3.setVisible(false);
            Estrella4.setVisible(false);
            Estrella5.setVisible(false);
        }
        if(apunte.getPuntuacion()==1){
            Estrella0.setVisible(false);
            Estrella1.setVisible(true);
            Estrella2.setVisible(false);
            Estrella3.setVisible(false);
            Estrella4.setVisible(false);
            Estrella5.setVisible(false);
        }
        if(apunte.getPuntuacion()==2){
            Estrella0.setVisible(false);
            Estrella1.setVisible(false);
            Estrella2.setVisible(true);
            Estrella3.setVisible(false);
            Estrella4.setVisible(false);
            Estrella5.setVisible(false);
        }
        if(apunte.getPuntuacion()==3){
            Estrella0.setVisible(false);
            Estrella1.setVisible(false);
            Estrella2.setVisible(false);
            Estrella3.setVisible(true);
            Estrella4.setVisible(false);
            Estrella5.setVisible(false);
        }
        if(apunte.getPuntuacion()==4){
            Estrella0.setVisible(false);
            Estrella1.setVisible(false);
            Estrella2.setVisible(false);
            Estrella3.setVisible(false);
            Estrella4.setVisible(true);
            Estrella5.setVisible(false);
        }
        if(apunte.getPuntuacion()==5){
            Estrella0.setVisible(false);
            Estrella1.setVisible(false);
            Estrella2.setVisible(false);
            Estrella3.setVisible(false);
            Estrella4.setVisible(false);
            Estrella5.setVisible(true);
        }
    }

    @FXML
    public void seleccionarApunte(Event event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/ApunteInformacion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Apunte Descargado");
        stage.setResizable(false);
        stage.getIcons().setAll(new Image(Main.class.getResourceAsStream("images/icono.png")));
        stage.initOwner(labelNombreUsuario.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

        ApunteInformacionController aic = loader.getController();
        aic.setDatosDescargados(this.apunte);
    }
}

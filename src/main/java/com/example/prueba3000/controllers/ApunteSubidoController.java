package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.model.ApunteModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.event.Event;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


public class ApunteSubidoController {

    @javafx.fxml.FXML
    private Label labelNombreUsuario;
    private Apunte apunte;
    @javafx.fxml.FXML
    private AnchorPane anchorPanePDF;

    public void setApunteSubido(Apunte a) {

        apunte = a;

        labelNombreUsuario.setText(apunte.getNombre());
    }

    @javafx.fxml.FXML
    public void seleccionarApunte(Event event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/ApunteInformacion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Apunte Subido");
        stage.getIcons().setAll(new Image(Main.class.getResourceAsStream("images/icono.png")));
        stage.show();

        ApunteInformacionController aic = loader.getController();

        HashMap<Integer, Apunte> apuntes = new ApunteModel().recuperarApuntes();

        int punt = apuntes.get(this.apunte.getId()).getPuntuacion();
        this.apunte.setPuntuacion(punt);

        aic.setDatos(this.apunte);
    }
}

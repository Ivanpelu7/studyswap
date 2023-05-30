package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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

    @javafx.fxml.FXML
    public void seleccionarApunte(Event event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/ApunteInformacion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Apunte Descargado");
        stage.getIcons().setAll(new Image(Main.class.getResourceAsStream("images/icono.png")));
        stage.show();

        ApunteInformacionController aic = loader.getController();
        aic.setDatosDescargados(this.apunte);
    }
}

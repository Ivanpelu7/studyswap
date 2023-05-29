package com.example.prueba3000.controllers;

import com.example.prueba3000.model.*;
import com.example.prueba3000.util.UsuarioHolder;
import com.example.prueba3000.util.Validador;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReporteController implements Initializable {


    @javafx.fxml.FXML
    private Button buttonEnviarReporte;
    @javafx.fxml.FXML
    private TextArea mensajeReporte;
    private Apunte apunte;
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.usuario = UsuarioHolder.getUsuario();
    }

    public void setApunte(Apunte apunte) {

        this.apunte = apunte;
    }


    @javafx.fxml.FXML
    public void enviarReporte(ActionEvent actionEvent) throws SQLException {

        String mensaje = mensajeReporte.getText();

        Reporte reporte = new Reporte(this.apunte, this.usuario, mensaje);

        ReporteModel reporteModel = new ReporteModel();
        reporteModel.insertarReporte(reporte);

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText(null);
        a.setContentText("Confirmar reporte");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK) {
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("Apunte reportado correctamente");
            a.showAndWait();

            Stage esteStage = (Stage) buttonEnviarReporte.getScene().getWindow();
            esteStage.close();
        }
    }
}


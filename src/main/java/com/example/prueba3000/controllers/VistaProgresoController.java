package com.example.prueba3000.controllers;

import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VistaProgresoController implements Initializable {

    @javafx.fxml.FXML
    private ProgressBar progressBar;
    @javafx.fxml.FXML
    private ProgressIndicator progressIndicator;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Progreso progreso = new Progreso();
        progressBar.progressProperty().bind(progreso.progressProperty());
        progressIndicator.progressProperty().bind(progreso.progressProperty());
        new Thread(progreso).start();
    }

    private class Progreso extends Task<Integer> {

        @Override
        protected void succeeded() {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("Proceso finalizado correctamente");
            a.show();

            Stage stage = (Stage) progressBar.getScene().getWindow();
            stage.close();
        }

        @Override
        protected Integer call() throws Exception {

            for (int i = 1; i <= 100; i++) {
                updateProgress(i, 100);
                Thread.sleep(40);
            }

            return 0;
        }
    }
}

package com.example.prueba3000.controllers;

import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.model.ApunteModel;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.util.UsuarioHolder;
import com.example.prueba3000.util.Validador;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ApunteInformacionController implements Initializable {
    @javafx.fxml.FXML
    private TextField textFieldCurso;
    @javafx.fxml.FXML
    private Label labelNombreApunte;
    private Apunte apunte;
    @javafx.fxml.FXML
    private TextField textFieldAsig;
    @javafx.fxml.FXML
    private TextField textFieldAutor;
    @javafx.fxml.FXML
    private Button buttonEliminar;
    @javafx.fxml.FXML
    private Button buttonReportar;
    @javafx.fxml.FXML
    private Button buttonCalificar;
    @javafx.fxml.FXML
    private Rating puntuacion;
    @javafx.fxml.FXML
    private Label labelTruco;
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.usuario = UsuarioHolder.getUsuario();
    }

    public void setDatos(Apunte apunte) {

        this.apunte = apunte;

        buttonCalificar.setVisible(false);
        buttonReportar.setVisible(false);

        puntuacion.setRating(this.apunte.getPuntuacion());
        labelNombreApunte.setText(apunte.getNombre());
        textFieldCurso.setText(apunte.getCurso().getNombre());
        textFieldAsig.setText(apunte.getAsignatura().getNombre());
        textFieldAutor.setText(apunte.getAutor().getNombreUsuario());
    }

    public void setDatosDescargados(Apunte apunte) {

        buttonCalificar.setVisible(true);
        buttonEliminar.setVisible(false);
        buttonCalificar.setVisible(true);
        labelTruco.setDisable(true);

        this.apunte = apunte;

        puntuacion.setRating(0);
        labelNombreApunte.setText(apunte.getNombre());
        textFieldCurso.setText(apunte.getCurso().getNombre());
        textFieldAsig.setText(apunte.getAsignatura().getNombre());
        textFieldAutor.setText(apunte.getAutor().getNombreUsuario());
    }

    @javafx.fxml.FXML
    public void eliminarApunte(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void reportarApunte(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void calificarApunte(ActionEvent actionEvent) throws SQLException {

        Validador v = new Validador();

        if (!v.validarCalificacion(this.usuario, this.apunte)) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Ya has calificado este apunte anteriormente");
            a.showAndWait();

        } else {
            ApunteModel apunteModel = new ApunteModel();

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText(null);
            a.setContentText("Confirmar puntuación");
            a.showAndWait();

            if (a.getResult() == ButtonType.OK) {
                apunteModel.calificarApunte(this.usuario, this.apunte, (int) puntuacion.getRating());

                a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Apunte calificado correctamente");
                a.showAndWait();
            }
        }



    }


}

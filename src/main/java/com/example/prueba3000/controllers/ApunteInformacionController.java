package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.model.ApunteModel;
import com.example.prueba3000.model.Usuario;
import com.example.prueba3000.util.UsuarioHolder;
import com.example.prueba3000.util.Validador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
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
    @FXML
    private TextField textFieldNumeroDescargas;
    @FXML
    private Label labelNumeroDescargas;
    @FXML
    private Label apunteEliminado;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.usuario = UsuarioHolder.getUsuario();
    }

    public void setDatos(Apunte apunte) throws SQLException {

        this.apunte = apunte;

        buttonCalificar.setVisible(false);
        buttonReportar.setVisible(false);



        int numDescargas = new ApunteModel().numeroDescargas(this.apunte);
        textFieldNumeroDescargas.setText(String.valueOf(numDescargas));
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
        textFieldNumeroDescargas.setVisible(false);
        labelNumeroDescargas.setVisible(false);

        this.apunte = apunte;

        if(apunte.getEliminado()==1){
            apunteEliminado.setVisible(true);
            buttonReportar.setDisable(true);
            buttonCalificar.setDisable(true);
        }
        else{
            apunteEliminado.setVisible(false);
        }
        puntuacion.setRating(0);
        labelNombreApunte.setText(apunte.getNombre());
        textFieldCurso.setText(apunte.getCurso().getNombre());
        textFieldAsig.setText(apunte.getAsignatura().getNombre());
        textFieldAutor.setText(apunte.getAutor().getNombreUsuario());
    }

    @javafx.fxml.FXML
    public void eliminarApunte(ActionEvent actionEvent) throws SQLException {
        ApunteModel am= new ApunteModel();

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("¿Estas seguro de que quieres eliminar el archivo?");
        a.showAndWait();
        if(a.getResult()==ButtonType.OK){
            am.eliminarApunte(this.apunte);

            Stage stage = (Stage) buttonEliminar.getScene().getWindow();
            stage.close();
        }
    }

    @javafx.fxml.FXML
    public void reportarApunte(ActionEvent actionEvent) throws IOException, SQLException {

        Validador v = new Validador();

        if (!v.validarReporte(this.usuario, this.apunte)) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Ya has reportado este apunte anteriormente");
            a.showAndWait();

        } else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/Reporte.fxml"));
            Parent root = loader.load();

            ReporteController reporteController = loader.getController();
            reporteController.setApunte(this.apunte);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Reporte");
            stage.setResizable(false);
            stage.getIcons().setAll(new Image(Main.class.getResourceAsStream("images/icono.png")));
            stage.initOwner(labelNombreApunte.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        }
    }

    @javafx.fxml.FXML
    public void calificarApunte(ActionEvent actionEvent) throws SQLException, IOException {

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

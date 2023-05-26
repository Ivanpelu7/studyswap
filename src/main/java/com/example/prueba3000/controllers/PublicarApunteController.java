package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;
import com.example.prueba3000.util.UsuarioHolder;
import com.example.prueba3000.util.Validador;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PublicarApunteController implements Initializable {

    @javafx.fxml.FXML
    private ComboBox comboboxCursos;
    @javafx.fxml.FXML
    private ComboBox comboboxAsignatura;
    @javafx.fxml.FXML
    private TextField textFieldNombreApunte;
    @javafx.fxml.FXML
    private TextField textFieldRuta;
    @javafx.fxml.FXML
    private Button buttonSeleccionarPDF;
    @javafx.fxml.FXML
    private Button buttonPublicar;
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.usuario = UsuarioHolder.getUsuario();

        CursoModel cm = new CursoModel();
        AsignaturaModel am = new AsignaturaModel();

        try {
            comboboxCursos.setItems(cm.recuperarCursosOL());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void seleccionarCurso(ActionEvent actionEvent) throws SQLException {

        AsignaturaModel am = new AsignaturaModel();

        comboboxAsignatura.setItems(am.asignaturasCurso((Curso) comboboxCursos.getValue()));
        comboboxAsignatura.setDisable(false);
    }

    @javafx.fxml.FXML
    public void seleccionarPDF(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File pdf = fileChooser.showOpenDialog(null);

        if (pdf != null) {
            String absolutPath = pdf.getAbsolutePath();
            textFieldRuta.setText(absolutPath);
        }

    }

    @javafx.fxml.FXML
    public void publicar(ActionEvent actionEvent) throws SQLException, IOException {

        Validador v = new Validador();

        if (!v.validarPublicacion(comboboxAsignatura, comboboxCursos, textFieldRuta, textFieldNombreApunte)) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Tienes que rellenar todos los campos");
            a.showAndWait();

        } else {
            File apunte = new File(textFieldRuta.getText());
            String nombre = textFieldNombreApunte.getText();
            Curso curso = (Curso) comboboxCursos.getValue();
            Asignatura asignatura = (Asignatura) comboboxAsignatura.getValue();
            Usuario usuario = this.usuario;

            Apunte ap = new Apunte(nombre, apunte, asignatura, curso, usuario);

            ApunteModel am = new ApunteModel();
            int i = am.insertarApunte(ap);

            if (i != 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setContentText("Publicación realizada correctamente");
                a.showAndWait();
                a.getResult();



                Stage stage = (Stage) buttonPublicar.getScene().getWindow();
                stage.close();
                FXMLLoader amigos = new FXMLLoader(Main.class.getResource("vistas/VistaPerfil.fxml"));
                Parent root = amigos.load();
                VistaPerfilController controller= amigos.getController();
                controller.CargarVista();



            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText("No se ha podido realizar la publicación");
                a.showAndWait();
            }
        }
    }
}

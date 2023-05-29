package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;
import com.example.prueba3000.util.MyListener;
import com.example.prueba3000.util.UsuarioHolder;
import com.example.prueba3000.util.Validador;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class VistaApuntesController implements Initializable {
    @javafx.fxml.FXML
    private ComboBox comboboxCurso;
    @javafx.fxml.FXML
    private ComboBox comboboxAsignatura;
    private HashMap<Integer, Apunte> apuntesHM;
    @javafx.fxml.FXML
    private Button botonFiltrar;
    @javafx.fxml.FXML
    private ImageView imgPDF;
    @javafx.fxml.FXML
    private TextField textFieldCurso;
    @javafx.fxml.FXML
    private Label labelCurso;
    @javafx.fxml.FXML
    private TextField textFieldAutor;
    @javafx.fxml.FXML
    private TextField textFieldAsignatura;
    @javafx.fxml.FXML
    private GridPane mainGridPane;
    private MyListener myListener;
    private Apunte apunte;
    @javafx.fxml.FXML
    private TextField textFieldNombre;
    private Usuario usuario;
    @javafx.fxml.FXML
    private Button botonDescargar;
    @javafx.fxml.FXML
    private Rating puntuacionApunte;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.usuario = UsuarioHolder.getUsuario();

        ApunteModel am = new ApunteModel();
        CursoModel cm = new CursoModel();

        myListener = new MyListener() {
            @Override
            public void onClickListener(Apunte apunte) {
                setApunte(apunte);
            }

            @Override
            public void onclicklistener(Usuario Usuario) throws SQLException {

            }

        };

        try {
            comboboxCurso.setItems(cm.recuperarCursosOL());

            apuntesHM = am.recuperarApuntes();

            ArrayList<Apunte> apuntes = new ArrayList<>(apuntesHM.values());

            int column = 0;
            int row = 1;

            for (int i = 0; i < apuntes.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(Main.class.getResource("vistas/ApunteItem.fxml"));

                AnchorPane pane = fxmlLoader.load();

                ApunteItemController aic2 = fxmlLoader.getController();
                aic2.setApunte(apuntes.get(i), myListener);

                if (column == 4) {
                    column = 0;
                    row++;
                }

                mainGridPane.add(pane, column++, row);

                mainGridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                mainGridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                mainGridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);

                mainGridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                mainGridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                mainGridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(pane, new Insets(8));
            }


        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    @javafx.fxml.FXML
    public void filtrarCurso(ActionEvent actionEvent) throws SQLException {

        AsignaturaModel am = new AsignaturaModel();

        comboboxAsignatura.setItems(am.asignaturasCurso((Curso) comboboxCurso.getValue()));
        comboboxAsignatura.setDisable(false);

    }

    @javafx.fxml.FXML
    public void filtrar(ActionEvent actionEvent) throws SQLException, IOException {

        Validador v = new Validador();
        ApunteModel am = new ApunteModel();

        if (!v.validarFiltro(comboboxCurso, comboboxAsignatura)) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Debe seleccionar los filtros");
            a.showAndWait();

        } else {
            ArrayList<Apunte> apuntesF = am.apuntesFiltro((Asignatura) comboboxAsignatura.getValue(), (Curso) comboboxCurso.getValue());

            mainGridPane.getChildren().clear();

            int column = 0;
            int row = 1;

            for (int i = 0; i < apuntesF.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(Main.class.getResource("vistas/ApunteItem.fxml"));

                AnchorPane pane = fxmlLoader.load();

                ApunteItemController aic = fxmlLoader.getController();
                aic.setApunte(apuntesF.get(i), myListener);

                if (column == 4) {
                    column = 0;
                    row++;
                }

                mainGridPane.add(pane, column++, row);

                mainGridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                mainGridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                mainGridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);

                mainGridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                mainGridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                mainGridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(pane, new Insets(8));
            }
        }
    }

    public void setApunte(Apunte apunte) {

        this.apunte = apunte;

        textFieldCurso.setText(apunte.getCurso().getNombre());
        textFieldAsignatura.setText(apunte.getAsignatura().getNombre());
        textFieldAutor.setText(apunte.getAutor().getNombreUsuario());
        textFieldNombre.setText(apunte.getNombre());
        puntuacionApunte.setRating(apunte.getPuntuacion());
    }

    @javafx.fxml.FXML
    public void descargarApunte(Event event) throws SQLException, IOException {

        Validador v = new Validador();

        if (!v.validarDescargarApunte(this.apunte, this.usuario, new ApunteModel())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setContentText("Ya te has descargado este apunte anteriormente");
            a.showAndWait();

        } else {
            Blob pdf = this.apunte.getPdf();
            String nombre = this.apunte.getNombre();

            File temporalFile = File.createTempFile("temp", ".pdf");
            InputStream is = pdf.getBinaryStream();
            OutputStream os = new FileOutputStream(temporalFile);
            byte[] buffer = new byte[8096];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) > 0) {
                os.write(buffer, 0, bytesRead);
            }

            pdf.free();

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName(nombre);

            File archivo = fileChooser.showSaveDialog(null);

            if (archivo != null) {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("vistas/VistaProgreso.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Descarga");
                stage.setScene(scene);
                stage.getIcons().setAll(new Image(Main.class.getResourceAsStream("images/icono.png")));
                stage.show();

                Files.copy(temporalFile.toPath(), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING);

                ApunteModel am = new ApunteModel();
                am.apunteDescargado(this.apunte, this.usuario);
            }

            temporalFile.delete();
        }
    }
}

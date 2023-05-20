package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;
import com.example.prueba3000.util.MyListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
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
    private ImageView botonDescargar;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ApunteModel am = new ApunteModel();
        CursoModel cm = new CursoModel();

        myListener = new MyListener() {
            @Override
            public void onClickListener(Apunte apunte) {
                setApunte(apunte);
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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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

        ApunteModel am = new ApunteModel();

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

    public void setApunte(Apunte apunte) {

        textFieldCurso.setText(apunte.getCurso().getNombre());
        textFieldAsignatura.setText(apunte.getAsignatura().getNombre());
        textFieldAutor.setText(apunte.getAutor().getNombreUsuario());
    }
}

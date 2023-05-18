package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.model.ApunteModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    @javafx.fxml.FXML
    private Label labelNombrePDF;
    @javafx.fxml.FXML
    private ImageView imgPDF;
    @javafx.fxml.FXML
    private ImageView botonDescargar;
    @javafx.fxml.FXML
    private ScrollPane scrollPane;
    @javafx.fxml.FXML
    private GridPane gridPane;
    private HashMap<Integer, Apunte> apuntesHM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ApunteModel am = new ApunteModel();

        try {
            apuntesHM = am.recuperarApuntes();

            ArrayList<Apunte> apuntes = new ArrayList<>(apuntesHM.values());

            int column = 0;
            int row = 0;

            for (int i = 0; i < apuntes.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(Main.class.getResource("vistas/ApunteItem.fxml"));

                AnchorPane pane = fxmlLoader.load();

                ApunteItemController aic = fxmlLoader.getController();
                aic.setApunte(apuntes.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }



                gridPane.add(pane, column++, row);

                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);

                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(pane, new Insets(10));



            }





        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @javafx.fxml.FXML
    public void filtrarCurso(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void filtrarAsignatura(ActionEvent actionEvent) {
    }


}

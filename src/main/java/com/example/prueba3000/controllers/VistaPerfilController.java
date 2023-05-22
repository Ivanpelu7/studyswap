package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.model.ApunteModel;
import com.example.prueba3000.model.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class VistaPerfilController {

    @javafx.fxml.FXML
    private GridPane gridPaneSubidos;
    @javafx.fxml.FXML
    private GridPane gridPaneDescargados;
    private Usuario usuario;
    private FXMLLoader loader;
    AnchorPane pane;

    public void setApuntesSubidos(Usuario usuario) {

        ApunteModel am = new ApunteModel();

        try {
            ArrayList<Apunte> apuntes = am.apuntesSubidos(usuario);

            int column = 0;
            int row = 1;

            for (int i = 0; i < apuntes.size(); i++) {
                loader = new FXMLLoader();

                loader.setLocation(Main.class.getResource("vistas/ItemSubidos.fxml"));

                pane = loader.load();

                ApunteSubidoController aic2 = loader.getController();
                aic2.setApunteSubido(apuntes.get(i));

                if (column == 4) {
                    column = 0;
                    row++;
                }

                gridPaneSubidos.add(pane, column++, row);

                gridPaneSubidos.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPaneSubidos.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPaneSubidos.setMaxWidth(Region.USE_COMPUTED_SIZE);

                gridPaneSubidos.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPaneSubidos.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPaneSubidos.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(pane, new Insets(8));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

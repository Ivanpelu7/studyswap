package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.Apunte;
import com.example.prueba3000.model.ApunteModel;
import com.example.prueba3000.model.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class VistaPerfilController {

    @javafx.fxml.FXML
    public GridPane gridPaneSubidos;
    @javafx.fxml.FXML
    private GridPane gridPaneDescargados;
    private Usuario usuario;
    private FXMLLoader loader;
    AnchorPane pane;
    @javafx.fxml.FXML
    private Button buttonPublicarApunte;
    @javafx.fxml.FXML
    public AnchorPane mainVistaPerfil;

    public void setApuntesSubidos(Usuario usuario) {

        this.usuario = usuario;

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

                if (column == 3) {
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

    public void setApuntesDescargados(Usuario usuario) {

        ApunteModel am = new ApunteModel();

        try {
            ArrayList<Apunte> apuntes = am.apuntesDescargados(usuario);

            int column = 0;
            int row = 1;

            for (int i = 0; i < apuntes.size(); i++) {
                loader = new FXMLLoader();

                loader.setLocation(Main.class.getResource("vistas/ItemDescargado.fxml"));

                pane = loader.load();

                ItemDescargadoController itdc = loader.getController();
                itdc.setApunteDescargado(apuntes.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridPaneDescargados.add(pane, column++, row);

                gridPaneDescargados.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPaneDescargados.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPaneDescargados.setMaxWidth(Region.USE_COMPUTED_SIZE);

                gridPaneDescargados.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPaneDescargados.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPaneDescargados.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(pane, new Insets(8));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void publicarApunte(ActionEvent actionEvent) throws IOException {

        loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("vistas/PublicarApunte.fxml"));
        pane = loader.load();

        PublicarApunteController pac = loader.getController();
        pac.setUsuario(this.usuario);

        Scene scene = new Scene(pane);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Publicar Apunte");
        stage.getIcons().setAll(new Image(Main.class.getResourceAsStream("images/icon.jpg")));
        stage.show();
    }
}

package com.example.prueba3000;

import com.example.prueba3000.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vistas/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("StudySwap");
        stage.getIcons().setAll(new Image(getClass().getResourceAsStream("images/icono.png")));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
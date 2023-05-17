package com.example.prueba3000.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.prueba3000.model.AmigosModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class VistaAmigosController {


    @javafx.fxml.FXML
    private TextField username_amigoaañadir;
    @javafx.fxml.FXML
    private Button añadiramigo;
    @javafx.fxml.FXML
    private Label numeroDeSolicitudes;
    @javafx.fxml.FXML
    private Button aceptar;
    @javafx.fxml.FXML
    private Button rechazar;
    ArrayList<String> amigos = new ArrayList<>();
    String nombreusuario = "";

    ArrayList<String> Solicitudes = new ArrayList<>();
    @javafx.fxml.FXML
    private Label nombresol;


    int numsolicitudes= 0;
    int i = 0;
    int s = 0;

    public void initialize() throws SQLException {

        LoginController lc= new LoginController();

        //cride al com.example.prueba3000.model
        AmigosModel am = new AmigosModel();
        //llamamos a la funcion ver amigos
        //am.veramigos(lc.getUsuario().getNombreUsuario());
        amigos.addAll(am.amigos);
        int i = 0;
        //llamo a la funcion ver solicitudes
        //am.mostrarsolicitudes(lc.getUsuario().getNombreUsuario());
        numsolicitudes= am.solicitudes.size();
        System.out.println(numsolicitudes);
        Solicitudes.addAll(am.solicitudes);
        numeroDeSolicitudes.setText("Tienes " + numsolicitudes + " Solicitudes");
        if(am.idsolicitantes.size()>0){
            nombresol.setText(Solicitudes.get(s));
        }

        if (am.idsolicitantes.size() < 1) {
            aceptar.setDisable(true);
            rechazar.setDisable(true);
            numeroDeSolicitudes.setText("No tienes solicitudes");
            nombresol.setText("");
        }
    }

    @javafx.fxml.FXML
    public void añadiramigo(ActionEvent actionEvent) throws SQLException {
        AmigosModel am = new AmigosModel();
        am.añadiramigo(nombreusuario, username_amigoaañadir.getText());
    }

    @javafx.fxml.FXML

    public void aceptar(ActionEvent actionEvent) throws SQLException {
        AmigosModel am = new AmigosModel();
        System.out.println(numsolicitudes);
        if (numsolicitudes > 0) {

            String emisor = Solicitudes.get(s);
            am.aceptarsolicitud(emisor, nombreusuario);
            s++;
            nombresol.setText(Solicitudes.get(s));
            numsolicitudes--;
            numeroDeSolicitudes.setText("Tienes " + numsolicitudes + " Solicitudes");
            if(numsolicitudes<1) {
                aceptar.setDisable(true);
                rechazar.setDisable(true);
                numeroDeSolicitudes.setText("No tienes solicitudes");
            }
        }
        if(numsolicitudes<1) {
            aceptar.setDisable(true);
            rechazar.setDisable(true);
            numeroDeSolicitudes.setText("No tienes solicitudes");
        }
    }

    @javafx.fxml.FXML
    public void rechazar(ActionEvent actionEvent) throws SQLException {
        AmigosModel am = new AmigosModel();
        if (numsolicitudes > 0) {

            System.out.println(numsolicitudes);
            String emisor = Solicitudes.get(s);
            am.rechazarsolicitud(emisor, nombreusuario);
            s++;
            numsolicitudes--;
            nombresol.setText(Solicitudes.get(s));
            numeroDeSolicitudes.setText("Tienes " +  numsolicitudes + " Solicitudes");
            if(numsolicitudes<1) {
                aceptar.setDisable(true);
                rechazar.setDisable(true);
                numeroDeSolicitudes.setText("No tienes solicitudes");
            }
        }
        if(numsolicitudes<1) {
            aceptar.setDisable(true);
            rechazar.setDisable(true);
            numeroDeSolicitudes.setText("No tienes solicitudes");
        }
    }
}
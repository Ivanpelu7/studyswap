package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;
import com.example.prueba3000.util.MyListener;
import com.example.prueba3000.util.UsuarioHolder;
import com.example.prueba3000.util.Validador;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class VistaAmigosController implements Initializable {


    private Usuario usuario;
    private Usuario usuarioaeliminar;
    private ArrayList<Usuario> amigos = new ArrayList<>();
    @javafx.fxml.FXML
    private Pane paneAmigoSeleccionado;
    private MyListener myListener;
    @javafx.fxml.FXML
    private ImageView fotohombre;
    @javafx.fxml.FXML
    private ImageView fotomujer;
    @javafx.fxml.FXML
    private TextField Mostar_username;
    @javafx.fxml.FXML
    private TextField Mostrar_Nombre;
    @javafx.fxml.FXML
    private TextField Mostrar_Apellidos;
    @javafx.fxml.FXML
    private TextField Mostrar_email;
    @javafx.fxml.FXML
    private TextField usernameAmigoAñadir;
    @javafx.fxml.FXML
    private ImageView fotomujer1;
    @javafx.fxml.FXML
    private ImageView fotohombre1;
    @javafx.fxml.FXML
    private Label nomuserMostrarAñadir;
    @javafx.fxml.FXML
    private ImageView botonAñadido;
    @javafx.fxml.FXML
    private Pane paneAmigobuscado;
    @javafx.fxml.FXML
    private AnchorPane anchor;
    @javafx.fxml.FXML
    private Label userNoExiste;
    @javafx.fxml.FXML
    private ImageView denegada;
    @javafx.fxml.FXML
    private ImageView pendiente;
    @javafx.fxml.FXML
    private ScrollPane scrollpane;
    @javafx.fxml.FXML
    private Pane PaneSolicitudes;
    @javafx.fxml.FXML
    private ImageView botoaceptarsolicitud;
    @javafx.fxml.FXML
    private ImageView fotomujer11;
    @javafx.fxml.FXML
    private ImageView fotohombre11;
    @javafx.fxml.FXML
    private Label nomuserSolicitud;
    ArrayList<SolicitudAmistad> solicitudes = new ArrayList<>();
    @javafx.fxml.FXML
    private Button botonañadir;
    @javafx.fxml.FXML
    private Button buscador;
    @javafx.fxml.FXML
    private Button boton_eliminar;
    @javafx.fxml.FXML
    private GridPane vbox_users;
    @javafx.fxml.FXML
    private Button botodenegarsolicitud;
    @javafx.fxml.FXML
    private Pane noAmigos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.usuario = UsuarioHolder.getUsuario();

        try {
            setSolicitudes(this.usuario);

            noAmigos.setVisible(false);
            paneAmigoSeleccionado.setVisible(false);
            paneAmigobuscado.setVisible(false);
            botonAñadido.setVisible(false);
            botonañadir.setVisible(false);
            denegada.setVisible(false);
            pendiente.setVisible(false);


            UsuarioModel um = new UsuarioModel();
            AmigosModel am = new AmigosModel();

            HashMap<Integer, Usuario> usuariosHashmap = new HashMap<>();
            ArrayList<Usuario> amigoss = new ArrayList<>();

            try {
                usuariosHashmap.putAll(um.recuperarUsuarios());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            amigoss.addAll(am.recuperarAmigos(this.usuario, usuariosHashmap));

            this.amigos.addAll(amigoss);
            myListener = new MyListener() {
                @Override
                public void onClickListener(Apunte apunte) {

                }

                @Override
                public void onclicklistener(Usuario Usuario) throws SQLException {
                    set_datos(Usuario);
                }
            };
            int column = 0;
            int row = 1;
            if (amigos.size() > 0) {
                for (Usuario user : this.amigos) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("vistas/AmigosItem.fxml"));
                    try {
                        HBox hBox = fxmlLoader.load();
                        AmigosItemController aic = fxmlLoader.getController();
                        aic.setData(user, myListener);
                        vbox_users.add(hBox, column, row++);
                        GridPane.setMargin(hBox, new Insets(10));

                        vbox_users.setMinWidth(Region.USE_COMPUTED_SIZE);
                        vbox_users.setPrefWidth(Region.USE_COMPUTED_SIZE);
                        vbox_users.setMaxWidth(Region.USE_COMPUTED_SIZE);

                        vbox_users.setMinHeight(Region.USE_COMPUTED_SIZE);
                        vbox_users.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        vbox_users.setMaxHeight(Region.USE_COMPUTED_SIZE);

                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
            } else {
                noAmigos.setVisible(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    int nsolicitud = 0;

    public void setSolicitudes(Usuario u) throws SQLException {
        SolicitudAmistadModel sam = new SolicitudAmistadModel();
        UsuarioModel um = new UsuarioModel();

        HashMap<Integer, Usuario> usuariosHashmap = new HashMap<>();
        usuariosHashmap.putAll(um.recuperarUsuarios());

        solicitudes.addAll(sam.peticionesAmistad(this.usuario, usuariosHashmap));
        if (this.solicitudes.size() > 0) {
            if (usuario.getSexo().equals("M")) {
                fotohombre11.setVisible(true);
                fotomujer11.setVisible(false);

            } else if (usuario.getSexo().equals("F")) {
                fotomujer11.setVisible(true);
                fotohombre11.setVisible(false);
            }

            SolicitudAmistad s = this.solicitudes.get(nsolicitud);
            nomuserSolicitud.setText(s.getUsuarioEmisor().getNombreUsuario());

        } else {
            PaneSolicitudes.setVisible(false);
        }
    }


    public void set_datos(Usuario usuario) throws SQLException {
        this.usuarioaeliminar = usuario;
        paneAmigoSeleccionado.setVisible(true);

        if (usuario.getSexo().equals("M")) {
            fotohombre.setVisible(true);
            fotomujer.setVisible(false);

        } else if (usuario.getSexo().equals("F")) {
            fotomujer.setVisible(true);
            fotohombre.setVisible(false);
        }

        Mostar_username.setText(usuario.getNombreUsuario());
        Mostrar_Nombre.setText(usuario.getNombre());
        Mostrar_Apellidos.setText(usuario.getApellidos());
        Mostrar_email.setText(usuario.getEmail());


    }


    @javafx.fxml.FXML
    public void eliminarAmigo(Event event) throws SQLException, IOException {
        AmigosModel am = new AmigosModel();

        am.eliminaramigo(this.usuario, this.usuarioaeliminar);

        FXMLLoader amigos = new FXMLLoader(Main.class.getResource("vistas/VistaAmigos.fxml"));

        Parent root = amigos.load();

        anchor.getChildren().setAll(root);
    }

    @javafx.fxml.FXML
    public void buscar_usuario(Event event) throws SQLException {
        paneAmigobuscado.setVisible(false);
        UsuarioModel um = new UsuarioModel();
        AmigosModel am = new AmigosModel();
        Validador v = new Validador();

        HashMap<Integer, Usuario> usuariosHashmap = new HashMap<>();
        usuariosHashmap.putAll(um.recuperarUsuarios());


        if (!v.nombreUsuarioExiste(usernameAmigoAñadir.getText(), usuariosHashmap)) {
            Usuario usuarioaBuscar = um.recuperarUsuario(usernameAmigoAñadir.getText());
            userNoExiste.setText("");
            paneAmigobuscado.setVisible(true);
            boolean sonamigos = am.sonamigos(this.usuario, usuarioaBuscar);

            if (sonamigos == true) {
                botonAñadido.setVisible(true);
                botonañadir.setVisible(false);
                denegada.setVisible(false);
                pendiente.setVisible(false);
            } else {
                boolean comprovar_solicitud_enviada = am.comprovar_solicitud_enviada(this.usuario, usuarioaBuscar);
                if (comprovar_solicitud_enviada == true) {
                    int i = am.comprovar_tipo_solicitud(this.usuario, usuarioaBuscar);

                    if (i == 0) {
                        botonAñadido.setVisible(false);
                        botonañadir.setVisible(false);
                        denegada.setVisible(false);
                        pendiente.setVisible(true);
                    }
                    if (i == 1) {
                        botonAñadido.setVisible(true);
                        botonañadir.setVisible(false);
                        denegada.setVisible(false);
                        pendiente.setVisible(false);
                    }
                    if (i == 2) {
                        botonAñadido.setVisible(false);
                        botonañadir.setVisible(false);
                        denegada.setVisible(true);
                        pendiente.setVisible(false);
                    }
                } else if (comprovar_solicitud_enviada == false) {
                    botonAñadido.setVisible(false);
                    botonañadir.setVisible(true);
                    denegada.setVisible(false);
                    pendiente.setVisible(false);
                }


            }
            if (usuario.getSexo().equals("M")) {
                fotohombre1.setVisible(true);
                fotomujer1.setVisible(false);

            } else if (usuario.getSexo().equals("F")) {
                fotomujer1.setVisible(true);
                fotohombre1.setVisible(false);
            }

            nomuserMostrarAñadir.setText(usuarioaBuscar.getNombreUsuario());

        } else {
            userNoExiste.setText("El usuario no existe");
        }

    }

    @javafx.fxml.FXML
    public void Añadir_usuario(Event event) throws SQLException {
        UsuarioModel um = new UsuarioModel();
        AmigosModel am = new AmigosModel();


        HashMap<Integer, Usuario> usuariosHashmap = new HashMap<>();
        usuariosHashmap.putAll(um.recuperarUsuarios());

        Usuario usuarioaañadir = um.recuperarUsuario(usernameAmigoAñadir.getText());

        int i = am.enviarSolicitud(this.usuario, usuarioaañadir);

        botonAñadido.setVisible(true);
        botonañadir.setVisible(false);
        denegada.setVisible(false);
        pendiente.setVisible(false);

    }

    @javafx.fxml.FXML
    public void aceptarsolicitud(Event event) throws SQLException, IOException {

        AmigosModel am = new AmigosModel();
        SolicitudAmistad s = this.solicitudes.get(nsolicitud);

        if (nsolicitud <= this.solicitudes.size()) {
            PaneSolicitudes.setVisible(true);
            am.aceptarSolicitud(s);
            if (usuario.getSexo().equals("M")) {
                fotohombre11.setVisible(true);
                fotomujer11.setVisible(false);

            } else if (usuario.getSexo().equals("F")) {
                fotomujer11.setVisible(true);
                fotohombre11.setVisible(false);
            }

            nsolicitud++;
            nomuserSolicitud.setText(s.getUsuarioEmisor().getNombreUsuario());
            PaneSolicitudes.setVisible(false);


            FXMLLoader amigos = new FXMLLoader(Main.class.getResource("vistas/VistaAmigos.fxml"));

            Parent root = amigos.load();

            anchor.getChildren().setAll(root);

        } else {
            PaneSolicitudes.setVisible(false);
        }
    }

    @javafx.fxml.FXML
    public void denegarsolicitud(Event event) throws SQLException, IOException {

        AmigosModel am = new AmigosModel();
        SolicitudAmistad s = this.solicitudes.get(nsolicitud);
        PaneSolicitudes.setVisible(true);
        if (nsolicitud <= this.solicitudes.size()) {
            am.rechazarSolicitud(s);
            if (usuario.getSexo().equals("M")) {
                fotohombre11.setVisible(true);
                fotomujer11.setVisible(false);

            } else if (usuario.getSexo().equals("F")) {
                fotomujer11.setVisible(true);
                fotohombre11.setVisible(false);
            }

            nsolicitud++;
            nomuserSolicitud.setText(s.getUsuarioEmisor().getNombreUsuario());
            PaneSolicitudes.setVisible(false);

            FXMLLoader amigos = new FXMLLoader(Main.class.getResource("vistas/VistaAmigos.fxml"));

            Parent root = amigos.load();

            anchor.getChildren().setAll(root);

        } else {
            PaneSolicitudes.setVisible(false);
        }
    }
}
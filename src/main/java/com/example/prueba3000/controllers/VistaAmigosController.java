package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;
import com.example.prueba3000.util.MyListener;
import com.example.prueba3000.util.Validador;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class VistaAmigosController implements Initializable {



    @javafx.fxml.FXML
    private VBox vbox_users;
    private Usuario usuario;
    private Usuario usuarioaeliminar;

    private ArrayList<Usuario> amigos=new ArrayList<>();
    @javafx.fxml.FXML
    private VBox VBoxInfoselccionat;
    @javafx.fxml.FXML
    private Pane paneAmigoSeleccionado;
    @javafx.fxml.FXML
    private ImageView boton_eliminar;
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
    private ImageView botonañadir;
    @javafx.fxml.FXML
    private ImageView botonAñadido;
    @javafx.fxml.FXML
    private Pane paneAmigobuscado;
    @javafx.fxml.FXML
    private ImageView buscador;
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
    @javafx.fxml.FXML
    private ImageView botodenegarsolicitud;
    ArrayList<SolicitudAmistad> solicitudes = new ArrayList<>();
    public Usuario getUsuario() {
        return usuario;
    }

    int nsolicitud=0;
    public void setSolicitudes(Usuario u) throws SQLException {
        SolicitudAmistadModel sam= new SolicitudAmistadModel();
        UsuarioModel um= new UsuarioModel();

        HashMap<Integer,Usuario> usuariosHashmap = new HashMap<>();
        usuariosHashmap.putAll(um.recuperarUsuarios());

        solicitudes.addAll(sam.peticionesAmistad(this.usuario, usuariosHashmap));
    if(this.solicitudes.size()>0) {
        if (usuario.getSexo().equals("M")) {
            fotohombre11.setVisible(true);
            fotomujer11.setVisible(false);

        } else if (usuario.getSexo().equals("F")) {
            fotomujer11.setVisible(true);
            fotohombre11.setVisible(false);
        }

        SolicitudAmistad s = this.solicitudes.get(nsolicitud);
        nomuserSolicitud.setText(s.getUsuarioEmisor().getNombreUsuario());
        nsolicitud++;
    }
    else{
        PaneSolicitudes.setVisible(false);
    }
    }
    public void setUsuario(Usuario u) throws SQLException {

        paneAmigoSeleccionado.setVisible(false);
        paneAmigobuscado.setVisible(false);
        botonAñadido.setVisible(false);
        botonañadir.setVisible(false);
        denegada.setVisible(false);
        pendiente.setVisible(false);


        this.usuario = u;


        UsuarioModel um= new UsuarioModel();
        AmigosModel am= new AmigosModel();

        HashMap<Integer,Usuario> usuariosHashmap = new HashMap<>();
        ArrayList<Usuario> amigoss= new ArrayList<>();

        usuariosHashmap.putAll(um.recuperarUsuarios());
        amigoss.addAll(am.recuperarAmigos(u, usuariosHashmap));

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
        for(Usuario user:this.amigos){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("vistas/AmigosItem.fxml"));
            try {
                HBox hBox=fxmlLoader.load();
                AmigosItemController aic=fxmlLoader.getController();
                aic.setData(user,myListener);
                vbox_users.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        setSolicitudes(u);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void set_datos(Usuario usuario) throws SQLException {
       this.usuarioaeliminar=usuario;
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
        AmigosModel am= new AmigosModel();
        am.eliminaramigo(this.usuario,this.usuarioaeliminar );
        System.out.println("qq");

        FXMLLoader amigos = new FXMLLoader(Main.class.getResource("vistas/VistaAmigos.fxml"));

        Parent root = amigos.load();

        anchor.getChildren().setAll(root);

        VistaAmigosController controller1 = amigos.getController();
        controller1.setUsuario(this.usuario);
    }

    @javafx.fxml.FXML
    public void buscar_usuario(Event event) throws SQLException {

        UsuarioModel um= new UsuarioModel();
        AmigosModel am= new AmigosModel();
        Validador v= new Validador();

        HashMap<Integer,Usuario> usuariosHashmap = new HashMap<>();
        usuariosHashmap.putAll(um.recuperarUsuarios());

        Usuario usuarioaBuscar=um.recuperarUsuario(usernameAmigoAñadir.getText());

        if(v.nombreUsuarioExiste(usuarioaBuscar.getNombre(),usuariosHashmap )==true){
            userNoExiste.setText("");
            paneAmigobuscado.setVisible(true);
            boolean sonamigos=am.sonamigos(this.usuario, usuarioaBuscar);

            if(sonamigos==true){
                botonAñadido.setVisible(true);
                botonañadir.setVisible(false);
                denegada.setVisible(false);
                pendiente.setVisible(false);
            }
            else{
                boolean comprovar_solicitud_enviada=am.comprovar_solicitud_enviada(this.usuario, usuarioaBuscar);
                if(comprovar_solicitud_enviada==true){
                    int i= am.comprovar_tipo_solicitud(this.usuario, usuarioaBuscar);

                    if(i==0){
                        botonAñadido.setVisible(false);
                        botonañadir.setVisible(false);
                        denegada.setVisible(false);
                        pendiente.setVisible(true);
                    }
                    if(i==1){
                        botonAñadido.setVisible(true);
                        botonañadir.setVisible(false);
                        denegada.setVisible(false);
                        pendiente.setVisible(false);
                    }
                    if(i==2){
                        botonAñadido.setVisible(false);
                        botonañadir.setVisible(false);
                        denegada.setVisible(true);
                        pendiente.setVisible(false);
                    }
                } else if (comprovar_solicitud_enviada==false) {
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
        }
        else{
            userNoExiste.setText("El usuario no existe");
        }

    }

    @javafx.fxml.FXML
    public void Añadir_usuario(Event event) throws SQLException {
        UsuarioModel um= new UsuarioModel();
        AmigosModel am= new AmigosModel();


        HashMap<Integer,Usuario> usuariosHashmap = new HashMap<>();
        usuariosHashmap.putAll(um.recuperarUsuarios());

        Usuario usuarioaañadir=um.recuperarUsuario(usernameAmigoAñadir.getText());

        int i=am.enviarSolicitud(this.usuario,usuarioaañadir );

            botonAñadido.setVisible(true);
            botonañadir.setVisible(false);
            denegada.setVisible(false);
            pendiente.setVisible(false);

    }


    @javafx.fxml.FXML
    public void aceptarsolicitud(Event event) throws SQLException {
        AmigosModel am= new AmigosModel();
        SolicitudAmistad s = this.solicitudes.get(nsolicitud);
        if( nsolicitud<=this.solicitudes.size()) {
            am.aceptarSolicitud(s.getUsuarioEmisor(), s.getUsuarioReceptor());
            if (usuario.getSexo().equals("M")) {
                fotohombre11.setVisible(true);
                fotomujer11.setVisible(false);

            } else if (usuario.getSexo().equals("F")) {
                fotomujer11.setVisible(true);
                fotohombre11.setVisible(false);
            }


            nomuserSolicitud.setText(s.getUsuarioEmisor().getNombreUsuario());
            nsolicitud++;
        }
        else{
            PaneSolicitudes.setVisible(false);
        }
    }

    @javafx.fxml.FXML
    public void denegarsolicitud(Event event) throws SQLException {
        AmigosModel am= new AmigosModel();
        SolicitudAmistad s = this.solicitudes.get(nsolicitud);
        if( nsolicitud<=this.solicitudes.size()) {
            am.rechazarSolicitud(s.getUsuarioEmisor(), s.getUsuarioReceptor());
            if (usuario.getSexo().equals("M")) {
                fotohombre11.setVisible(true);
                fotomujer11.setVisible(false);

            } else if (usuario.getSexo().equals("F")) {
                fotomujer11.setVisible(true);
                fotohombre11.setVisible(false);
            }


            nomuserSolicitud.setText(s.getUsuarioEmisor().getNombreUsuario());
            nsolicitud++;
        }
        else{
            PaneSolicitudes.setVisible(false);
        }
    }
}
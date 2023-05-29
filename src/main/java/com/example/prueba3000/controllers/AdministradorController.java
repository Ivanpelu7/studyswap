package com.example.prueba3000.controllers;

import com.example.prueba3000.Main;
import com.example.prueba3000.model.*;


import com.example.prueba3000.util.Validador;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdministradorController implements Initializable {
    @javafx.fxml.FXML
    private AnchorPane anchor;
    @javafx.fxml.FXML
    private Button eliminarUsuario;
    @javafx.fxml.FXML
    private Button añadirCursos;
    @javafx.fxml.FXML
    private Button añadirAsignatura;
    @javafx.fxml.FXML
    private Button eliminarApunte;
    @javafx.fxml.FXML
    private Pane mostrarEliminarUsuario;
    @javafx.fxml.FXML
    private Pane mostrarAñadirCursos;
    @javafx.fxml.FXML
    private Pane mostrarEliminarApunte;
    @javafx.fxml.FXML
    private Pane mostrarAñadirAsignatura;
    @javafx.fxml.FXML
    private ScrollPane scrollPane;
    @javafx.fxml.FXML
    private GridPane vbox_users;
    @javafx.fxml.FXML
    private Label labelErrorCurso;
    @javafx.fxml.FXML
    private Button buttonCrearCursos;
    @javafx.fxml.FXML
    private TextField nombre_curso_crear;
    @javafx.fxml.FXML
    private Button buttonEliminarCursos;
    @javafx.fxml.FXML
    private Button button_editar_cursos;
    @javafx.fxml.FXML
    private TextField nombre_editar_nuevo;
    @javafx.fxml.FXML
    private ComboBox nombre_editar_antiguo;
    @javafx.fxml.FXML
    private ComboBox nombre_eliminar;
    @FXML
    private Label labelErrorCrear;
    @FXML
    private Label labelErrorEditar;
    @FXML
    private Pane paneVincularConCurso;
    @FXML
    private ComboBox listaCursos;
    @FXML
    private Button botonVincularAsignaturaCurso;
    @FXML
    private TextField nombreAsignaturaAñadir;
    @FXML
    private Button botonAñadirAsignatura;
    @FXML
    private Label ErrorNombreAsig;
    @FXML
    private ComboBox listaAsignaturas;
    @FXML
    private Label errorVincularAsig;
    @FXML
    private Label ErrorNombreAsig1;
    @FXML
    private ComboBox listaAsignaturasEliminar;
    @FXML
    private Button botonEliminarAsignatura;
    @FXML
    private TableView tabla;
    @FXML
    private TableColumn columnaNombrePDF;
    @FXML
    private TableColumn columnaCurso;
    @FXML
    private TableColumn columnaAsignatura;
    @FXML
    private TableColumn columnaNombreUsuario;
    @FXML
    private TableColumn columnaIDReporte;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Mostrar_eliminar_usuario();

        mostrarEliminarUsuario.setVisible(true);

        mostrarAñadirCursos.setVisible(false);

        mostrarEliminarApunte.setVisible(false);

        mostrarAñadirAsignatura.setVisible(false);

    }

    @javafx.fxml.FXML
    public void Mostrar_eliminar_usuario() {
        mostrarEliminarUsuario.setVisible(true);

        mostrarAñadirCursos.setVisible(false);

        mostrarEliminarApunte.setVisible(false);

        mostrarAñadirAsignatura.setVisible(false);

        UsuarioModel um = new UsuarioModel();
        HashMap<Integer, Usuario> usuariosHashmap = new HashMap<>();

        try {
            usuariosHashmap.putAll(um.recuperarUsuarios());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Usuario>usuariosNoAdmins= new ArrayList<>();
        for(Usuario u: usuariosHashmap.values() ){
            if(u.getTipoUsuario()==0){
                usuariosNoAdmins.add(u);}
        }

        int column = 0;
        int row = 1;

        for (Usuario user : usuariosNoAdmins) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("vistas/AdminUsersItem.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                AdminUsersItemController auic = fxmlLoader.getController();
                auic.setData(user);
                if (column == 2) {
                    row++;
                    column = 0;
                }
                vbox_users.add(hBox, column++, row);

                GridPane.setMargin(hBox, new Insets(8));

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


    }



    @javafx.fxml.FXML
    public void Mostrar_editar_cursos(ActionEvent actionEvent) throws SQLException {
        mostrarEliminarUsuario.setVisible(false);

        mostrarAñadirCursos.setVisible(true);

        mostrarEliminarApunte.setVisible(false);

        mostrarAñadirAsignatura.setVisible(false);

        CursoModel cm= new CursoModel();


        nombre_editar_antiguo.setItems(cm.recuperarCursosOL());
        nombre_eliminar.setItems(cm.recuperarCursosOL());




    }

    @javafx.fxml.FXML
    public void Mostrar_añadir_asignatura(ActionEvent actionEvent) throws SQLException {
        mostrarEliminarUsuario.setVisible(false);

        mostrarAñadirCursos.setVisible(false);

        mostrarEliminarApunte.setVisible(false);

        mostrarAñadirAsignatura.setVisible(true);

        CursoModel cm= new CursoModel();
        listaCursos.setItems(cm.recuperarCursosOL());

        AsignaturaModel am= new AsignaturaModel();


        listaAsignaturas.setItems(am.recuperarAsignaturasOL());
        listaAsignaturasEliminar.setItems(am.recuperarAsignaturasOL());
    }

    @javafx.fxml.FXML
    public void Mostrar_eliminar_apunte(ActionEvent actionEvent) throws SQLException, IOException {
        mostrarEliminarUsuario.setVisible(false);

        mostrarAñadirCursos.setVisible(false);

        mostrarEliminarApunte.setVisible(true);

        mostrarAñadirAsignatura.setVisible(false);

        columnaIDReporte.setCellValueFactory(new PropertyValueFactory("id"));
        columnaNombrePDF.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaCurso.setCellValueFactory(new PropertyValueFactory("curso"));
        columnaAsignatura.setCellValueFactory(new PropertyValueFactory("asignatura") );
        columnaNombreUsuario.setCellValueFactory(new PropertyValueFactory("autor") );


        ApunteModel am= new ApunteModel();
        ObservableList<Apunte> apuntes = FXCollections.observableArrayList(am.recuperarApuntesTabla().values());
        tabla.setItems(apuntes);
    }




    @javafx.fxml.FXML
    public void añadir_cursos(ActionEvent actionEvent) throws SQLException, IOException {
        labelErrorCrear.setVisible(false);
        CursoModel cm= new CursoModel();
        Curso c= new Curso(nombre_curso_crear.getText().toUpperCase());

        ArrayList<Curso>cursos=new ArrayList<>(cm.recuperarCursos().values());

        boolean existe=false;

        for(Curso curso:cursos){
            if(curso.getNombre().equals(c.getNombre())){

                existe=true;
            }

        }

        if(existe==true){
            labelErrorCrear.setText("EL curso ya existe");
            labelErrorCrear.setVisible(true);
        }
        else {
            cm.crearCurso(c);
            nombre_editar_antiguo.setItems(cm.recuperarCursosOL());
            nombre_eliminar.setItems(cm.recuperarCursosOL());
            nombre_curso_crear.setText(" ");
        }

    }

    @javafx.fxml.FXML
    public void Eliminar_cursos(ActionEvent actionEvent) throws SQLException {
        CursoModel cm= new CursoModel();

        Curso c= (Curso) nombre_eliminar.getValue();

        int i=cm.eliminarCurso(c.getId());
        if(i>0){
            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Curso eliminado correctamente");
            a.showAndWait();
            nombre_editar_antiguo.setItems(cm.recuperarCursosOL());
            nombre_eliminar.setItems(cm.recuperarCursosOL());
        }



    }

    @javafx.fxml.FXML
    public void Editar_Cursos(ActionEvent actionEvent) throws SQLException {
        labelErrorEditar.setVisible(false);
        CursoModel cm= new CursoModel();
        String nombrenuevo= nombre_editar_nuevo.getText( ).toUpperCase();
        String nombreantiguo= nombre_editar_antiguo.getValue().toString();
        ArrayList<Curso>cursos=new ArrayList<>(cm.recuperarCursos().values());
        boolean existe=false;

        for(Curso curso:cursos){
            if(curso.getNombre().equals(nombrenuevo)){
                existe=true;
            }
            System.out.println(curso.getNombre()+" "+nombrenuevo+" "+existe);
        }

        if(existe==true){
            labelErrorEditar.setText("El nombre seleccionado ya existe");
            labelErrorEditar.setVisible(true);
        }
        else {
            cm.editarCurso(nombrenuevo,nombreantiguo);
            System.out.println(nombrenuevo+nombreantiguo);
            nombre_editar_nuevo.setText(" ");
        }
        nombre_editar_antiguo.setItems(cm.recuperarCursosOL());
        nombre_eliminar.setItems(cm.recuperarCursosOL());
    }

    @FXML
    public void Añadir_asignatura(ActionEvent actionEvent) throws SQLException {
        ErrorNombreAsig.setVisible(false);
        AsignaturaModel am = new AsignaturaModel();
        HashMap<Integer,Asignatura> asignaturas = new HashMap<>(am.recuperarAsignaturas());

        String nombreAsignueva= nombreAsignaturaAñadir.getText();
        boolean existe=false;
        for(Asignatura a: asignaturas.values()) {
            if (a.getNombre().equalsIgnoreCase(nombreAsignueva)) {
                existe = true;
            }

        }

            if(existe==true){
                ErrorNombreAsig.setText("Esta asignatura ya existe");
                ErrorNombreAsig.setVisible(true);
            }
            else {
                    am.añadirAsignatura(nombreAsignueva);
            }


    }
    @FXML
    public void filtrarAsignatura(ActionEvent actionEvent) throws SQLException {
        AsignaturaModel am = new AsignaturaModel();
        CursoModel cm= new CursoModel();
        Validador v= new Validador();
        ObservableList<Curso> cursos= FXCollections.observableArrayList();

        listaCursos.setDisable(false);


    }
    @FXML
    public void VincularAsignaturaCurso(ActionEvent actionEvent) throws SQLException {
        Validador v= new Validador();


        if(!v.validarFiltro(listaCursos, listaAsignaturas)){

            errorVincularAsig.setText("Debe seleccionar una asignatura y un curso");
        }
        else {
            errorVincularAsig.setText("");
            String nombreAsig= listaAsignaturas.getValue().toString();
            String nombreCurso= listaCursos.getValue().toString();
            AsignaturaModel am= new AsignaturaModel();
            CursoModel cm= new CursoModel();

            HashMap<Integer,Asignatura> asignaturas = new HashMap<>(am.recuperarAsignaturas());
            HashMap<Integer,Curso> cursos = new HashMap<>(cm.recuperarCursos());

            Asignatura asigantura = null;
            Curso curso = null;
            for(Asignatura a: asignaturas.values()){
                if(nombreAsig.equalsIgnoreCase(a.getNombre())){
                    asigantura=a;
                }
            }

            for(Curso c:cursos.values()){
                if(nombreCurso.equalsIgnoreCase(c.getNombre())){
                    curso=c;
                }
            }
            boolean pertenece=am.comprovarAsignaturaCurso(asigantura, curso);
            if(pertenece==false){
            am.vincularAsignaturaConCurso(curso, asigantura);
            }
            else{
                errorVincularAsig.setText("La asignatura ya pertenece a este curso");
            }
        }




    }

    @FXML
    public void eliminar_asignatura(ActionEvent actionEvent) throws SQLException {
        AsignaturaModel am= new AsignaturaModel();
        Asignatura a= (Asignatura) listaAsignaturasEliminar.getValue();
        am.eliminarAsignatura(a);
    }







    public void acualizarPagina() throws IOException {
        FXMLLoader admin = new FXMLLoader(Main.class.getResource("vistas/Administracion.fxml"));

        Parent root = admin.load();
        anchor.getChildren().setAll(root);
    }



}

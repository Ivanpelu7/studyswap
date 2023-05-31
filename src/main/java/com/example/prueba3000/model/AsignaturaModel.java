package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AsignaturaModel extends DBUtil {

    public HashMap<Integer, Asignatura> recuperarAsignaturas() throws SQLException {

        HashMap<Integer, Asignatura> asignaturas = new HashMap<>();

        String query = "SELECT * FROM asignaturas";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_asignatura");
            String nombre = rs.getString("nombre");

            Asignatura asig = new Asignatura(id, nombre);

            asignaturas.put(asig.getId(), asig);
        }

        cerrarConexion();

        return asignaturas;
    }

    public ObservableList<Asignatura> asignaturasCurso(Curso c) throws SQLException {

        HashMap<Integer, Asignatura> todasAsignaturas = recuperarAsignaturas();

        ObservableList<Asignatura> asignaturas = FXCollections.observableArrayList();

        String query = "SELECT id_asignatura FROM pertenece WHERE id_curso = " + c.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer id = rs.getInt("id_asignatura");

            Asignatura a = todasAsignaturas.get(id);

            asignaturas.add(a);
        }

        cerrarConexion();

        return asignaturas;
    }

    public ObservableList<Asignatura> recuperarAsignaturasOL() throws SQLException {

        ObservableList<Asignatura> asignaturas = FXCollections.observableArrayList();

        String query = "SELECT * FROM asignaturas ";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer id = rs.getInt("id_asignatura");
            String nombre= rs.getString("nombre");
            Asignatura a = new Asignatura(id,nombre);

            asignaturas.add(a);
        }

        cerrarConexion();

        return asignaturas;
    }

    public void añadirAsignatura(String nomasig) throws SQLException {
        String query = "Insert into asignaturas (nombre) values (?)";

        PreparedStatement ps = getConexion().prepareStatement(query);
        ps.setString(1, nomasig);
        ps.execute();
    }


    public void vincularAsignaturaConCurso(Curso c, Asignatura a) throws SQLException {
        String query = "Insert into pertenece (id_asignatura,id_curso) values (?,?)";

        PreparedStatement ps = getConexion().prepareStatement(query);
        ps.setInt(1, a.getId());
        ps.setInt(2, c.getId());
        ps.execute();
    }

    public void eliminarAsignatura(Asignatura a) throws SQLException {
        String query = "delete from asignaturas where id_asignatura=?";
        String query1 = "delete from pertenece where id_asignatura=?";

        PreparedStatement ps = getConexion().prepareStatement(query);
        PreparedStatement ps1 = getConexion().prepareStatement(query1);

        ps.setInt(1, a.getId());
        ps1.setInt(1, a.getId());

        ps1.executeUpdate();
        ps.executeUpdate();
    }

    public boolean comprovarAsignaturaCurso(Asignatura a,Curso c) throws SQLException {
        String query = "Select id_curso from pertenece where id_asignatura=?";

        PreparedStatement ps = getConexion().prepareStatement(query);
        ps.setInt(1, a.getId());
        ResultSet rs=ps.executeQuery();
        boolean pertenece=false;
        while(rs.next()){
            int id= rs.getInt("id_curso");
            if(id==c.getId()){
                pertenece=true;
            }

        }
        return  pertenece;
    }
}

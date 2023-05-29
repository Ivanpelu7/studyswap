package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CursoModel extends DBUtil {

    public Curso recuperarCurso(Integer id_curso) throws SQLException {
        String query = "SELECT * FROM cursos where id_curso="+id_curso;

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        Curso curso = null;
        while (rs.next()) {

            Integer id = rs.getInt("id_curso");
            String nombre = rs.getString("nombre");

             curso = new Curso(id, nombre);
        }
        return curso;
    }

    public HashMap<Integer, Curso> recuperarCursos() throws SQLException {

        HashMap<Integer, Curso> cursos = new HashMap<>();

        String query = "SELECT * FROM cursos";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_curso");
            String nombre = rs.getString("nombre");

            Curso curso = new Curso(id, nombre);

            cursos.put(curso.getId(), curso);
        }

        return cursos;
    }

    public ObservableList<Curso> recuperarCursosOL() throws SQLException {

        ObservableList<Curso> cursos = FXCollections.observableArrayList();

        String query = "SELECT * FROM cursos";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_curso");
            String nombre = rs.getString("nombre");

            Curso curso = new Curso(id, nombre);

            cursos.add(curso);
        }

        return cursos;
    }



    public void crearCurso(Curso curso) throws SQLException {
        String query = "Insert into cursos (nombre) values(?);";
        PreparedStatement ps = getConexion().prepareStatement(query);
        ps.setString(1, curso.getNombre());
        int rs=ps.executeUpdate();
    }

    public int  eliminarCurso(Integer id_curso) throws SQLException {
        String query1 = "delete from pertenece where id_curso= "+id_curso;
        PreparedStatement ps1 = getConexion().prepareStatement(query1);
        int rs1=ps1.executeUpdate();


            String query = "delete from cursos where id_curso= " + id_curso;
            PreparedStatement ps = getConexion().prepareStatement(query);
             int rs = ps.executeUpdate();


        return rs;
    }

    public int  editarCurso(String nombreNuevo,String nombreantiguo) throws SQLException {
        String query = "update cursos set nombre=?  where nombre= ?;";
        PreparedStatement ps = getConexion().prepareStatement(query);
        ps.setString(1, nombreNuevo);
        ps.setString(2, nombreantiguo);
        int rs=ps.executeUpdate();

        return rs;
    }
}

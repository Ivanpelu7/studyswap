package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CursoModel extends DBUtil {

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
}

package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}

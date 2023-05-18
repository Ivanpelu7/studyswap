package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AsignaturaModel extends DBUtil {

    public HashMap<Integer, Asignatura> recuperarAsignaturas() throws SQLException {

        HashMap<Integer, Asignatura> asignaturas = new HashMap<>();

        String query = "SELECT * FROM asigntauras";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_asignatura");
            String nombre = rs.getString("nombre");

            Asignatura asig = new Asignatura(id, nombre);

            asignaturas.put(asig.getId(), asig);
        }

        return asignaturas;
    }
}

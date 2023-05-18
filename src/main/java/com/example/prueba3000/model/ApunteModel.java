package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ApunteModel extends DBUtil {

    public HashMap<Integer, Apunte> recuperarApuntes() throws SQLException {

        HashMap<Integer, Apunte> apuntes = new HashMap<>();

        String query = "SELECT * FROM apuntes";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_apunte");
            String nombre = rs.getString("nombre");
            Blob pdf = rs.getBlob("pdf");
            Integer puntuacion = rs.getInt("puntuacion");

            Apunte apunte = new Apunte(id, nombre, pdf, puntuacion);

            apuntes.put(apunte.getId(), apunte);
        }

        return apuntes;
    }

}

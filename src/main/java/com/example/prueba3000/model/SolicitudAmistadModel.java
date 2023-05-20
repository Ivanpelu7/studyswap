package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SolicitudAmistadModel extends DBUtil {

    public ArrayList<SolicitudAmistad> peticionesAmistad(Usuario usuario, HashMap<Integer, Usuario> usuarios) throws SQLException {

        ArrayList<SolicitudAmistad> solicitudes = new ArrayList<>();

        String query = "SELECT * FROM solicitudes WHERE id_usuario_receptor = " + usuario.getId() + " AND estado = 0";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer idEmisor = rs.getInt("id_usuario_emisor");
            Integer estado = rs.getInt("estado");

            Usuario usEmisor = null;

            for (Usuario u : usuarios.values()) {
                if (u.getId() == idEmisor) {
                    usEmisor = u;
                }
            }

            SolicitudAmistad solicitudAmistad = new SolicitudAmistad(usEmisor, usuario , estado);

            solicitudes.add(solicitudAmistad);
        }

        return solicitudes;
    }
}

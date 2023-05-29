package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteModel extends DBUtil {

    public ReporteModel() {

    }

    public void insertarReporte(Reporte reporte) throws SQLException {

        String query = "INSERT INTO reportes(id_usuario, id_apunte, mensaje) VALUES(?, ?, ?)";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setInt(1, reporte.getUsuario().getId());
        ps.setInt(2, reporte.getApunte().getId());
        ps.setString(3, reporte.getMensaje());

        ps.executeUpdate();

        cerrarConexion();
    }

    public boolean reporteExisteUsuario(Usuario usuario, Apunte apunte) throws SQLException {

        String query = "SELECT * FROM reportes WHERE id_usuario = " + usuario.getId() + " AND id_apunte = " +
                apunte.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return false;

        } else {
            return true;
        }
    }
}

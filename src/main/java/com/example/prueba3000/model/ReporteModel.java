package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ReporteModel extends DBUtil {
    public void insertarReporte(Reporte reporte) throws SQLException, SQLException {

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

    public HashMap<Integer, Reporte> recuperarReportes() throws SQLException, IOException {
        HashMap<Integer,Reporte>reportes = new HashMap<>();

        HashMap<Integer, Apunte> apuntes = new ApunteModel().recuperarApuntes();
        HashMap<Integer, Usuario> usuarios = new UsuarioModel().recuperarUsuarios();

        String query = "SELECT * FROM reportes";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_reporte");
            Integer id_usuario=rs.getInt("id_usuario");
            Integer id_apunte= rs.getInt("id_apunte");
            String mensaje=rs.getString("mensaje");


            Apunte a= apuntes.get(id_apunte);
            Usuario u = usuarios.get(id_usuario);

            Reporte r= new Reporte(id, a, u, mensaje);
            reportes.put(id, r);
        }

        return reportes;
    }
}



package com.example.prueba3000.model;



import com.example.prueba3000.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AmigosModel extends DBUtil {

    public ArrayList<Usuario> amigos = new ArrayList<Usuario>();
    public ArrayList<Integer> idsolicitantes = new ArrayList<Integer>();
    public ArrayList<String> solicitudes = new ArrayList<String>();

    public ArrayList<Usuario> recuperarAmigos(Usuario usuario, HashMap<Integer, Usuario> usuarios) throws SQLException {

        ArrayList<Usuario> amigos = new ArrayList<Usuario>();
        String query = "SELECT id_amigo FROM amigos WHERE id_usuario = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_amigo");

            Usuario u = usuarios.get(id);

            amigos.add(u);
        }

        cerrarConexion();

        return amigos;
    }

    public int aceptarSolicitud(Usuario usuarioEmisor, Usuario usuarioConectado) throws SQLException {

        String query = "UPDATE solicitudes SET estado = 1 WHERE id_usuario_emisor = " + usuarioEmisor.getId() + " AND " +
                "id_usuario_receptor = " + usuarioConectado.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        int i = ps.executeUpdate();

        return i;
    }

    public int rechazarSolicitud(Usuario usuarioEmisor, Usuario usuarioConectado) throws SQLException {

        String query = "UPDATE solicitudes SET estado = 2 WHERE id_usuario_emisor = " + usuarioEmisor.getId() + " AND " +
                "id_usuario_receptor = " + usuarioConectado.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        int i = ps.executeUpdate();

        return i;
    }

    public int enviarSolicitud(Usuario usuarioConectado, Usuario usuarioReceptor) throws SQLException {

        String query = "INSERT INTO solicitudes VALUES(?, ?, 0)";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setInt(1, usuarioConectado.getId());
        ps.setInt(2, usuarioReceptor.getId());

        int i = ps.executeUpdate();

        return i;
    }

}



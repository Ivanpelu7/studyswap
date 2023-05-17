package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UsuarioModel extends DBUtil {

    public HashMap<Integer, Usuario> recuperarUsuarios() throws SQLException {

        HashMap<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

        String query = "SELECT * FROM usuarios";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_usuario");
            String nombreUsuario = rs.getString("nombre_usuario");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            int tipoUsuario = rs.getInt("tipo_usuario");
            String sexo = rs.getString("sexo");

            Usuario usuario = new Usuario(id, nombreUsuario, password, email, nombre, apellidos, tipoUsuario, sexo);

            usuarios.put(usuario.getId(), usuario);
        }

        cerrarConexion();

        return usuarios;

    }

    public int addUsuario(Usuario u) throws SQLException {

        String query1 = "INSERT INTO usuarios (nombre_usuario, password, email, nombre, apellidos, tipo_usuario, sexo) VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = getConexion().prepareStatement(query1);

        ps.setString(1, u.getNombreUsuario());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getEmail());
        ps.setString(4, u.getNombre());
        ps.setString(5, u.getApellidos());
        ps.setInt(6, u.getTipoUsuario());
        ps.setString(7, u.getSexo());

        int i = ps.executeUpdate();

        return i;
    }
/*
    public Usuario recuperarUsuario(String nomuser) throws SQLException {
        DBUtil db = new DBUtil();

        String query1 = "select * from usuarios where nombre_usuario= ?";
        PreparedStatement ps = db.getConexion().prepareStatement(query1);
        ps.setString(1, nomuser);
        ResultSet rs = ps.executeQuery();
        int id=0;
        String username="";
        String contraseña="";
        String email="";
        String nombre="";
        String apellidos="";
        Integer tipo_usuario=0;
        String sexo="";

        while (rs.next()) {

             id=rs.getInt("id_usuario");
             username=rs.getString("nombre_usuario");
             contraseña=rs.getString("password");
             email=rs.getString("email");
             nombre=rs.getString("nombre");
             apellidos=rs.getString("apellidos");
             tipo_usuario=rs.getInt("tipo_usuario");
             sexo=rs.getString("sexo");


        }
        Usuario u= new Usuario(username, contraseña, email,nombre , apellidos, tipo_usuario, sexo);
        return u;
        }
 */
}


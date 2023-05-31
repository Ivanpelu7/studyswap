package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UsuarioModel extends DBUtil {

    public UsuarioModel() {

    }

    public Usuario recuperarUsuario(String nomnreuser) throws SQLException {
        String query = "SELECT * FROM usuarios where nombre_usuario=? ";

        PreparedStatement ps = getConexion().prepareStatement(query);
        ps.setString(1, nomnreuser);
        ResultSet rs = ps.executeQuery();
        Usuario usuario = null;

        while (rs.next()) {

            Integer id = rs.getInt("id_usuario");
            String nombreUsuario = rs.getString("nombre_usuario");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            int tipoUsuario = rs.getInt("tipo_usuario");
            String sexo = rs.getString("sexo");
            Blob fotoPerfil = rs.getBlob("foto_perfil");

            Image image = null;

            if (fotoPerfil != null) {
                InputStream inputStream = fotoPerfil.getBinaryStream();
                image = new Image(inputStream);
            }

            usuario = new Usuario(id, nombreUsuario, password, email, nombre, apellidos, tipoUsuario, sexo, image);


        }
        return usuario;
    }

    public HashMap<Integer, Usuario> recuperarUsuarios() throws SQLException {

        HashMap<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

        String query = "SELECT * FROM usuarios ";

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
            Blob fotoPerfil = rs.getBlob("foto_perfil");

            Image image = null;

            if (fotoPerfil != null) {
                InputStream inputStream = fotoPerfil.getBinaryStream();
                image = new Image(inputStream);
            }

            Usuario usuario = new Usuario(id, nombreUsuario, password, email, nombre, apellidos, tipoUsuario, sexo, image);

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

    public int anadirFotoPerfil(Usuario usuario, File file) throws SQLException, FileNotFoundException {

        String query = "UPDATE usuarios SET foto_perfil = ? WHERE id_usuario = " + usuario.getId();

        PreparedStatement preparedStatement = getConexion().prepareStatement(query);

        InputStream inputStream = new FileInputStream(file);
        preparedStatement.setBinaryStream(1, inputStream, (int) file.length());

        int i = preparedStatement.executeUpdate();

        return i;
    }

    public void eliminarUsuario(Usuario user) throws SQLException {
        String query = "delete from usuarios where id_usuario="+user.getId();
        String query1 = "delete from descargas where id_usuario="+user.getId();

        AmigosModel amigosModel = new AmigosModel();
        amigosModel.eliminarAmistad(user);

        PreparedStatement ps1 = getConexion().prepareStatement(query1);

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps1.executeUpdate();
        ps.executeUpdate();
    }

    public int modificarApellidos(Usuario usuario, String apellidos) throws SQLException {

        String query = "UPDATE usuarios SET apellidos = ? WHERE id_usuario = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setString(1, apellidos);

        int i = ps.executeUpdate();

        cerrarConexion();

        return i;
    }

    public int modificarNombre(Usuario usuario, String nombre) throws SQLException {

        String query = "UPDATE usuarios SET nombre = ? WHERE id_usuario = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setString(1, nombre);

        int i = ps.executeUpdate();

        cerrarConexion();

        return i;
    }

    public int modificarNombreUsuario(Usuario usuario, String nombreUsuario) throws SQLException {

        String query = "UPDATE usuarios SET nombre_usuario = ? WHERE id_usuario = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setString(1, nombreUsuario);

        int i = ps.executeUpdate();

        cerrarConexion();

        return i;
    }

    public int modificarPassword(Usuario usuario, String password) throws SQLException {

        String query = "UPDATE usuarios SET password = ? WHERE id_usuario = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setString(1, password);

        int i = ps.executeUpdate();

        cerrarConexion();

        return i;
    }

    public int modificarCorreoElectronico(Usuario usuario, String correoElectronico) throws SQLException {

        String query = "UPDATE usuarios SET email = ? WHERE id_usuario = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setString(1, correoElectronico);

        int i = ps.executeUpdate();

        cerrarConexion();

        return i;
    }
}



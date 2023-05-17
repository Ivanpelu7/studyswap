package model;

import util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioModel {
    public int comprovarusuario(String nombreuser) throws SQLException {
        DBUtil db = new DBUtil();

        String query1 = "select nombre_usuario from usuarios";
        PreparedStatement ps = db.getConexion().prepareStatement(query1);
        ResultSet rs = ps.executeQuery();
        ArrayList<String> nombre_usuarios = new ArrayList<String>();
        while (rs.next()) {
            String nombre_user = rs.getString("nombre_usuario");
            nombre_usuarios.add(nombre_user);
        }
        int estarepetit = 0;
        for (String s : nombre_usuarios) {
            if (nombreuser.equals(s)) {
                estarepetit = 1;
            }
        }
        return estarepetit;
    }

    public void añadirUsuario(Usuario u) throws SQLException {
        DBUtil db = new DBUtil();

        String query1 = "INSERT INTO usuarios (nombre_usuario,password,email,nombre,apellidos,tipo_usuario,sexo) VALUES (?,?,?,?,?,0,?);";

        PreparedStatement ps = db.getConexion().prepareStatement(query1);

        ps.setString(1, u.getNombreUsuario());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getEmail());
        ps.setString(4, u.getNombre());
        ps.setString(5, u.getApellidos());
        ps.setString(6, u.getSexo());
        System.out.println(query1);

        int rs = ps.executeUpdate();
    }

    public String comprovarcontraseña(String username) throws SQLException {
        DBUtil db = new DBUtil();

        String query1 = "select password from usuarios where nombre_usuario= ?";
        PreparedStatement ps = db.getConexion().prepareStatement(query1);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        String validar = "";
        while (rs.next()) {
            String contraseña = rs.getString("password");
            validar = contraseña;
        }

        return validar;
    }

    public Usuario recuperarusuario(String nomuser) throws SQLException {
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
    }


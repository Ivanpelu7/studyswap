package com.example.prueba3000.model;



import com.example.prueba3000.util.DBUtil;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AmigosModel extends DBUtil {
    public ArrayList<String> amigos = new ArrayList<String>();
    public ArrayList<Integer> idamigos = new ArrayList<Integer>();
    public ArrayList<Integer> idsolicitantes = new ArrayList<Integer>();
    public ArrayList<String> solicitudes = new ArrayList<String>();

    public void veramigos(String nombreusuario) throws SQLException {
        DBUtil db = new DBUtil();
        //ID DEL USUARIO
        String id_user = "select id_usuario from usuarios where nombre_usuario=?";
        PreparedStatement ps = db.getConexion().prepareStatement(id_user);
        ps.setString(1, nombreusuario);
        ResultSet rs = ps.executeQuery();
        Integer id = 0;
        while (rs.next()) {
            id = rs.getInt("id_usuario");
        }
        //ID AMIGOS
        String id_amigos = "select id_amigo from amigos where id_usuario=" + id + ";";
        PreparedStatement ps1 = db.getConexion().prepareStatement(id_amigos);
        ResultSet rs1 = ps1.executeQuery();
        while (rs1.next()) {
            int ids = rs1.getInt("id_amigo");
            idamigos.add(ids);
        }

        //NOMBRE AMIGOS
        for (Integer i : idamigos) {
            String nombreamigos = "select nombre_usuario from usuarios where id_usuario=" + i + ";";
            PreparedStatement ps2 = db.getConexion().prepareStatement(nombreamigos);

            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                String nombre = rs2.getString("nombre_usuario");
                amigos.add(nombre);
            }
        }

    }


    public void añadiramigo(String usuarioemisor, String usuarioreceptor) throws SQLException {
        DBUtil db = new DBUtil();
        //ID DEL USUARIO_emisor
        String id_user = "select id_usuario from usuarios where nombre_usuario=" + "'" + usuarioemisor + "'" + ";";
        PreparedStatement ps = db.getConexion().prepareStatement(id_user);
        ResultSet rs = ps.executeQuery();
        Integer id_emisor = 0;
        while (rs.next()) {
            id_emisor = rs.getInt("id_usuario");
        }


        String id_user2 = "select id_usuario from usuarios where nombre_usuario=" + "'" + usuarioreceptor + "'" + ";";
        PreparedStatement ps2 = db.getConexion().prepareStatement(id_user2);
        ResultSet rs2 = ps2.executeQuery();
        Integer id_receptor = 0;
        while (rs2.next()) {
            id_receptor = rs2.getInt("id_usuario");
        }

        String añadir = "call solicitud_amistad(" + id_emisor + "," + id_receptor + ");";
        PreparedStatement psañadir = db.getConexion().prepareStatement(añadir);
        psañadir.executeQuery();


    }
    public void mostrarsolicitudes(String nombreusuario) throws SQLException {
        DBUtil db = new DBUtil();
        //ID DEL USUARIO
        String id_user = "select id_usuario from usuarios where nombre_usuario=?";
        PreparedStatement ps = db.getConexion().prepareStatement(id_user);
        ps.setString(1, nombreusuario);
        ResultSet rs = ps.executeQuery();
        Integer id = 0;
        while (rs.next()) {
            id = rs.getInt("id_usuario");
        }


        //ID DE los emisores
        String id_users_emisores = "SELECT id_usuario_emisor FROM solicitudes where id_usuario_receptor="+ id+" and estado=0;";
        PreparedStatement ps1 = db.getConexion().prepareStatement(id_users_emisores);
        ResultSet rs1 = ps1.executeQuery();
        while (rs1.next()) {
            idsolicitantes.add(rs1.getInt("id_usuario_emisor"));
            System.out.println(rs1.getInt("id_usuario_emisor"));


        }
        System.out.println(idsolicitantes.size());
    if(idsolicitantes.size()>0){
       // NOMBRE solicitantes
        for (Integer i : idsolicitantes) {
            String nombresolicitantes = "select nombre_usuario from usuarios where id_usuario=" + i + ";";
            PreparedStatement ps2 = db.getConexion().prepareStatement(nombresolicitantes);

            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                String nombre = rs2.getString("nombre_usuario");
                solicitudes.add(nombre);
                System.out.println(nombre);
            }
        }
        }






    }
    public void aceptarsolicitud(String usuarioemisor, String usuarioreceptor) throws SQLException {
        DBUtil db = new DBUtil();
        //ID DEL USUARIO_emisor
        String id_user = "select id_usuario from usuarios where nombre_usuario=" + "'" + usuarioemisor + "'" + ";";
        PreparedStatement ps = db.getConexion().prepareStatement(id_user);
        ResultSet rs = ps.executeQuery();
        Integer id_emisor = 0;
        while (rs.next()) {
            id_emisor = rs.getInt("id_usuario");
        }

        //ID DEL USUARIO RECEPTOR
        String id_user2 = "select id_usuario from usuarios where nombre_usuario=" + "'" + usuarioreceptor +"'"+";";
        PreparedStatement ps2 = db.getConexion().prepareStatement(id_user2);
        ResultSet rs2 = ps2.executeQuery();
        Integer id_receptor = 0;
        while (rs2.next()) {
            id_receptor = rs2.getInt("id_usuario");
        }

        String aceptar= "UPDATE solicitudes SET estado=1 where id_usuario_receptor= "+id_receptor+" and id_usuario_emisor= "+id_emisor+";";
        System.out.println(aceptar);
        PreparedStatement ps3 = db.getConexion().prepareStatement(aceptar);
        ps3.executeUpdate();

    }

    public void rechazarsolicitud(String usuarioemisor, String usuarioreceptor) throws SQLException {
        DBUtil db = new DBUtil();
        //ID DEL USUARIO_emisor
        String id_user = "select id_usuario from usuarios where nombre_usuario=" + "'" + usuarioemisor + "'" + ";";
        PreparedStatement ps = db.getConexion().prepareStatement(id_user);
        ResultSet rs = ps.executeQuery();
        Integer id_emisor = 0;
        while (rs.next()) {
            id_emisor = rs.getInt("id_usuario");
        }

        //ID DEL USUARIO RECEPTOR
        String id_user2 = "select id_usuario from usuarios where nombre_usuario=" + "'" + usuarioreceptor +"'"+";";
        PreparedStatement ps2 = db.getConexion().prepareStatement(id_user2);
        ResultSet rs2 = ps2.executeQuery();
        Integer id_receptor = 0;
        while (rs2.next()) {
            id_receptor = rs2.getInt("id_usuario");
        }

        String rechazar= "UPDATE solicitudes SET estado=2 where id_usuario_receptor= "+id_receptor+" and id_usuario_emisor= "+id_emisor+";";
        System.out.println(rechazar);
        PreparedStatement ps3 = db.getConexion().prepareStatement(rechazar);
        ps3.executeUpdate();

    }

    public ArrayList<Usuario> recuperarAmigos(Usuario usuario, HashMap<Integer, Usuario> usuarios) throws SQLException {

        ArrayList<Usuario> amigos = new ArrayList<>();

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

    public ArrayList<Usuario> solicitantesAmistad(Usuario usuario, HashMap<Integer, Usuario> usuarios) throws SQLException {

        ArrayList<Usuario> solicitantes = new ArrayList<>();

        String query = "SELECT id_usuario_emisor FROM solicitudes WHERE id_usuario_receptor = " + usuario.getId() + " AND " +
                "estado = 0";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_usuario_emisor");

            Usuario u = usuarios.get(id);

            solicitantes.add(u);
        }

        return solicitantes;
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



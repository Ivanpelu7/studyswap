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

    public int aceptarSolicitud(SolicitudAmistad s) throws SQLException {

        String query = "UPDATE solicitudes SET estado = 1 WHERE id_solicitud ="+s.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        int i = ps.executeUpdate();

        return i;
    }

    public int rechazarSolicitud(SolicitudAmistad s) throws SQLException {

        String query = "UPDATE solicitudes SET estado = 2 WHERE id_solicitud ="+s.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        int i = ps.executeUpdate();

        return i;
    }

    public int enviarSolicitud(Usuario usuarioConectado, Usuario usuarioReceptor) throws SQLException {

        String query = "call solicitud_amistad(?, ?)";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setInt(1, usuarioConectado.getId());
        ps.setInt(2, usuarioReceptor.getId());

        int i = ps.executeUpdate();

        return i;
    }

    public void eliminaramigo(Usuario userconectado,Usuario amigoEliminado) throws SQLException {

        String query = "call eliminar_amigo(" + userconectado.getId() + ", " + amigoEliminado.getId() + ")";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.execute();
    }

    public boolean sonamigos(Usuario userconectado,Usuario amigo) throws SQLException {

        String query = "SELECT id_amigo FROM amigos WHERE id_usuario = "+ userconectado.getId()+";";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        boolean comprovaramistad=false;

        while(rs.next()){
            int id= rs.getInt("id_amigo");
            if(amigo.getId()==id){
                comprovaramistad=true;
            }
        }
        return comprovaramistad;
    }

    public int comprovar_tipo_solicitud(Usuario userconectado,Usuario recividor) throws SQLException {
        String query = "SELECT id_usuario_receptor,estado FROM solicitudes where id_usuario_emisor="+userconectado.getId()+" and estado=0 or estado=2;";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        int comprovarSolicitud=0;
        while(rs.next()){
            int id= rs.getInt("id_usuario_receptor");
            int estado=rs.getInt("estado");
            if(recividor.getId()==id){
                if(estado==1){comprovarSolicitud=1;}
                if(estado==2){comprovarSolicitud=2;}
            }
        }
       return comprovarSolicitud;
    }

    public boolean comprovar_solicitud_enviada(Usuario userconectado, Usuario recividor) throws SQLException {
        String query = "SELECT id_usuario_receptor FROM solicitudes where id_usuario_emisor="+userconectado.getId()+" and estado=0 or estado=2;";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        boolean comprovarSolicitud=false;
        while(rs.next()){
            int id= rs.getInt("id_usuario_receptor");
            if(recividor.getId()==id){
                comprovarSolicitud=true;
            }
        }
        return comprovarSolicitud;
    }

    public void eliminarAmistad(Usuario usuario) throws SQLException {

        String query = "DELETE FROM amigos WHERE id_usuario = " + usuario.getId() + " OR id_amigo = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.executeUpdate();
    }

}



package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ApunteModel extends DBUtil {


    public HashMap<Integer, Apunte> recuperarApuntes() throws SQLException, IOException {

        HashMap<Integer, Apunte> apuntes = new HashMap<>();
        HashMap<Integer, Asignatura> asignaturas = new AsignaturaModel().recuperarAsignaturas();
        HashMap<Integer, Curso> cursos = new CursoModel().recuperarCursos();
        HashMap<Integer, Usuario> usuarios = new UsuarioModel().recuperarUsuarios();

        String query = "SELECT * FROM apuntes";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_apunte");
            String nombre = rs.getString("nombre");
            Blob pdf = rs.getBlob("pdf");
            Integer puntuacion = rs.getInt("puntuacion");
            Integer idAsignatura = rs.getInt("id_asignatura");
            Integer idCurso = rs.getInt("id_curso");
            Integer idAutor = rs.getInt("id_autor");
            Integer eliminado = rs.getInt("eliminado");

            Asignatura a = asignaturas.get(idAsignatura);
            Curso c = cursos.get(idCurso);
            Usuario u = usuarios.get(idAutor);

            Apunte apunte = new Apunte(id, nombre, pdf, puntuacion, a, c, u, eliminado);

            apuntes.put(apunte.getId(), apunte);
        }

        return apuntes;
    }

    public ArrayList<Apunte> recuperarApuntesArray() throws SQLException, IOException {

        ArrayList<Apunte> apuntes = new ArrayList<>();
        HashMap<Integer, Asignatura> asignaturas = new AsignaturaModel().recuperarAsignaturas();
        HashMap<Integer, Curso> cursos = new CursoModel().recuperarCursos();
        HashMap<Integer, Usuario> usuarios = new UsuarioModel().recuperarUsuarios();

        String query = "SELECT * FROM apuntes ORDER BY puntuacion DESC";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_apunte");
            String nombre = rs.getString("nombre");
            Blob pdf = rs.getBlob("pdf");
            Integer puntuacion = rs.getInt("puntuacion");
            Integer idAsignatura = rs.getInt("id_asignatura");
            Integer idCurso = rs.getInt("id_curso");
            Integer idAutor = rs.getInt("id_autor");
            Integer eliminado = rs.getInt("eliminado");

            Asignatura a = asignaturas.get(idAsignatura);
            Curso c = cursos.get(idCurso);
            Usuario u = usuarios.get(idAutor);

            Apunte apunte = new Apunte(id, nombre, pdf, puntuacion, a, c, u, eliminado);

            apuntes.add(apunte);
        }

        return apuntes;
    }

    public ArrayList<Apunte> apuntesFiltro(Asignatura a, Curso c) throws SQLException, IOException {

        HashMap<Integer, Apunte> apuntesTodos = recuperarApuntes();

        ArrayList<Apunte> apuntesFiltro = new ArrayList<>();

        String query = "SELECT id_apunte FROM apuntes WHERE id_asignatura = " + a.getId() + " AND id_curso = " + c.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer id = rs.getInt("id_apunte");

            Apunte ap = apuntesTodos.get(id);

            apuntesFiltro.add(ap);
        }

        return apuntesFiltro;
    }

    public ArrayList<Apunte> apuntesSubidos(Usuario usuario) throws SQLException, IOException {

        HashMap<Integer, Apunte> apuntes = recuperarApuntes();
        ArrayList<Apunte> apuntesSubidos = new ArrayList<>();

        String query = "SELECT * FROM apuntes WHERE id_autor = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_apunte");

            Apunte apunte = apuntes.get(id);

            apuntesSubidos.add(apunte);
        }

        return apuntesSubidos;
    }

    public ArrayList<Apunte> apuntesDescargados(Usuario usuario) throws SQLException, IOException {

        HashMap<Integer, Apunte> apuntes = recuperarApuntes();
        ArrayList<Apunte> apuntesDescargados = new ArrayList<>();

        String query = "SELECT * FROM descargas WHERE id_usuario = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("id_apunte");

            Apunte apunte = apuntes.get(id);

            apuntesDescargados.add(apunte);
        }

        return apuntesDescargados;
    }

    public int insertarApunte(Apunte apunte) throws SQLException, FileNotFoundException {

        String query = "INSERT INTO apuntes(nombre, pdf, id_asignatura, id_curso, id_autor)" +
                " VALUES(?, ?, ?, ?, ?)";

        PreparedStatement ps = getConexion().prepareStatement(query);

        FileInputStream flujo = new FileInputStream(apunte.getPf());

        ps.setString(1, apunte.getNombre());
        ps.setBlob(2, flujo);
        ps.setInt(3, apunte.getAsignatura().getId());
        ps.setInt(4, apunte.getCurso().getId());
        ps.setInt(5, apunte.getAutor().getId());

        int i = ps.executeUpdate();

        cerrarConexion();

        return i;
    }

    public void apunteDescargado(Apunte apunte, Usuario usuario) throws SQLException {

        String query = "call añadirRegistroDescargas("+ usuario.getId() + ", " + apunte.getId() + ")";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.executeUpdate();

    }

    public void eliminarApuntesUsuario(Usuario usuario) throws SQLException {

        String query = "DELETE FROM apuntes WHERE id_autor = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.executeUpdate();

        cerrarConexion();
    }

    public void eliminarDescargasUsuario(Usuario usuario) throws SQLException {

        String query = "DELETE FROM descargas WHERE id_usuario = " + usuario.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.executeUpdate();

        cerrarConexion();
    }

    public void calificarApunte(Usuario usuario, Apunte apunte, int calificacion) throws SQLException {

        String query = "INSERT INTO puntuaciones(id_usuario, id_apunte, puntuacion) VALUES(?, ?, ?)";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.setInt(1, usuario.getId());
        ps.setInt(2, apunte.getId());
        ps.setInt(3, calificacion);

        ps.executeUpdate();

        cerrarConexion();
    }

    public boolean comprobarPuntuacionExiste(Usuario usuario, Apunte apunte) throws SQLException {

        String query = "SELECT * FROM puntuaciones WHERE id_usuario = " + usuario.getId() + " AND id_apunte = "
                + apunte.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            return false;

        } else {
            return true;
        }
    }



    public void eliminarApunte(Apunte apunte) throws SQLException {

        String query = "DELETE FROM apuntes WHERE id_apunte = " + apunte.getId();
        String query1 = "DELETE FROM descargas WHERE id_apunte = " + apunte.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);
        PreparedStatement ps1 = getConexion().prepareStatement(query1);

        ps1.executeUpdate();
        ps.executeUpdate();
    }

    public int numeroDescargas(Apunte apunte) throws SQLException {

        int i = 0;

        String query = "SELECT count(*) FROM descargas WHERE id_apunte = " + apunte.getId();

        PreparedStatement ps = getConexion().prepareStatement(query);

        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            i = resultSet.getInt("count(*)");
        }

        return i;
    }


}

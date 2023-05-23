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

            Asignatura a = asignaturas.get(idAsignatura);
            Curso c = cursos.get(idCurso);
            Usuario u = usuarios.get(idAutor);

            Apunte apunte = new Apunte(id, nombre, pdf, puntuacion, a, c, u);

            apuntes.put(apunte.getId(), apunte);
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

        cerrarConexion();

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

        String query = "call añadirRegistroDescargas(" + usuario.getId() + ", " + apunte.getId() + ")";

        PreparedStatement ps = getConexion().prepareStatement(query);

        ps.executeUpdate();

        cerrarConexion();
    }
}

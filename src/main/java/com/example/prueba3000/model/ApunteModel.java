package com.example.prueba3000.model;

import com.example.prueba3000.util.DBUtil;

import javax.xml.transform.Result;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ApunteModel extends DBUtil {

    public HashMap<Integer, Apunte> recuperarApuntes() throws SQLException {

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
            Integer idAutor = rs.getInt("id_propietario");

            Asignatura a = asignaturas.get(idAsignatura);
            Curso c = cursos.get(idCurso);
            Usuario u = usuarios.get(idAutor);

            Apunte apunte = new Apunte(id, nombre, pdf, puntuacion, a, c, u);

            apuntes.put(apunte.getId(), apunte);
        }

        return apuntes;
    }

    public ArrayList<Apunte> apuntesFiltro(Asignatura a) throws SQLException {

        HashMap<Integer, Apunte> apuntesTodos = recuperarApuntes();

        ArrayList<Apunte> apuntesFiltro = new ArrayList<>();

        String query = "SELECT id_apunte FROM apuntes WHERE id_asignatura = " + a.getId();

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

}

package com.example.prueba3000.util;

import com.example.prueba3000.model.Asignatura;
import com.example.prueba3000.model.Curso;
import com.example.prueba3000.model.Usuario;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.util.HashMap;

public class Validador {
    public boolean validarPasswordIguales(String password, String repeatPassword) {

        if (password.equals(repeatPassword)) {
            return true;

        } else {
            return false;
        }
    }

    public boolean nombreUsuarioExiste(String nombreUsuario, HashMap<Integer, Usuario> usuarios) {

        for (Usuario usuario : usuarios.values()) {

            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return false;
            }
        }

        return true;
    }

    public boolean camposRellenados(TextField n, TextField a, TextField em, RadioButton m, RadioButton f, PasswordField p, PasswordField pa, TextField nu) {

        if (n == null || a == null || em == null || (!m.isSelected() && !f.isSelected()) || p == null || pa == null || nu == null) {
            return false;
        }

        return true;
    }

    public boolean comprobarInicioSesion(String nombreUsuario, String password, HashMap<Integer, Usuario> usuarios) {

        for (Usuario u : usuarios.values()) {

            if (nombreUsuario.equals(u.getNombreUsuario()) && password.equals(u.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public boolean validarPasswordFormato(String password) {

        if (password.length() > 5) {
            return false;
        }

        for (char caracter : password.toCharArray()) {
            if (!Character.isLetterOrDigit(caracter)) {
                return false;
            }
        }

        return true;
    }

    public boolean validarFiltro(ComboBox<Curso> cursos, ComboBox<Asignatura> asignaturas) {

        if (cursos.getValue() == null) {
            return false;

        } else if (asignaturas.getValue() == null) {
            return false;
        }

        return true;
    }

    public boolean validarPublicacion(ComboBox<Asignatura> asignaturas, ComboBox<Curso> cursos, TextField ruta, TextField nombre) {

        if (asignaturas.getValue() == null || cursos.getValue() == null || ruta.getText().equals("") || nombre.getText().equals("")) {
            return false;

        } else {
            return true;
        }
    }
}

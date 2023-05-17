package com.example.prueba3000.util;

import com.example.prueba3000.model.Usuario;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class Validador {

    public boolean validarPasswordRegistro(String password, String repeatPassword) {

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

    public boolean camposRellenados(TextField n, TextField a, TextField em, RadioButton m, RadioButton f, PasswordField p, PasswordField pa) {

        if (n == null || a == null || em == null || (!m.isSelected() && !f.isSelected()) || p == null || pa == null) {
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






}

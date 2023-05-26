package com.example.prueba3000.util;

import com.example.prueba3000.model.Usuario;

public class UsuarioHolder {

    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {

        UsuarioHolder.usuario = usuario;
    }
}

package com.example.prueba3000.util;

import com.example.prueba3000.model.Usuario;

public final class UsuarioHolder {

    private static Usuario usuario;

    private UsuarioHolder() {

    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        UsuarioHolder.usuario = usuario;
    }
}

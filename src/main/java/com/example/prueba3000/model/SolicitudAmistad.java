package com.example.prueba3000.model;

import java.util.Date;

public class SolicitudAmistad {

    private Usuario usuarioEmisor;
    private Usuario usuarioReceptor;
    private int estado;

    public SolicitudAmistad(Usuario usuarioEmisor, Usuario usuarioReceptor, int estado) {
        this.usuarioEmisor = usuarioEmisor;
        this.usuarioReceptor = usuarioReceptor;
        this.estado = 0;
    }

    public Usuario getUsuarioEmisor() {
        return usuarioEmisor;
    }

    public void setUsuarioEmisor(Usuario usuarioEmisor) {
        this.usuarioEmisor = usuarioEmisor;
    }

    public Usuario getUsuarioReceptor() {
        return usuarioReceptor;
    }

    public void setUsuarioReceptor(Usuario usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}

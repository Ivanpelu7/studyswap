package com.example.prueba3000.model;

public class Reporte {
    private int id;

    private Apunte apunte;

    private Usuario usuario;

    private String mensaje;

    public Reporte(int id, Apunte apunte, Usuario usuario, String mensaje) {
        this.id = id;
        this.apunte = apunte;
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public Reporte(Apunte apunte, Usuario usuario, String mensaje) {
        this.apunte = apunte;
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public Apunte getApunte() {
        return apunte;
    }

    public void setApunte(Apunte apunte) {
        this.apunte = apunte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

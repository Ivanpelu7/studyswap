package com.example.prueba3000.model;

import java.io.File;
import java.sql.Blob;

public class Apunte {

    private int id;
    private String nombre;
    private Blob pdf;
    private File pf;
    private int puntuacion;

    public Apunte(int id, String nombre, Blob pdf, File pf, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.pdf = pdf;
        this.pf = pf;
        this.puntuacion = puntuacion;
    }

    public Apunte(int id, String nombre, File pf, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.pf = pf;
        this.puntuacion = puntuacion;
    }

    public Apunte(int id, String nombre, Blob pdf, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.pdf = pdf;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Blob getPdf() {
        return pdf;
    }

    public void setPdf(Blob pdf) {
        this.pdf = pdf;
    }

    public File getPf() {
        return pf;
    }

    public void setPf(File pf) {
        this.pf = pf;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}

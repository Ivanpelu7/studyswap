package com.example.prueba3000.model;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class Apunte {

    private int id;
    private String nombre;
    private Blob pdf;
    private File pf;
    private int puntuacion;
    private Asignatura asignatura;
    private Curso curso;
    private Usuario autor;
    private int eliminado;

    public Apunte(String nombre, File pf, Asignatura asignatura, Curso curso, Usuario autor) {
        this.id = id;
        this.nombre = nombre;
        this.pdf = pdf;
        this.pf = pf;
        this.asignatura = asignatura;
        this.curso = curso;
        this.autor = autor;
    }

    public Apunte(int id, String nombre, File pf, int puntuacion, Asignatura asignatura, Curso curso, Usuario autor) {
        this.id = id;
        this.nombre = nombre;
        this.pf = pf;
        this.puntuacion = puntuacion;
        this.asignatura = asignatura;
        this.curso = curso;
        this.autor = autor;
    }

    public Apunte(int id, String nombre, Blob pdf, int puntuacion, Asignatura asignatura, Curso curso, Usuario autor, int eliminado) throws SQLException, IOException {
        this.id = id;
        this.nombre = nombre;
        this.pdf = pdf;
        this.puntuacion = puntuacion;
        this.asignatura = asignatura;
        this.curso = curso;
        this.autor = autor;
        this.eliminado = eliminado;
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

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return  this.nombre+"  "+this.curso.getNombre()+"  "+this.asignatura.getNombre();
    }


    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }
}

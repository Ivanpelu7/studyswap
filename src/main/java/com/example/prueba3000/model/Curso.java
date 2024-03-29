package com.example.prueba3000.model;

import java.util.ArrayList;

public class Curso {

    private int id;
    private String nombre;
    private ArrayList<Asignatura> asignaturasCurso;

    public Curso(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public Curso( String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return nombre;
    }
}

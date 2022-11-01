package com.example.duoc.oracle.Modelo;

public class Profesional {


    private String rut;
    private int idComuna;
    private int idProfesion;
    private String nombre;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    public int getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(int idProfesion) {
        this.idProfesion = idProfesion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesional() {
    }

    public Profesional(String rut, int idComuna, int idProfesion, String nombre) {
        this.rut = rut;
        this.idComuna = idComuna;
        this.idProfesion = idProfesion;
        this.nombre = nombre;
    }


}

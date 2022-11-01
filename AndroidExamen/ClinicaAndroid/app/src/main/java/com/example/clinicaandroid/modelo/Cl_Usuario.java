package com.example.clinicaandroid.modelo;

public class Cl_Usuario {
    private String usuario;
    private String pass;

    public Cl_Usuario() {
    }

    public Cl_Usuario(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

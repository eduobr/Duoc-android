package com.example.lc13007xx.myapplication.entidades;

import android.widget.Toast;

import com.example.lc13007xx.myapplication.MainActivity;

public class Automovil {

    private String patente;
    private int numChasis;
    private String color;
    private String modelo;

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente.length()<6){
            //Toast.makeText(MainActivity.class, "PATENTE MUY CORTITA", Toast.LENGTH_SHORT).show();
        }
        this.patente = patente;
    }

    public int getNumChasis() {
        return numChasis;
    }

    public void setNumChasis(int numChasis) {
        this.numChasis = numChasis;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Automovil() {
    }

    public Automovil(String patente, int numChasis, String color, String modelo) {
        setPatente(patente);
        setNumChasis(numChasis);
        setColor(color);
        setModelo(modelo);
    }
}

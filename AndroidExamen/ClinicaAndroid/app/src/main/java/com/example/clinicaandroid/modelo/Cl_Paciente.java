package com.example.clinicaandroid.modelo;

import java.sql.Date;

public class Cl_Paciente {
    private String rut;
    private String pNombre;
    private String sNombre;
    private String aPaterno;
    private String aMaterno;
    private Date fecNacimiento;
    private int telefono;
    private int saludId;
    private String salud;

    public Cl_Paciente() {
    }

    public Cl_Paciente(String rut, String pNombre, String sNombre, String aPaterno, String aMaterno, Date fecNacimiento, int telefono, int saludId, String salud) {
        this.rut = rut;
        this.pNombre = pNombre;
        this.sNombre = sNombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.fecNacimiento = fecNacimiento;
        this.telefono = telefono;
        this.saludId = saludId;
        this.salud = salud;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) throws Exception {
        this.rut = rut;
        if (validarRut(rut)) {
            this.rut = rut;
        }else{
            throw new Exception("El rut ingresado no es valido");
        }
    }

    public String getpNombre() {
        return pNombre;
    }

    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getSaludId() {
        return saludId;
    }

    public void setSaludId(int saludId) {
        this.saludId = saludId;
    }

    public String getSalud() {
        return salud;
    }

    public void setSalud(String salud) {
        this.salud = salud;
    }

    private boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {

        } catch (Exception e) {

        }
        return validacion;
    }
}

package com.example.clinicaandroid.modelo;

import java.sql.Date;

public class Cl_Medico {
    private String rut;
    private String pNombre;
    private String sNombre;
    private String aPaterno;
    private String aMaterno;
    private long telefono;
    private int sueldoBase;
    private Date fecContrato;
    private float comision;
    private String unidad;
    private String cargo;
    private String especialidad;
    private int jefeRut;

    public Cl_Medico() {
    }

    public Cl_Medico(String rut, String pNombre, String sNombre, String aPaterno, String aMaterno, int telefono, int sueldoBase, Date fecContrato, int comision, String unidad, String cargo, String especialidad, int jefeRut) {
        this.rut = rut;
        this.pNombre = pNombre;
        this.sNombre = sNombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.telefono = telefono;
        this.sueldoBase = sueldoBase;
        this.fecContrato = fecContrato;
        this.comision = comision;
        this.unidad = unidad;
        this.cargo = cargo;
        this.especialidad = especialidad;
        this.jefeRut = jefeRut;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) throws Exception {
        this.rut = rut;
        /*if (validarRut(rut)) {
            this.rut = rut;
        }else{
            throw new Exception("El rut ingresado no es valido");
        }*/
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

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public int getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(int sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public Date getFecContrato() {
        return fecContrato;
    }

    public void setFecContrato(Date fecContrato) {
        this.fecContrato = fecContrato;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getJefeRut() {
        return jefeRut;
    }

    public void setJefeRut(int jefeRut) {
        this.jefeRut = jefeRut;
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

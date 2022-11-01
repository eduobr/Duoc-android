package com.example.clinicaandroid.modelo;

import java.sql.Date;

public class Cl_Atencion {
    private int idAtencion;
    private String rutMedico;
    private String nombreMedico;
    private String rutPaciente;
    private String nombrePaciente;
    private Date fecAtencion;
    private String horaAtencion;

    public Cl_Atencion() {
    }

    public Cl_Atencion(int idAtencion, String rutMedico, String nombreMedico, String rutPaciente, String nombrePaciente, Date fecAtencion, String horaAtencion) {
        this.idAtencion = idAtencion;
        this.rutMedico = rutMedico;
        this.nombreMedico = nombreMedico;
        this.rutPaciente = rutPaciente;
        this.nombrePaciente = nombrePaciente;
        this.fecAtencion = fecAtencion;
        this.horaAtencion = horaAtencion;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public String getRutMedico() {
        return rutMedico;
    }

    public void setRutMedico(String rutMedico) {
        this.rutMedico = rutMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getRutPaciente() {
        return rutPaciente;
    }

    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public Date getFecAtencion() {
        return fecAtencion;
    }

    public void setFecAtencion(Date fecAtencion) {
        this.fecAtencion = fecAtencion;
    }

    public String getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(String horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    @Override
    public String toString() {
        return "Cl_Atencion{" +
                "idAtencion=" + idAtencion +
                ", rutMedico='" + rutMedico + '\'' +
                ", nombreMedico='" + nombreMedico + '\'' +
                ", rutPaciente='" + rutPaciente + '\'' +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                ", fecAtencion=" + fecAtencion +
                ", horaAtencion='" + horaAtencion + '\'' +
                '}';
    }
}
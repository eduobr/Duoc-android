package com.example.clinicaandroid.modelo;

public class Cl_Salud {
    private int sal_id;
    private String descripcion;
    private int costo_pago;
    private int tipo_sal_id;

    public Cl_Salud() {
    }

    public int getSal_id() {
        return sal_id;
    }

    public void setSal_id(int sal_id) {
        this.sal_id = sal_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCosto_pago() {
        return costo_pago;
    }

    public void setCosto_pago(int costo_pago) {
        this.costo_pago = costo_pago;
    }

    public int getTipo_sal_id() {
        return tipo_sal_id;
    }

    public void setTipo_sal_id(int tipo_sal_id) {
        this.tipo_sal_id = tipo_sal_id;
    }
}

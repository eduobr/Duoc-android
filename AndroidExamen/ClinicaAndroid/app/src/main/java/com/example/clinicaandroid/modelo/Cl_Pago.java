package com.example.clinicaandroid.modelo;

public class Cl_Pago {
    private int idAtencion;
    private int costo;
    private int monto_cancelar;
    private String fecVenc;
    private String obsPago;

    public Cl_Pago() {
    }

    public Cl_Pago(int idPago, int costo, int monto_cancelar, String fecVenc, String obsPago) {
        this.idAtencion = idPago;
        this.costo = costo;
        this.monto_cancelar = monto_cancelar;
        this.fecVenc = fecVenc;
        this.obsPago = obsPago;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getMonto_cancelar() {
        return monto_cancelar;
    }

    public void setMonto_cancelar(int monto_cancelar) {
        this.monto_cancelar = monto_cancelar;
    }

    public String getFecVenc() {
        return fecVenc;
    }

    public void setFecVenc(String fecVenc) {
        this.fecVenc = fecVenc;
    }

    public String getObsPago() {
        return obsPago;
    }

    public void setObsPago(String obsPago) {
        this.obsPago = obsPago;
    }
}

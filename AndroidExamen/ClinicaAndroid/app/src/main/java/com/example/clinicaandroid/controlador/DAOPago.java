package com.example.clinicaandroid.controlador;

import com.example.clinicaandroid.modelo.Cl_Conexion;
import com.example.clinicaandroid.modelo.Cl_Pago;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class DAOPago {
    private Connection conexion;

    public DAOPago() {
        conexion = new Cl_Conexion().getConexion();
    }

    public boolean guardarPago(int idAtencion, String obs_pago) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call SP_GUARDAR_PAGO_ATENCION(?,?)}";
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.setInt(1, idAtencion);
            cstmt.setString(2, obs_pago);
            cstmt.execute();
            return true;
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt != null) {
                cstmt.close();
            }
        }
    }

    public Cl_Pago obtenerPago(int idAtencion) throws Exception {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String sql = "{call SP_OBTENER_PAGO(?,?)}";
        List<Cl_Pago> listaPago = new ArrayList<>();
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.setInt(1, idAtencion);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
            Cl_Pago pago = new Cl_Pago();
            while (rs.next()) {
                pago.setIdAtencion(rs.getInt(1));
                pago.setCosto(rs.getInt(2));
                pago.setFecVenc(rs.getString(3));
                pago.setMonto_cancelar(rs.getInt(4));
                pago.setObsPago(rs.getString(5));
            }

            return pago;
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt != null) {
                cstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}

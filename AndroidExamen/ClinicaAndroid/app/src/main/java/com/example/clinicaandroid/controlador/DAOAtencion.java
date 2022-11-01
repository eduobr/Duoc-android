package com.example.clinicaandroid.controlador;

import com.example.clinicaandroid.modelo.Cl_Atencion;
import com.example.clinicaandroid.modelo.Cl_Conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class DAOAtencion {

    private Connection conexion;

    public DAOAtencion() {
        conexion = new Cl_Conexion().getConexion();
    }

    public List<Cl_Atencion> listarAtenciones() throws Exception {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Cl_Atencion> listaAtencion = new ArrayList<>();
        try {
            cstmt = conexion.prepareCall("{call SP_LISTAR_ATENCIONES(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Cl_Atencion atencion = new Cl_Atencion();
                atencion.setIdAtencion(rs.getInt(1));
                atencion.setRutMedico(rs.getString(2));
                atencion.setNombreMedico(rs.getString(3));
                atencion.setRutPaciente(rs.getString(4));
                atencion.setNombrePaciente(rs.getString(5));
                atencion.setFecAtencion(rs.getDate(6));
                atencion.setHoraAtencion(rs.getString(7));
                listaAtencion.add(atencion);
            }
            return listaAtencion;

        } catch (SQLException ex) {
            System.out.println("Error listar atenciones:" + ex);
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

    public List<Cl_Atencion> obtenerAtencion(String rut) throws Exception {
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            List<Cl_Atencion> listaAtencion = new ArrayList<>();
            cstmt = conexion.prepareCall("{call SP_OBTENER_ATENCION(?,?)}");
            if (validarRut(rut)) {
                String rutTxt = rut.substring(0, rut.length() - 2);
                cstmt.setInt(1, Integer.valueOf(rutTxt));
                cstmt.registerOutParameter(2, OracleTypes.CURSOR);
                cstmt.execute();
                rs = (ResultSet) cstmt.getObject(2);
                while (rs.next()) {
                    Cl_Atencion atencion = new Cl_Atencion();
                    atencion.setIdAtencion(rs.getInt(1));
                    atencion.setRutMedico(rs.getString(2));
                    atencion.setNombreMedico(rs.getString(3));
                    atencion.setRutPaciente(rs.getString(4));
                    atencion.setNombrePaciente(rs.getString(5));
                    atencion.setFecAtencion(rs.getDate(6));
                    atencion.setHoraAtencion(rs.getString(7));
                    listaAtencion.add(atencion);
                }
            }
            return listaAtencion;

        } catch (SQLException ex) {
            System.out.println("Error obtener la atención:" + ex);
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

    public boolean guardarAtencion(int costo, String med_rut, String pac_rut, Date fecVencimiento) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call SP_GUARDAR_ATENCION(?,?,?,?)}";
        int numRutMed = 0;
        int numRutPac = 0;
        if (validarRut(med_rut)) {
            numRutMed = Integer.valueOf(med_rut.substring(0, med_rut.length() - 2));
        } else {
            throw new Exception("El rut del medico ingresado no es valido");
        }
        if (validarRut(pac_rut)) {
            numRutPac = Integer.valueOf(pac_rut.substring(0, pac_rut.length() - 2));
        } else {
            throw new Exception("El rut del paciente ingresado no es valido");
        }
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.setInt(1, costo);
            cstmt.setInt(2, numRutMed);
            cstmt.setInt(3, numRutPac);
            cstmt.setDate(4, fecVencimiento);
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

    public boolean eliminar_atencion(int idAtencion) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call SP_ELIMINAR_ATENCION(?)}";
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.setInt(1, idAtencion);
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

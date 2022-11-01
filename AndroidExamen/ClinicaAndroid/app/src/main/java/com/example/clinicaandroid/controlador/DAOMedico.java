package com.example.clinicaandroid.controlador;

import com.example.clinicaandroid.modelo.Cl_Conexion;
import com.example.clinicaandroid.modelo.Cl_Medico;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class DAOMedico {

    private Connection conexion;

    public DAOMedico() {
        this.conexion = new Cl_Conexion().getConexion();
    }

    public int guardarMedico(Cl_Medico medico) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call SP_GUARDAR_MEDICO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try{
            String rut = medico.getRut();
            int numerosRut = Integer.valueOf(rut.substring(0,rut.length()-2));
            String dv = rut.substring(rut.length()-1);
            //System.out.println(numerosRut);
            cstmt = conexion.prepareCall(sql);
            cstmt.setInt(1,numerosRut);
            cstmt.setString(2,dv);
            cstmt.setString(3,medico.getpNombre());
            cstmt.setString(4,medico.getsNombre());
            cstmt.setString(5,medico.getaPaterno());
            cstmt.setString(6,medico.getaMaterno());
            cstmt.setLong(7,medico.getTelefono());
            cstmt.setInt(8,medico.getSueldoBase());
            cstmt.setDate(9, medico.getFecContrato());
            cstmt.setFloat(10,medico.getComision());
            cstmt.setString(11,medico.getUnidad());
            cstmt.setString(12,medico.getCargo());
            cstmt.setString(13,medico.getEspecialidad());
            cstmt.setInt(14,medico.getJefeRut());
            cstmt.registerOutParameter(15,OracleTypes.NUMBER);
            cstmt.execute();
            int resp = cstmt.getInt(15);
            return resp;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
        }
    }

    public int modificarMedico(Cl_Medico medico) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call SP_MODIFICAR_MEDICO(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try{
            cstmt = conexion.prepareCall(sql);
            String rut = medico.getRut();
            int numerosRut = Integer.valueOf(rut.substring(0,rut.length()-2));
            cstmt.setInt(1,numerosRut);
            cstmt.setString(2,medico.getpNombre());
            cstmt.setString(3,medico.getsNombre());
            cstmt.setString(4,medico.getaPaterno());
            cstmt.setString(5,medico.getaMaterno());
            cstmt.setLong(6,medico.getTelefono());
            cstmt.setInt(7,medico.getSueldoBase());
            //Date fecContrato = medico.getFecContrato();
            cstmt.setDate(8, medico.getFecContrato());
            cstmt.setFloat(9,medico.getComision());
            cstmt.setString(10,medico.getUnidad());
            cstmt.setString(11,medico.getCargo());
            cstmt.setString(12,medico.getEspecialidad());
            cstmt.setInt(13,medico.getJefeRut());
            cstmt.registerOutParameter(14,OracleTypes.NUMBER);
            cstmt.execute();
            int resp = cstmt.getInt(14);
            return resp;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
        }
    }

    public List<Cl_Medico> listarMedicos() throws Exception {
        CallableStatement cstmt = null;
        List<Cl_Medico> listaMedico;
        String sql  = "{call SP_LISTAR_MEDICOS(?)}";
        ResultSet rs = null;
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            listaMedico = new ArrayList<>();
            while (rs.next()){
                Cl_Medico medico = new Cl_Medico();
                medico.setRut(String.valueOf(rs.getInt(1))+'-'+rs.getString(2));
                medico.setpNombre(rs.getString(3));
                medico.setsNombre(rs.getString(4));
                medico.setaPaterno(rs.getString(5));
                medico.setaMaterno(rs.getString(6));
                medico.setTelefono(rs.getLong(7));
                medico.setSueldoBase(rs.getInt(8));
                medico.setFecContrato(rs.getDate(9));
                medico.setComision(rs.getFloat(10));
                medico.setUnidad(rs.getString(11));
                medico.setCargo(rs.getString(12));
                medico.setEspecialidad(rs.getString(13));
                medico.setJefeRut(rs.getInt(14));
                listaMedico.add(medico);
            }
            return listaMedico;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
            if (rs!=null){
                rs.close();
            }
        }
    }

    public boolean eliminarMedico(String rut) throws Exception {
        CallableStatement cstmt = null;
        try {
            cstmt = conexion.prepareCall("{call SP_ELIMINAR_MEDICO(?)}");
            int numerosRut = Integer.valueOf(rut);
            cstmt.setInt(1,numerosRut);
            cstmt.execute();
            return true;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
        }
    }

    public List<Cl_Medico> obtenerMedico(String rut) throws Exception {
        CallableStatement cstmt = null;
        List<Cl_Medico> listaMedico;
        String sql  = "{call SP_OBTENER_MEDICO(?,?)}";
        ResultSet rs = null;
        try {
            cstmt = conexion.prepareCall(sql);
            int numerosRut = Integer.valueOf(rut);
            cstmt.setInt(1,numerosRut);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
            listaMedico = new ArrayList<>();
            while (rs.next()){
                Cl_Medico medico = new Cl_Medico();
                medico.setRut(String.valueOf(rs.getInt(1))+'-'+rs.getString(2));
                medico.setpNombre(rs.getString(3));
                medico.setsNombre(rs.getString(4));
                medico.setaPaterno(rs.getString(5));
                medico.setaMaterno(rs.getString(6));
                medico.setTelefono(rs.getLong(7));
                medico.setSueldoBase(rs.getInt(8));
                medico.setFecContrato(rs.getDate(9));
                medico.setComision(rs.getFloat(10));
                medico.setUnidad(rs.getString(11));
                medico.setCargo(rs.getString(12));
                medico.setEspecialidad(rs.getString(13));
                medico.setJefeRut(rs.getInt(14));
                listaMedico.add(medico);
            }
            return listaMedico;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
            if (rs!=null){
                rs.close();
            }
        }
    }

    public List<String> obtenerEspecialidad() throws Exception {
        CallableStatement cstmt = null;
        List<String> lista;
        String sql  = "{call SP_SPINNER_ESP(?)}";
        ResultSet rs = null;
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            lista = new ArrayList<>();
            while (rs.next()){
                lista.add(rs.getString(1));
            }
            return lista;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
            if (rs!=null){
                rs.close();
            }
        }
    }

    public List<String> obtenerUnidad() throws Exception {
        CallableStatement cstmt = null;
        List<String> lista;
        String sql  = "{call SP_SPINNER_UNI(?)}";
        ResultSet rs = null;
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            lista = new ArrayList<>();
            while (rs.next()){
                lista.add(rs.getString(1));
            }
            return lista;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
            if (rs!=null){
                rs.close();
            }
        }
    }

    public List<String> obtenerCargo() throws Exception {
        CallableStatement cstmt = null;
        List<String> lista;
        String sql  = "{call SP_SPINNER_CAR(?)}";
        ResultSet rs = null;
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            lista = new ArrayList<>();
            while (rs.next()){
                lista.add(rs.getString(1));
            }
            return lista;
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
            if (rs!=null){
                rs.close();
            }
        }
    }


}

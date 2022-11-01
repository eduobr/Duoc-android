package com.example.clinicaandroid.controlador;

import android.telecom.Call;
import android.widget.ListView;

import com.example.clinicaandroid.modelo.Cl_Conexion;
import com.example.clinicaandroid.modelo.Cl_Paciente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

public class DAOPaciente {
    private Connection conexion;

    public DAOPaciente() {
        this.conexion= new Cl_Conexion().getConexion();
    }

    public int guardarPaciente(Cl_Paciente paciente) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call SP_GUARDAR_PACIENTE(?,?,?,?,?,?,?,?,?,?)}";
        try {
            String rut = paciente.getRut();
            String txtRut = rut.substring(0,rut.length()-2);
            int numerosRut = Integer.valueOf(rut.substring(0,rut.length()-2));
            String dv = rut.substring(rut.length()-1);
            cstmt=conexion.prepareCall(sql);
            cstmt.setInt(1,numerosRut);
            cstmt.setString(2,dv);
            cstmt.setString(3,paciente.getpNombre());
            cstmt.setString(4,paciente.getsNombre());
            cstmt.setString(5,paciente.getaPaterno());
            cstmt.setString(6,paciente.getaMaterno());
            Date fecNacimiento = paciente.getFecNacimiento();
            System.out.println(fecNacimiento.toString());
            cstmt.setDate(7, fecNacimiento);
            cstmt.setInt(8,paciente.getTelefono());
            cstmt.setString(9,paciente.getSalud());
            cstmt.registerOutParameter(10,OracleTypes.NUMBER);
            cstmt.execute();
            return cstmt.getInt(10);
        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt!=null){
                cstmt.close();
            }
        }
    }

    public boolean modificarPaciente(Cl_Paciente paciente) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call SP_MODIFICAR_PACIENTE(?,?,?,?,?,?,?,?,?)}";
        try {
            String rut = paciente.getRut();
            String txtRut = rut.substring(0,rut.length()-2);
            int numerosRut = Integer.valueOf(rut.substring(0,rut.length()-2));
            String dv = rut.substring(rut.length()-1);
            cstmt=conexion.prepareCall(sql);
            cstmt.setInt(1,numerosRut);
            cstmt.setString(2,dv);
            cstmt.setString(3,paciente.getpNombre());
            cstmt.setString(4,paciente.getsNombre());
            cstmt.setString(5,paciente.getaPaterno());
            cstmt.setString(6,paciente.getaMaterno());
            Date fecNacimiento = paciente.getFecNacimiento();
            System.out.println(fecNacimiento.toString());
            cstmt.setDate(7, fecNacimiento);
            cstmt.setInt(8,paciente.getTelefono());
            cstmt.setString(9,paciente.getSalud());
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



    public List<Cl_Paciente> listarPacientes() throws Exception {
        CallableStatement cstmt = null;
        List<Cl_Paciente> listaPacientes;
        String sql  = "{call SP_LISTAR_PACIENTES(?)}";
        ResultSet rs = null;
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            listaPacientes = new ArrayList<>();
            while (rs.next()){
                Cl_Paciente paciente = new Cl_Paciente();
                paciente.setRut(String.valueOf(rs.getInt(1))+'-'+rs.getString(2));
                paciente.setpNombre(rs.getString(3));
                paciente.setsNombre(rs.getString(4));
                paciente.setaPaterno(rs.getString(5));
                paciente.setaMaterno(rs.getString(6));
                paciente.setFecNacimiento(rs.getDate(7));
                //paciente.setFecNacimiento(String.valueOf(rs.getDate(7)));
                paciente.setTelefono(rs.getInt(8));
                paciente.setSalud(rs.getString(9));
                listaPacientes.add(paciente);
            }
            return listaPacientes;
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

    public List<Cl_Paciente> obtenerPaciente(String rut) throws Exception {
        CallableStatement cstmt = null;
        List<Cl_Paciente> listaPacientes;
        String sql  = "{call SP_OBTENER_PACIENTE(?,?)}";
        ResultSet rs = null;
        try {

            cstmt = conexion.prepareCall(sql);
            cstmt.setInt(1,Integer.valueOf(rut));
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(2);
            listaPacientes = new ArrayList<>();
            while (rs.next()){
                Cl_Paciente paciente = new Cl_Paciente();
                paciente.setRut(String.valueOf(rs.getInt(1))+'-'+rs.getString(2));
                paciente.setpNombre(rs.getString(3));
                paciente.setsNombre(rs.getString(4));
                paciente.setaPaterno(rs.getString(5));
                paciente.setaMaterno(rs.getString(6));
                paciente.setFecNacimiento(rs.getDate(7));
                //paciente.setFecNacimiento(String.valueOf(rs.getDate(7)));
                paciente.setTelefono(rs.getInt(8));
                paciente.setSalud(rs.getString(9));
                listaPacientes.add(paciente);
            }
            return listaPacientes;
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

    public List<String> listarSaludSpinner() throws Exception {
        CallableStatement cstmt = null;
        String sql  = "{call SP_OBTENER_SALUD_SPINNER(?)}";
        ResultSet rs = null;
        List<String> listaSalud;
        try {
            listaSalud = new ArrayList<>();
            cstmt = conexion.prepareCall(sql);
            cstmt.registerOutParameter(1,OracleTypes.CURSOR);
            cstmt.executeUpdate();
            rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()){
                listaSalud.add(rs.getString(1));
            }
            return listaSalud;
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

    public boolean eliminarPaciente(String rut) throws Exception {
        CallableStatement cstmt = null;
        try {
            cstmt = conexion.prepareCall("{call SP_ELIMINAR_PACIENTE(?)}");
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

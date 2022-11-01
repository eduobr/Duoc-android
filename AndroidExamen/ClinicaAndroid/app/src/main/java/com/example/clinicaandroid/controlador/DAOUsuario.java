package com.example.clinicaandroid.controlador;

import com.example.clinicaandroid.modelo.Cl_Conexion;
import com.example.clinicaandroid.modelo.Cl_Usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class DAOUsuario {
    private Connection conexion;

    public DAOUsuario() {
        this.conexion = new Cl_Conexion().getConexion();
    }

    public int iniciar_sesion(Cl_Usuario usuario) throws Exception {
        CallableStatement cstmt = null;
        String sql = "{call SP_LOGIN_USUARIO(?,?,?)}";
        try {
            cstmt = conexion.prepareCall(sql);
            cstmt.setString(1, usuario.getUsuario());
            cstmt.setString(2, usuario.getPass());
            cstmt.registerOutParameter(3, OracleTypes.NUMBER);
            cstmt.execute();
            int resp = cstmt.getInt(3);
            return resp;
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            conexion = new Cl_Conexion().cerrarConexion();
            if (cstmt != null) {
                cstmt.close();
            }
        }
    }
}

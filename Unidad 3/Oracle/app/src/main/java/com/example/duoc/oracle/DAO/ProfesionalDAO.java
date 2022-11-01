package com.example.duoc.oracle.DAO;

import com.example.duoc.oracle.Conexion.Conexion;
import com.example.duoc.oracle.Modelo.Profesional;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProfesionalDAO {

    Conexion c=new Conexion();
    Connection cone= c.obtenerConexion();

    public void insertarProfesional(Profesional p){
        try {
            CallableStatement st=cone.prepareCall("{call SP_INSERTAR(?,?,?,?)}");
            st.setString(1, p.getRut());
            st.setInt(2, p.getIdComuna());
            st.setInt(3, p.getIdProfesion());
            st.setString(4, p.getNombre());
            st.execute();
            st.close();
            System.out.println("PROFESIONAL GUARDADO");
        }catch (SQLException ex){
            System.out.println("ERROR: " + ex);
        }
    }



}

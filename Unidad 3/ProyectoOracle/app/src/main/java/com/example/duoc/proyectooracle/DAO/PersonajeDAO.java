package com.example.duoc.proyectooracle.DAO;

import com.example.duoc.proyectooracle.Cl_Conexion;
import com.example.duoc.proyectooracle.Modelo.Cl_Personaje;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonajeDAO {
    public void insertar(Cl_Personaje personaje){
        try {
            Cl_Conexion conn = new Cl_Conexion("10.0.2.2","xe","ANDROID","ANDROID");
            Connection con=conn.getConexion();
            CallableStatement statement = con.prepareCall("{call SP_INSERTAR_PERSONAJE(?,?,?,?)}");
            statement.setString(1,personaje.getNombre());
            statement.setString(2,personaje.getSexo());
            statement.setString(3,personaje.getOrigen());
            statement.setInt(4,personaje.getTipo());
            statement.execute();
            System.out.println("Exito");
        }catch (SQLException ex){
            System.out.println("ERROR:"+ex);
        }
    }
}

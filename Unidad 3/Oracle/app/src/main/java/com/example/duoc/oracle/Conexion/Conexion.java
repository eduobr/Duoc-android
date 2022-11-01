package com.example.duoc.oracle.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public Connection cone;

    public Conexion(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            cone=DriverManager.getConnection("jdbc:oracle:thin:@10.0.2.2:1521:xe", "android", "android");
            System.out.println("CONEXIÓN EXITOSA");
        }catch(Exception ex){
            System.out.println("ERROR: " + ex);
        }
    }

    public Connection obtenerConexion(){
        return cone;
    }

}

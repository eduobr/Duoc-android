package com.example.clinicaandroid.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cl_Conexion {
    public String driver, url, ip, bd, usr, pass;
    public Connection conexion;

    public Cl_Conexion(){
        driver = "oracle.jdbc.driver.OracleDriver";
        url = "jdbc:oracle:thin:@10.0.2.2:1521:XE";
        usr = "CLINICA_ANDROID";
        pass = "CLINICA";
            try {
                Class.forName(driver).newInstance();
                conexion= DriverManager.getConnection(url,usr,pass);
                System.out.println("Conexion Exitosa");
            }catch (Exception ex){
                System.out.println("Error al conectar:"+ex);
            }
    }

    public Connection getConexion(){
        return conexion;
    }

    public Connection cerrarConexion() throws SQLException {
        conexion.close();
        conexion = null;
        return conexion;
    }


}

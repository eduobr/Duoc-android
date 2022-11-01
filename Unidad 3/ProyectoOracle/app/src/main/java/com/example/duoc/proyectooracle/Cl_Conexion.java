package com.example.duoc.proyectooracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cl_Conexion {
    public String driver, url, ip, bd, usr, pass;
    public Connection connection;

    public Cl_Conexion(String ip, String bd, String usr, String pass)
    {
        driver="oracle.jdbc.driver.OracleDriver";
        this.bd = bd;
        this.usr = usr;
        this.pass = pass;
        url=new String("jdbc:oracle:thin:@"+ip+":1521:"+bd);
        try{
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url,usr,pass);
            System.out.println("Conexion exitosa a la base de datos: "+bd);
        }catch(Exception ex){
            System.out.println("Error en la conexion con la base de datos: "+bd+" "+ex);
        }
        //Al llamar a la conexion se le debe dar la ip:10.0.2.2
    }
    public Connection getConexion(){
        return connection;
    }

    public Connection cerrarConexion() throws SQLException {
        connection.close();
        connection = null;
        return  connection;
    }


}

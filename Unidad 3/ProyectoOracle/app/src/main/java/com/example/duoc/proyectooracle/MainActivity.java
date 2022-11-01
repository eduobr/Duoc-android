package com.example.duoc.proyectooracle;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duoc.proyectooracle.DAO.PersonajeDAO;
import com.example.duoc.proyectooracle.Modelo.Cl_Personaje;

public class MainActivity extends AppCompatActivity {
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThreadPolicy policy =new ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cl_Conexion conexion = new Cl_Conexion("10.0.2.2","XE","ANDROID","ANDROID");
                PersonajeDAO dao = new PersonajeDAO();
                Cl_Personaje p = new Cl_Personaje();

            }
        });
    }
}

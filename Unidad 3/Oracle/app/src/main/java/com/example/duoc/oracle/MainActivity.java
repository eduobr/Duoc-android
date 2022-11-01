package com.example.duoc.oracle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duoc.oracle.DAO.ProfesionalDAO;
import com.example.duoc.oracle.Modelo.Profesional;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar;
    ProfesionalDAO dao=new ProfesionalDAO();
    Profesional pro=new Profesional();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar=findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargo datos al profesional
                pro.setRut("VALOR CAJA TEXTO RUT");
                //valor de la caja comuna y profesion
                pro.setIdComuna(1);
                pro.setIdProfesion(1);
                pro.setNombre("VALOR CAJA DE TEXTO NOMBRE");
                dao.insertarProfesional(pro);

            }
        });


    }
}

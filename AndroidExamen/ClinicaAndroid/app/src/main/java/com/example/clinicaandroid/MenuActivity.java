package com.example.clinicaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button btnPaciente,btnMedico,btnAtencion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPaciente = findViewById(R.id.btnMenuPaciente);
        btnAtencion = findViewById(R.id.btnMenuAtencion);
        btnMedico = findViewById(R.id.btnMenuMedico);

        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PacienteActivity.class);
                startActivity(i);
            }
        });

        btnMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MedicoActivity.class);
                startActivity(i);
            }
        });

        btnAtencion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AtencionActivity.class);
                startActivity(i);
            }
        });
    }
}

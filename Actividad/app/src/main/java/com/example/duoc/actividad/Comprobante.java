package com.example.duoc.actividad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Comprobante extends AppCompatActivity {

    TextView lblCliente;
    TextView lblRut;
    TextView lblRazon;
    TextView lblNombre;
    TextView lblEmail;
    TextView lblRegion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante);
        lblCliente=findViewById(R.id.cliente);
        lblRut = findViewById(R.id.rut);
        lblNombre=findViewById(R.id.nombre);
        lblRazon=findViewById(R.id.razon);
        lblEmail=findViewById(R.id.correo);
        lblRegion = findViewById(R.id.region);
        lblCliente.setText(getIntent().getStringExtra("cliente"));
        lblNombre.setText(getIntent().getStringExtra("nombre"));
        lblRut.setText(getIntent().getStringExtra("rut"));
        lblRazon.setText(getIntent().getStringExtra("razon"));
        if (lblRazon.getText().toString().isEmpty()){
            lblRazon.setVisibility(View.GONE);
            TextView labelRazon = findViewById(R.id.labelRazon);
            labelRazon.setVisibility(View.GONE);
        }
        lblEmail.setText(getIntent().getStringExtra("email"));
        lblRegion.setText(getIntent().getStringExtra("region"));
    }
}

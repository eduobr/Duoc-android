package com.example.lc13007xx.clase2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtRut;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRut = findViewById(R.id.txtRut);
        btnEnviar= findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toma el contexto y lo manda al otro activity
                Intent i = new Intent(getApplicationContext(), VoucherActivity.class);
                //colocamos nombre de variable y valor, lo transformamos a String
                i.putExtra("rut",txtRut.getText().toString());
                //inicia el activity y lo manda haci allá
                startActivity(i);
            }
        });
    }
}

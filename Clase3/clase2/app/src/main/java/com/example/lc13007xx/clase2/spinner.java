package com.example.lc13007xx.clase2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class spinner extends AppCompatActivity {
    Spinner spiBanco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        spiBanco =(Spinner) findViewById(R.id.spinnerBanco);
        //String.valueOf(spiBanco.getSelectedItem()) para transformarlo a string
        String bancoSeleccionado=spiBanco.getSelectedItem().toString();
        if (bancoSeleccionado.equals("Seleccionar")){
            Toast.makeText(getApplicationContext(),"Debe seelccionar un banco",Toast.LENGTH_SHORT).show();
        }

        String[] bancos={"Banco de Chile","Estado","Itau","Santander"};
        //ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,bancos);
        //spiBanco.setAdapter(adapter);
        spiBanco.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,bancos));
    }
}

package com.example.duoc.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgCliente;
    RadioButton rbNatural;
    RadioButton rbEmpresa;
    TextView txtRut;
    TextView txtRazon;
    TextView txtNombre;
    TextView txtEmail;
    CheckBox chkVerificar;
    Button btnLimpiar;
    Spinner spiRegion;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgCliente=findViewById(R.id.rgCliente);
        txtRazon=findViewById(R.id.txtRazonSocial);
        txtNombre = findViewById(R.id.txtNombre);
        txtEmail = findViewById(R.id.txtCorreo);
        chkVerificar = findViewById(R.id.chkVerificar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        txtRut = findViewById(R.id.txtRut);
        rbNatural = findViewById(R.id.rbNatural);
        rbEmpresa = findViewById(R.id.rbEmpresa);
        spiRegion = findViewById(R.id.spiRegion);
        btnGuardar = findViewById(R.id.btnGuardar);
        rbNatural.setChecked(true);
        txtRazon.setEnabled(false);

        String[] regiones = {"Seleccione",
                "I de Tarapacá",
                "II de Antofagasta",
                "III de Atacama",
                "IV de Coquimbo",
                "V de Valparaíso",
                "VI del Libertador General Bernardo O’Higgins",
                "VII del Maule",
                "VIII de Concepción",
                "IX de la Araucanía",
                "X de Los Lagos",
                "XI de Aysén del General Carlos Ibañez del Campo",
                "XII de Magallanes y de la Antártica Chilena",
                "Metropolitana de Santiago",
                "XIV de Los Ríos",
                "XV de Arica y Parinacota",
                "XVI del Ñuble"};

        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,regiones);
        spiRegion.setAdapter(adapter);

        rgCliente.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbNatural:
                        txtRazon.setEnabled(false);
                        break;
                    case R.id.rbEmpresa:
                        txtRazon.setEnabled(true);
                        break;
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbNatural.setChecked(true);
                txtRut.setText("");
                txtRazon.setText("");
                txtNombre.setText("");
                txtEmail.setText("");
                chkVerificar.setChecked(false);
                spiRegion.setSelection(0);

                txtRut.requestFocus();

                //para eliminar todos los focus
                /*try {
                    getCurrentFocus().clearFocus();
                }catch (Exception e){
                    System.out.print(e.getMessage());
                }*/

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Comprobante.class);
                int idSeleccionada = rgCliente.getCheckedRadioButtonId();
                RadioButton rbSeleccionado = findViewById(idSeleccionada);
                if (txtRut.getText().toString().trim().isEmpty()){
                    txtRut.setError("El rut no puede estar vacio");
                    txtRut.requestFocus();
                }else if (rbEmpresa.isChecked() && txtRazon.getText().toString().trim().isEmpty()){
                    txtRazon.setError("La razon social no puede estar vacia");
                }else if (txtNombre.getText().toString().trim().isEmpty()){
                    txtNombre.setError("El nombre no puede esta vacio");
                    txtNombre.requestFocus();
                }else if (txtNombre.getText().toString().trim().length()<=2){
                    txtNombre.setError("El nombre no puede tener menos de dos caracteres");
                    txtNombre.requestFocus();
                }else if (txtEmail.getText().toString().trim().isEmpty()){
                    txtEmail.setError("El email no puede estar vacio");
                    txtEmail.requestFocus();
                }else if (spiRegion.getSelectedItem().toString().equals("Seleccione")){
                    Toast.makeText(getApplicationContext(),"Debe seleccionar una region",Toast.LENGTH_LONG).show();
                    spiRegion.requestFocus();
                }else{
                    i.putExtra("cliente",rbSeleccionado.getText().toString());
                    i.putExtra("nombre",txtNombre.getText().toString());
                    i.putExtra("rut",txtRut.getText().toString());
                    i.putExtra("razon",txtRazon.getText().toString());
                    i.putExtra("email",txtEmail.getText().toString());
                    i.putExtra("region",spiRegion.getSelectedItem().toString());
                    startActivity(i);
                }
            }
        });

    }
}

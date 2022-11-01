package com.tornade.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Empleado extends AppCompatActivity {

    TextView txtRut;
    TextView txtNombre;
    TextView txtCargo;
    Button btnGuardarEmp;
    Button btnVolver;
    RadioButton rbHoraSi;
    RadioButton rbHoraNo;
    RadioGroup rgHorasExtra;
    Spinner spiAfp;
    RadioButton rbIndefinido;
    RadioButton rbFijo;
    TextView txtHorasExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        txtRut = findViewById(R.id.txtRut);
        txtNombre = findViewById(R.id.txtNombre);
        txtCargo = findViewById(R.id.txtCargo);
        btnGuardarEmp = findViewById(R.id.btnGuardarEmp);
        rbHoraSi = findViewById(R.id.rbHorasSi);
        rbHoraNo = findViewById(R.id.rbHorasNo);
        rbIndefinido = findViewById(R.id.rbIndefinido);
        rbFijo = findViewById(R.id.rbFijo);
        btnVolver = findViewById(R.id.btnVolver);
        rgHorasExtra = findViewById(R.id.rgHorasExtra);
        txtHorasExtra = findViewById(R.id.txtHorasExtra);
        spiAfp = findViewById(R.id.spiAfp);
        rbHoraNo.setChecked(true);


        rgHorasExtra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbHorasSi:
                        txtHorasExtra.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbHorasNo:
                        txtHorasExtra.setVisibility(View.GONE);
                        break;
                }
            }
        });

        btnGuardarEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int horas = Integer.parseInt(txtHorasExtra.getText().toString());
                /*if (txtRut.getText().toString().trim().isEmpty()) {
                    txtRut.setError("El rut no puede estar vacio");
                    txtRut.requestFocus();
                } else if (!validarRut(txtRut.getText().toString())) {
                    txtRut.setError("Debe escribir un rut valido");
                    txtRut.requestFocus();
                }else if (txtNombre.getText().toString().trim().isEmpty()){
                    txtNombre.setError("El nombre no puede estar vacio");
                    txtNombre.requestFocus();
                }else if (txtNombre.getText().toString().trim().length()<2){
                    txtNombre.setError("El nombre no puede tener menos de dos caracteres");
                    txtNombre.requestFocus();
                }else if (txtHorasExtra.getText().toString()<0){
                    txtHorasExtra.setError("Las horas extra debe ser mayor a 0");
                    txtHorasExtra.requestFocus();
                }else{
                    Intent i = new Intent(getApplicationContext(),Haberes.class);
                    //i.putExtra("horasExtra",horasExtra);
                    startActivity(i);
                }*/

                Intent i = new Intent(getApplicationContext(),Haberes.class);
                i.putExtra("horasExtra",horas);
                i.putExtra("afp", spiAfp.getSelectedItem().toString());
                if (rbIndefinido.isChecked()){
                    i.putExtra("indefinido","seguro");
                }else{
                    i.putExtra("indefinido","");
                }
                startActivity(i);
            }

            private boolean validarRut(String rut) {
                boolean validacion = false;
                try {
                    rut = rut.toUpperCase();
                    rut = rut.replace(".", "");
                    rut = rut.replace("-", "");
                    int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

                    char dv = rut.charAt(rut.length() - 1);

                    int m = 0, s = 1;
                    for (; rutAux != 0; rutAux /= 10) {
                        s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
                    }
                    if (dv == (char) (s != 0 ? s + 47 : 75)) {
                        validacion = true;
                    }

                } catch (java.lang.NumberFormatException e) {

                } catch (Exception e) {

                }
                return validacion;
            }
        });



        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}

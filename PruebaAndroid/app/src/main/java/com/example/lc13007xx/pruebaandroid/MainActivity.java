package com.tornade.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtRut;
    TextView txtNombre;
    TextView txtDireccion;
    Button btnGuardarEmpleador;
    Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRut = findViewById(R.id.txtRut);
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        btnGuardarEmpleador = findViewById(R.id.btnGuardarEmpleador);
        btnLimpiar = findViewById(R.id.btnLimpiarEmpleador);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtRut.setText("");
                txtNombre.setText("");
                txtDireccion.setText("");

                txtRut.requestFocus();


            }
        });

        btnGuardarEmpleador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                }else if (txtDireccion.getText().toString().trim().isEmpty()){
                    txtDireccion.setError("La dirección no puede estar vacia");
                    txtDireccion.requestFocus();
                }else if (txtDireccion.getText().toString().trim().length()<3){
                    txtDireccion.setError("La dirección no puede tener menos de dos caracteres");
                    txtDireccion.requestFocus();
                }else{
                    Intent i = new Intent(getApplicationContext(),Empleado.class);
                    startActivity(i);
                }*/
                Intent i = new Intent(getApplicationContext(),Empleado.class);
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
    }


}

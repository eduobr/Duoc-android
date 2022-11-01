package com.example.clinicaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaandroid.controlador.DAOAtencion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuardarAtencionActivity extends AppCompatActivity {
    TextView txtMedRut,txtPacRut,txtFecAtencion,txtCosto,txtFecVencPago;

    Button btnGuardarAte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_atencion);
        btnGuardarAte = findViewById(R.id.btnGrdAte);

        txtMedRut = findViewById(R.id.txtRutMedico);
        txtPacRut = findViewById(R.id.txtRutPaciente);
        //txtFecAtencion = findViewById(R.id.txtFechaAtencion);
        txtCosto = findViewById(R.id.txtCostoAtencion);
        txtFecVencPago = findViewById(R.id.txtFecVecAtencion);

        btnGuardarAte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern patFecha = Pattern.compile("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$");
                Pattern patCosto = Pattern.compile("([1-8][0-9]{3}|9[0-8][0-9]{2}|99[0-8][0-9]|999[0-9]|[1-7][0-9]{4}|80000)");
                Matcher mat = null;
                String rutMed = txtMedRut.getText().toString();
                String rutPac = txtPacRut.getText().toString();

                //String fechaAtencion = txtFecAtencion.getText().toString();
                mat = patFecha.matcher(txtFecVencPago.getText().toString());
                String fechaVecPago = "";
                if (mat.matches()){
                    fechaVecPago = txtFecVencPago.getText().toString();
                }else{
                    Toast.makeText(getApplicationContext(),"La fecha de vencimiento no tiene el formato correcto",Toast.LENGTH_LONG).show();
                    txtFecVencPago.requestFocus();
                    return;
                }
                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    //Date formatFecAtencion = format.parse(fechaAtencion);
                    Date formatFecVencPago = format.parse(fechaVecPago);
                    //java.sql.Date fecAtencion = new java.sql.Date(formatFecAtencion.getTime());
                    java.sql.Date fecVecPago= new java.sql.Date(formatFecVencPago.getTime());
                    int costo  = 0;
                    mat = patCosto.matcher(txtCosto.getText().toString());
                    if (mat.matches()) {
                        costo = Integer.valueOf(txtCosto.getText().toString());
                        if (costo>=1000 ) {
                            costo = Integer.valueOf(txtCosto.getText().toString());
                        }else{
                            Toast.makeText(getApplicationContext(),"El costo debe ser mayor a 1000",Toast.LENGTH_LONG).show();
                            txtCosto.requestFocus();
                            return;
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"El costo no posee el formato correcto",Toast.LENGTH_LONG).show();
                        txtCosto.requestFocus();
                        return;
                    }
                    DAOAtencion daoAtencion = new DAOAtencion();
                    boolean resp=daoAtencion.guardarAtencion(costo,rutMed,rutPac,fecVecPago);
                    if (resp==true){
                        txtMedRut.setText("");
                        txtPacRut.setText("");
                        txtCosto.setText("");
                        txtFecVencPago.setText("");
                        Toast.makeText(getApplicationContext(),"La atencion se ingreso correctamente",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"La atencion no se pudo ingresar",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Error: "+e,Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}

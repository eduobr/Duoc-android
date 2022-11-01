package com.example.clinicaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaandroid.controlador.DAOPago;
import com.example.clinicaandroid.modelo.Cl_Pago;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuardarPagoActivity extends AppCompatActivity {
    Button btnGuardarPag;
    TextView txtCostoPag, txtFecVecPag, txtMontoPag, txtObsPag;
    int cuenta=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_pago);
        txtCostoPag = findViewById(R.id.txtCostoPag);
        txtFecVecPag = findViewById(R.id.txtFechaVecPag);
        txtMontoPag = findViewById(R.id.txtMontoPag);
        txtObsPag = findViewById(R.id.txtObsPag);

        btnGuardarPag = findViewById(R.id.btnGrdPago);

        final int idAtencion = getIntent().getIntExtra("idAtencion", 0);
        DAOPago daoPago = new DAOPago();
        DecimalFormat formatter = new DecimalFormat("$###,###,###");
        String obsPago="";
        try {
            Cl_Pago pago = daoPago.obtenerPago(idAtencion);
            txtCostoPag.setText(formatter.format(pago.getCosto()));
            txtFecVecPag.setText(pago.getFecVenc());
            txtMontoPag.setText(formatter.format(pago.getMonto_cancelar()));
            obsPago = pago.getObsPago();
            txtObsPag.setText(obsPago);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!txtCostoPag.getText().equals("")){
            txtCostoPag.setEnabled(false);
            cuenta++;
        }
        if (!txtFecVecPag.getText().equals("")){
            txtFecVecPag.setEnabled(false);
            cuenta++;
        }
        if (!txtMontoPag.getText().equals("")){
            txtMontoPag.setEnabled(false);
            cuenta++;
        }
        if (obsPago!=null){
            txtObsPag.setEnabled(false);
            cuenta++;
        }
        if (cuenta==4){
            btnGuardarPag.setEnabled(false);
        }



        btnGuardarPag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAOPago daoPago = new DAOPago();
                try {
                    Pattern pat = Pattern.compile("[a-zA-Z ]{2,254}");
                    Matcher mat = pat.matcher(txtObsPag.getText());
                    if (!mat.matches()){
                        Toast.makeText(getApplicationContext(),"La observación de pago debe tener al menos 3 caracteres",Toast.LENGTH_LONG);
                        txtObsPag.setError("La observación de pago debe tener al menos 3 caracteres");
                        txtObsPag.requestFocus();
                        return;
                    }
                    boolean resp = daoPago.guardarPago(idAtencion, txtObsPag.getText().toString());
                    if (resp==true){
                        Toast.makeText(getApplicationContext(), "Pago Guardado", Toast.LENGTH_LONG).show();
                        txtObsPag.setEnabled(false);
                        btnGuardarPag.setEnabled(false);
                        // Timer
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        Intent i = new Intent(getApplicationContext(), AtencionActivity.class);
                                        startActivity(i);
                                    }
                                },
                                3000
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

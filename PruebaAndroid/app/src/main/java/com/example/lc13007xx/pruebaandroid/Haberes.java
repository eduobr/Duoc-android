package com.tornade.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Optional;

public class Haberes extends AppCompatActivity {

    TextView txtSueldoBase;
    TextView txtBono;
    TextView txtGratificacion;
    TextView txtHorasExtras;
    TextView txtTotalImponible;
    TextView txtColacion;
    TextView txtMovilizacion;
    TextView txtTotalNoImponible;
    Button btnSiguiente;
    int horas;
    String afp;
    String contrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haberes);

         txtSueldoBase= findViewById(R.id.txtSueldoBase);
         txtBono= findViewById(R.id.txtBono);
         txtGratificacion= findViewById(R.id.txtGratificacion);
         txtHorasExtras= findViewById(R.id.txtHorasExtra);
         txtTotalImponible= findViewById(R.id.txtTotalImponible);
         txtColacion= findViewById(R.id.txtColacion);
         txtMovilizacion= findViewById(R.id.txtMovilizacion);
         txtTotalImponible= findViewById(R.id.txtTotalImponible);
         txtTotalNoImponible = findViewById(R.id.txtTotalNoImponible);
         btnSiguiente = findViewById(R.id.btnSiguienteHaberes);
         horas = getIntent().getIntExtra("horasExtra",0)*5000;

         afp = getIntent().getStringExtra("afp");
         contrato = getIntent().getStringExtra("indefinido");

         txtHorasExtras.setText(String.valueOf(horas));

        /*txtSueldoBase.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 int total = ((txtBono.getText().toString().equals(""))?0:Integer.parseInt(txtBono.getText().toString()));
                 total+= ((txtGratificacion.getText().toString().equals(""))?0:Integer.parseInt(txtGratificacion.getText().toString()));
                 total+=((txtSueldoBase.getText().toString().equals(""))?0:Integer.parseInt(txtSueldoBase.getText().toString()));
                 total+=horas;
                 int total = horas+ Optional.Integer.parseInt(txtBono.getText().toString())+Integer.parseInt(txtGratificacion.getText().toString())
                         +Integer.parseInt(txtSueldoBase.getText().toString());
                 txtTotalImponible.setText(String.valueOf(total));
             }

             @Override
             public void afterTextChanged(Editable editable) {

             }
         });*/

         /*txtBono.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 int total = ((txtBono.getText().toString().equals(""))?0:Integer.parseInt(txtBono.getText().toString()));
                 total+= ((txtGratificacion.getText().toString().equals(""))?0:Integer.parseInt(txtGratificacion.getText().toString()));
                 total+=((txtSueldoBase.getText().toString().equals(""))?0:Integer.parseInt(txtSueldoBase.getText().toString()));
                 total+=horas;
                 int total = horas+ Optional.Integer.parseInt(txtBono.getText().toString())+Integer.parseInt(txtGratificacion.getText().toString())
                         +Integer.parseInt(txtSueldoBase.getText().toString());
                 txtTotalImponible.setText(String.valueOf(total));
             }

             @Override
             public void afterTextChanged(Editable editable) {

    }
});*/

         Total(txtSueldoBase,"Imponible");
         Total(txtGratificacion,"Impponible");
         Total(txtBono,"Imponinble");
         Total(txtColacion,"No Imponinble");
         Total(txtMovilizacion,"No Imponinble");

         btnSiguiente.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 int totImponible = Integer.parseInt(txtTotalImponible.getText().toString());
                 int totNoImponible = Integer.parseInt(txtTotalNoImponible.getText().toString());
                 Intent i = new Intent(getApplicationContext(),Descuentos.class);
                 i.putExtra("afp", afp);
                 i.putExtra("imponible",totImponible);
                 i.putExtra("noImponible",totNoImponible);
                 i.putExtra("seguro",contrato);
                 startActivity(i);
             }
         });


         //Toast.makeText(getApplicationContext(),horas+"",Toast.LENGTH_LONG);
    }

    public int Total(TextView campo,String tipo){
        int total = 0;
        if (tipo=="Imponible"){
            campo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int total = ((txtBono.getText().toString().equals(""))?0:Integer.parseInt(txtBono.getText().toString()));
                    total+= ((txtGratificacion.getText().toString().equals(""))?0:Integer.parseInt(txtGratificacion.getText().toString()));
                    total+=((txtSueldoBase.getText().toString().equals(""))?0:Integer.parseInt(txtSueldoBase.getText().toString()));
                    total+=horas;
                 /*int total = horas+ Optional.Integer.parseInt(txtBono.getText().toString())+Integer.parseInt(txtGratificacion.getText().toString())
                         +Integer.parseInt(txtSueldoBase.getText().toString());*/
                    txtTotalImponible.setText(String.valueOf(total));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }else{
            campo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int total = ((txtColacion.getText().toString().equals(""))?0:Integer.parseInt(txtColacion.getText().toString()));
                    total += ((txtMovilizacion.getText().toString().equals(""))?0:Integer.parseInt(txtMovilizacion.getText().toString()));
                    txtTotalNoImponible.setText(String.valueOf(total));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        return total;
    }
}

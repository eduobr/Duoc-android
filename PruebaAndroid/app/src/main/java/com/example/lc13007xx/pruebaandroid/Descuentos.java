package com.tornade.prueba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Descuentos extends AppCompatActivity {
    TextView txtCotizacion;
    TextView txtSocial;
    TextView txtCesantia;
    TextView txtTotalDesc;
    TextView txtTotalNoImp;
    TextView txtLiquido;
    LinearLayout lyCesantia;
    Button btnListo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descuentos);

        txtCotizacion = findViewById(R.id.txtCotizacion);
        txtSocial = findViewById(R.id.txtSocial);
        txtCesantia = findViewById(R.id.txtCesantia);
        txtTotalDesc = findViewById(R.id.txtTotalDesc);
        txtTotalNoImp = findViewById(R.id.txtTotalNoImp);
        txtLiquido = findViewById(R.id.txtLiquido);
        lyCesantia = findViewById(R.id.lyCesantia);
        btnListo = findViewById(R.id.btnListoDescuento);
        String afp = getIntent().getStringExtra("afp");
        int totalImp = getIntent().getIntExtra("imponible",0);
        int totalNoImp = getIntent().getIntExtra("noImponible",0);
        String seguro = getIntent().getStringExtra("seguro");

        double cotizacion=0;
        if (afp.equals("Capital")){
            cotizacion = totalImp*0.1144;
        }else if(afp.equals("Cuprum")){
            cotizacion = totalImp*0.1144;
        }else if(afp.equals("Habitat")){
            cotizacion = totalImp*0.1127;
        }else if(afp.equals("Plan Vital")){
            cotizacion = totalImp*0.1116;
        }else if(afp.equals("Provida")){
            cotizacion = totalImp*0.1105;
        }else if(afp.equals("Modelo")){
            cotizacion = totalImp*0.1077;
        }

        double social = totalImp*0.07;
        double totalDesc=0;



        if (seguro.equals("")){
            lyCesantia.setVisibility(View.GONE);
            totalDesc = cotizacion+social;
            txtTotalDesc.setText(String.valueOf(totalDesc));
        }else{
            double cesantia = totalImp*0.006;
            txtCesantia.setText(String.valueOf(cesantia));
            totalDesc = cotizacion+social+cesantia;
            txtTotalDesc.setText(String.valueOf(totalDesc));
        }

        txtCotizacion.setText(String.valueOf(cotizacion));
        txtSocial.setText(String.valueOf(social));
        txtTotalNoImp.setText(String.valueOf(totalNoImp));

        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Liquidacion.class);
                startActivity(i);
            }
        });


    }
}

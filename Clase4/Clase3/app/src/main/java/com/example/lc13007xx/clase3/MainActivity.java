package com.example.lc13007xx.clase3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgSexo;
    RadioButton rbMasculino, rbFemenino;
    TextView lblSexo;
    Button btnSexo;
    private View linearCuadro;
    CheckBox cbConfirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgSexo = findViewById(R.id.rgSexo);
        rbMasculino = findViewById(R.id.rbMasculino);
        rbFemenino = findViewById(R.id.rbFemenino);
        btnSexo = findViewById(R.id.btnSexo);
        lblSexo = findViewById(R.id.lblSexo);
        linearCuadro = findViewById(R.id.linearCuadro);
        cbConfirmacion = findViewById(R.id.cbConfirmacion);

        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Boolean b;
                switch (checkedId){
                    case R.id.rbFemenino:
                        lblSexo.setText("Mujer");
                        b=true;
                        linearCuadro.setVisibility(b ? View.VISIBLE: View.GONE);
                        break;
                    case R.id.rbMasculino:
                        lblSexo.setText("Hombre");
                        b=false;
                        linearCuadro.setVisibility(b ? View.VISIBLE: View.GONE);
                        break;

                }

            }
        });
        cbConfirmacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbConfirmacion.isChecked()) {
                    Toast.makeText(getApplicationContext(), "CONFIRMADO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void metodo(View v){
        /*if (rbFemenino.isChecked()){
            lblSexo.setText("Es Masculino");
        }else{
            lblSexo.setText("Es Femenino");
        }*/
        if (cbConfirmacion.isChecked()){
            Toast.makeText(getApplicationContext(),"CONFIRMADO",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"DEBES CONFIRMAR ANTES DE CONTINUAR",Toast.LENGTH_SHORT).show();
        }
    }
}

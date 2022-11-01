package com.example.clinicaandroid;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaandroid.controlador.DAOUsuario;
import com.example.clinicaandroid.modelo.Cl_Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button btnIngresar;
    TextView txtUsuario, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitNetwork().build();
        StrictMode.setThreadPolicy(policy);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPass);


        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cl_Usuario usuario = new Cl_Usuario();
                Pattern pat = Pattern.compile("[a-zA-Z0-9]{3,20}");
                Matcher mat = null;
                mat = pat.matcher(txtUsuario.getText().toString());

                if (mat.matches()) {
                    usuario.setUsuario(txtUsuario.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "El usuario solo puede contener letras y numeros", Toast.LENGTH_LONG).show();
                    return;
                }
                mat = pat.matcher(txtPass.getText().toString());
                if (mat.matches()){
                    usuario.setPass(txtPass.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "El contraseña solo puede contener letras y numeros", Toast.LENGTH_LONG).show();
                    return;
                }
                DAOUsuario daoUsuario = new DAOUsuario();
                int resp = 0;
                try {
                    resp = daoUsuario.iniciar_sesion(usuario);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (resp > 0) {
                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "El Usuario Ingresado No existe", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

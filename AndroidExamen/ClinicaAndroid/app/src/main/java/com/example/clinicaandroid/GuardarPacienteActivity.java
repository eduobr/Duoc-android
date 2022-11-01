package com.example.clinicaandroid;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaandroid.controlador.DAOPaciente;
import com.example.clinicaandroid.modelo.Cl_Paciente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuardarPacienteActivity extends AppCompatActivity {
    TextView txtTituloPac, txtFecNacimiento, txtTelefono;
    EditText txtRutPac, txtPrimerNomPac, txtSegundoNomPac, txtApePaternoPac, txtApeMaternoPac;
    Spinner spiSalud;
    Button btnGuardarPac;
    String modificar;
    String rut;
    String p_nombre;
    String s_nombre;
    String apaterno;
    String amaterno;
    String fecNacimiento;
    int telefono;
    String salud;
    boolean guardar = true;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_paciente);
        try {
            txtRutPac = findViewById(R.id.txtRutPac);
            txtTituloPac = findViewById(R.id.txtTituloPac);
            txtPrimerNomPac = findViewById(R.id.txtPrimerNomPac);
            txtSegundoNomPac = findViewById(R.id.txtSegundoNomPac);
            txtApePaternoPac = findViewById(R.id.txtApePaternoPac);
            txtApeMaternoPac = findViewById(R.id.txtApeMaternoPac);
            txtFecNacimiento = findViewById(R.id.txtFecNacimientoPac);
            txtTelefono = findViewById(R.id.txtTelefonoPac);
            spiSalud = findViewById(R.id.spSaludPac);
            btnGuardarPac = findViewById(R.id.btnGuardarPac);
            final DAOPaciente daoPaciente = new DAOPaciente();
            ArrayList<String> listaSaludSpi = (ArrayList<String>) daoPaciente.listarSaludSpinner();
            ArrayAdapter<String> adapterSalud = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSaludSpi);
            spiSalud.setAdapter(adapterSalud);
            modificar = getIntent().getStringExtra("modificar");
            guardar = getIntent().getBooleanExtra("guardar", true);
            if (modificar != null) {
                txtTituloPac.setText(modificar);
                txtRutPac.setEnabled(false);
                rut = getIntent().getStringExtra("rutPac");
                p_nombre = getIntent().getStringExtra("pnombre");
                s_nombre = getIntent().getStringExtra("snombre");
                apaterno = getIntent().getStringExtra("apaterno");
                amaterno = getIntent().getStringExtra("amaterno");
                String fechaNacimiento = getIntent().getStringExtra("fecNacimiento");
                String[] arrayFec = fechaNacimiento.split("-");
                fecNacimiento = arrayFec[2] + "/" + arrayFec[1] + "/" + arrayFec[0];
                telefono = getIntent().getIntExtra("telefono", 0);
                salud = getIntent().getStringExtra("salud");
                txtRutPac.setText(rut);
                txtPrimerNomPac.setText(p_nombre);
                txtSegundoNomPac.setText(s_nombre);
                txtApePaternoPac.setText(apaterno);
                txtApeMaternoPac.setText(amaterno);
                txtFecNacimiento.setText(fecNacimiento);
                txtTelefono.setText(String.valueOf(telefono));
                int posicionSpinner = adapterSalud.getPosition(salud);
                spiSalud.setSelection(posicionSpinner);
            }

            btnGuardarPac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pattern pat = Pattern.compile("[A-Za-z]{3,12}");
                    Pattern patTelefono = Pattern.compile("[0-9]{8,9}");
                    Pattern patFecha = Pattern.compile("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$");
                    Pattern patRutPac = Pattern.compile("\\b\\d{1,8}\\-[K|k|0-9]");
                    Matcher mat = null;

                    Cl_Paciente paciente = new Cl_Paciente();

                    mat = patRutPac.matcher(txtRutPac.getText().toString());
                    if (mat.matches()) {
                        try {
                            paciente.setRut(txtRutPac.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "El  rut ingresado no es valido", Toast.LENGTH_LONG).show();
                            txtRutPac.requestFocus();
                            return;
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "El  rut no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtRutPac.setError("Ej: XXXXXXX-5");
                        txtRutPac.requestFocus();
                        return;
                    }

                    mat = pat.matcher(txtPrimerNomPac.getText().toString());

                    if (mat.matches()) {
                        paciente.setpNombre(txtPrimerNomPac.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "El  primer nombre no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtPrimerNomPac.requestFocus();
                        return;
                    }

                    mat = pat.matcher(txtSegundoNomPac.getText().toString());
                    if (mat.matches()) {
                        paciente.setsNombre(txtSegundoNomPac.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "El  segundo nombre no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtSegundoNomPac.requestFocus();
                        return;
                    }

                    mat = pat.matcher(txtApePaternoPac.getText().toString());
                    if (mat.matches()) {
                        paciente.setaPaterno(txtApePaternoPac.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "El  apellido paterno no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtApePaternoPac.requestFocus();
                        return;
                    }
                    mat = pat.matcher(txtApeMaternoPac.getText().toString());
                    if (mat.matches()) {
                        paciente.setaMaterno(txtApeMaternoPac.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "El  apellido materno no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtApeMaternoPac.requestFocus();
                        return;
                    }
                    String fechaNacimiento = txtFecNacimiento.getText().toString();
                    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    mat = patTelefono.matcher(txtTelefono.getText().toString());
                    if (mat.matches()) {
                        paciente.setTelefono(Integer.valueOf(txtTelefono.getText().toString()));
                    } else {
                        Toast.makeText(getApplicationContext(), "El  telefono no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtTelefono.setError("Debe contener entre 8 y 9 numeros");
                        txtTelefono.requestFocus();
                        return;
                    }
                    paciente.setSalud(spiSalud.getSelectedItem().toString());
                    mat = patFecha.matcher(txtFecNacimiento.getText().toString());
                    if (mat.matches()) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecNacimiento = null;
                        try {
                            fecNacimiento = format.parse(fechaNacimiento);
                            java.sql.Date sqlDate = new java.sql.Date(fecNacimiento.getTime());
                            paciente.setFecNacimiento(sqlDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "La fecha ingresada no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtFecNacimiento.setError("DD/MM/YYYY");
                        txtFecNacimiento.requestFocus();
                        return;
                    }

                    if (guardar == true) {
                        int resp = 0;
                        //Date fecNacimiento = sdf.parse(fechaNacimiento);
                        //Toast.makeText(getApplicationContext(), fechaNacimiento, Toast.LENGTH_LONG).show();
                        try {
                            DAOPaciente daoPacienteGuardar = new DAOPaciente();
                            resp = daoPacienteGuardar.guardarPaciente(paciente);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Error: "+e.getCause(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                        if (resp == 1) {
                            Toast.makeText(getApplicationContext(), "El Paciente se Ingreso Correctamente", Toast.LENGTH_LONG).show();
                            txtRutPac.setText(rut);
                            txtPrimerNomPac.setText("");
                            txtSegundoNomPac.setText("");
                            txtApePaternoPac.setText("");
                            txtApeMaternoPac.setText("");
                            txtFecNacimiento.setText("");
                            txtTelefono.setText("");
                            spiSalud.setSelection(0);
                        } else if(resp==0){
                            Toast.makeText(getApplicationContext(), "El paciente ya existe", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "No se pudo ingresar al paciente", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        boolean resp = false;
                        try {
                            DAOPaciente daoPaciente = new DAOPaciente();
                            resp = daoPaciente.modificarPaciente(paciente);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (resp == true) {
                            Toast.makeText(getApplicationContext(), "El Paciente se Modifico Correctamente", Toast.LENGTH_LONG).show();
                            // Timer
                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public void run() {
                                            Intent i = new Intent(getApplicationContext(), PacienteActivity.class);
                                            startActivity(i);
                                        }
                                    },
                                    3000
                            );
                        } else {
                            Toast.makeText(getApplicationContext(), "No se Modificar el paciente", Toast.LENGTH_LONG).show();
                        }
                    }

                }
            });

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Error:" + ex, Toast.LENGTH_LONG).show();
        }

    }
}

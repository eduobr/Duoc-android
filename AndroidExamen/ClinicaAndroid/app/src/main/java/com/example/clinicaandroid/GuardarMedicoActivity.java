package com.example.clinicaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaandroid.controlador.DAOMedico;
import com.example.clinicaandroid.modelo.Cl_Medico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuardarMedicoActivity extends AppCompatActivity {
    TextView txtTituloMed, txtRutMed, txtNomMed, txtSNomMed, txtApaternoMed, txtAmaternoMed, txtTelMed, txtSueldo,
            txtFecContrato, txtComision, txtRutJefe;
    Spinner spiUnidad, spiCargo, spiEspecialiad;
    Button btnGuardarMed;

    String rut;
    String p_nombre;
    String s_nombre;
    String apaterno;
    String amaterno;
    long telefono;
    int sueldoBase;
    String fecContrato;
    float comision;
    String unidad;
    String cargo;
    String especialidad;
    int jefe_rut;

    String modificar;
    boolean guardar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_medico);
        try {

            txtTituloMed = findViewById(R.id.txtTituloMed);

            txtRutMed = findViewById(R.id.txtRutMed);
            txtNomMed = findViewById(R.id.txtPrimerNomMed);
            txtSNomMed = findViewById(R.id.txtSegundoNomMed);
            txtApaternoMed = findViewById(R.id.txtApePaternoMed);
            txtAmaternoMed = findViewById(R.id.txtApeMaternoMed);
            txtTelMed = findViewById(R.id.txtTelefonoMed);
            txtSueldo = findViewById(R.id.txtSueldo);
            txtFecContrato = findViewById(R.id.txtFecContrato);
            txtComision = findViewById(R.id.txtComisionMed);

            spiUnidad = findViewById(R.id.spUnidadMed);
            spiCargo = findViewById(R.id.spCargoMed);
            spiEspecialiad = findViewById(R.id.spEspecialidadMed);

            txtRutJefe = findViewById(R.id.txtRutJefe);
            modificar = getIntent().getStringExtra("modificar");
            guardar = getIntent().getBooleanExtra("guardar", true);

            btnGuardarMed = findViewById(R.id.btnGuardarMed);
            ArrayList<String> listaUniSpi = null;
            ArrayList<String> listaCarSpi = null;
            ArrayList<String> listaEspSpi = null;

            DAOMedico daoMedUni = new DAOMedico();
            listaUniSpi = (ArrayList<String>) daoMedUni.obtenerUnidad();
            ArrayAdapter<String> adapterUni = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaUniSpi);
            spiUnidad.setAdapter(adapterUni);

            DAOMedico daoMedCar = new DAOMedico();
            listaCarSpi = (ArrayList<String>) daoMedCar.obtenerCargo();
            ArrayAdapter<String> adapterCar = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCarSpi);
            spiCargo.setAdapter(adapterCar);

            DAOMedico daoMedEsp = new DAOMedico();
            listaEspSpi = (ArrayList<String>) daoMedEsp.obtenerEspecialidad();
            ArrayAdapter<String> adapterEsp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaEspSpi);
            spiEspecialiad.setAdapter(adapterEsp);
            guardar = getIntent().getBooleanExtra("guardar", true);
            if (modificar != null) {

                txtTituloMed.setText("Modificar Medico");
                txtRutMed.setEnabled(false);
                rut = getIntent().getStringExtra("rutMed");
                p_nombre = getIntent().getStringExtra("pnombre");
                s_nombre = getIntent().getStringExtra("snombre");
                apaterno = getIntent().getStringExtra("apaterno");
                amaterno = getIntent().getStringExtra("amaterno");
                telefono = getIntent().getLongExtra("telefono", 0L);
                sueldoBase = getIntent().getIntExtra("sueldoBase", 0);
                String fechaContrato = getIntent().getStringExtra("fecContrato");
                String[] arrayFec = fechaContrato.split("-");
                fecContrato = arrayFec[2] + "/" + arrayFec[1] + "/" + arrayFec[0];
                comision = getIntent().getFloatExtra("comision", 1F);
                unidad = getIntent().getStringExtra("unidad");
                cargo = getIntent().getStringExtra("cargo");
                especialidad = getIntent().getStringExtra("especialidad");
                jefe_rut = getIntent().getIntExtra("jefe_rut", 0);

                txtRutMed.setText(rut);
                txtNomMed.setText(p_nombre);
                txtSNomMed.setText(s_nombre);
                txtApaternoMed.setText(apaterno);
                txtAmaternoMed.setText(amaterno);
                txtTelMed.setText(String.valueOf(telefono));
                txtSueldo.setText(String.valueOf(sueldoBase));
                txtFecContrato.setText(fecContrato);
                txtComision.setText(String.valueOf(comision));
                int posicionSpinner = 0;
                posicionSpinner = adapterUni.getPosition(unidad);
                spiUnidad.setSelection(posicionSpinner);
                posicionSpinner = adapterCar.getPosition(cargo);
                spiCargo.setSelection(posicionSpinner);
                posicionSpinner = adapterEsp.getPosition(especialidad);
                spiEspecialiad.setSelection(posicionSpinner);
                txtRutJefe.setText(String.valueOf(jefe_rut));
            }


            btnGuardarMed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pattern pat = Pattern.compile("[A-Za-z]{3,12}");
                    Pattern patTelefono = Pattern.compile("[0-9]{8,11}");
                    Pattern patSueldo = Pattern.compile("(28[0-9]{4}|29[0-8][0-9]{3}|299[0-8][0-9]{2}|2999[0-8][0-9]|29999[0-9]|[3-9][0-9]{5}|1000000)");
                    Pattern patFecha = Pattern.compile("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$");
                    //EXPRESION REGULAR PARA NUMEROS DECIMALES ENTRE 0,0-9
                    Pattern patComision = Pattern.compile("^[0]([.][0-9])?$");
                    Pattern patRutMed = Pattern.compile("\\b\\d{1,8}\\-[K|k|0-9]");
                    Pattern patRutJefe = Pattern.compile("^\\d+$");

                    Matcher mat = null;

                    Cl_Medico medico = new Cl_Medico();
                    mat = patRutMed.matcher(txtRutMed.getText().toString());
                    if (mat.matches()) {

                        try {
                            medico.setRut(txtRutMed.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "El rut del medico no es valido", Toast.LENGTH_LONG).show();
                            txtRutMed.requestFocus();
                            return;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "El rut no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtRutMed.setError("Ej: XXXXXXX-5");
                        txtRutMed.requestFocus();
                        return;
                    }
                    mat = pat.matcher(txtNomMed.getText().toString());
                    if (mat.matches()) {
                        medico.setpNombre(txtNomMed.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "El nombre no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtNomMed.requestFocus();
                        return;
                    }

                    mat = pat.matcher(txtSNomMed.getText().toString());
                    if (mat.matches()) {
                        medico.setsNombre(txtSNomMed.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "El segundo nombre no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtSNomMed.requestFocus();
                        return;
                    }
                    mat = pat.matcher(txtApaternoMed.getText().toString());
                    if (mat.matches()) {
                        medico.setaPaterno(txtApaternoMed.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "El apellido paterno no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtApaternoMed.requestFocus();
                        return;
                    }
                    mat = pat.matcher(txtAmaternoMed.getText().toString());
                    if (mat.matches()) {
                        medico.setaMaterno(txtAmaternoMed.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "El apellido Materno no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtAmaternoMed.requestFocus();
                        return;
                    }
                    mat = patTelefono.matcher(txtTelMed.getText().toString());
                    if (mat.matches()) {
                        medico.setTelefono(Long.valueOf(txtTelMed.getText().toString()));
                    } else {
                        Toast.makeText(getApplicationContext(), "El telefono no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtTelMed.setError("Debe contener entre 8 y 9 numeros");
                        txtTelMed.requestFocus();
                        return;
                    }
                    int sueldo = Integer.valueOf(txtSueldo.getText().toString());
                    mat = patSueldo.matcher(txtSueldo.getText().toString());
                    if (mat.matches()) {
                        if (sueldo >= 500000) {
                            medico.setSueldoBase(sueldo);
                        } else {
                            Toast.makeText(getApplicationContext(), "Ingrese un sueldo mayor o igual a $500.000", Toast.LENGTH_LONG).show();
                            txtSueldo.requestFocus();
                            return;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "El sueldo no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtSueldo.requestFocus();
                        return;
                    }
                    mat = patFecha.matcher(txtFecContrato.getText().toString());
                    if (mat.matches()) {
                        String fechaContrato = txtFecContrato.getText().toString();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date fecContrato = format.parse(fechaContrato);
                            java.sql.Date sqlDate = new java.sql.Date(fecContrato.getTime());
                            medico.setFecContrato(sqlDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "La fecha de contrato no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtFecContrato.setError("DD/MM/YYYY");
                        txtFecContrato.requestFocus();
                        return;
                    }
                    mat = patComision.matcher(txtComision.getText().toString());
                    if (mat.matches()) {
                        medico.setComision(Float.valueOf(txtComision.getText().toString()));
                    } else {
                        Toast.makeText(getApplicationContext(), "La comision no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtComision.setError("desde el 0,0 hasta el 0,9");
                        txtComision.requestFocus();
                        return;
                    }
                    medico.setUnidad(spiUnidad.getSelectedItem().toString());
                    medico.setCargo(spiCargo.getSelectedItem().toString());
                    medico.setEspecialidad(spiEspecialiad.getSelectedItem().toString());
                    mat = patRutJefe.matcher(txtRutJefe.getText().toString());
                    String jefe_rut;
                    if (mat.matches()) {
                        jefe_rut = txtRutJefe.getText().toString();
                    }else{
                        Toast.makeText(getApplicationContext(), "El rut del jefe no posee el formato correcto", Toast.LENGTH_LONG).show();
                        txtRutJefe.setError("rut sin puntos ni digito verificador");
                        txtRutJefe.requestFocus();
                        return;
                    }
                    int numerosRut = Integer.valueOf(jefe_rut);
                    medico.setJefeRut(numerosRut);
                    int resp = 0;
                    if (guardar == true) {
                        try {
                            DAOMedico daoMedico = new DAOMedico();
                            resp = daoMedico.guardarMedico(medico);
                            if (resp == 1) {
                                Toast.makeText(getApplicationContext(), "El Medico se Ingreso Correctamente", Toast.LENGTH_LONG).show();
                                txtRutMed.setText("");
                                txtNomMed.setText("");
                                txtSNomMed.setText("");
                                txtApaternoMed.setText("");
                                txtAmaternoMed.setText("");
                                txtTelMed.setText("");
                                txtSueldo.setText("");
                                txtFecContrato.setText("");
                                txtComision.setText("");
                                int posicionSpinner = 0;
                                spiUnidad.setSelection(posicionSpinner);
                                spiCargo.setSelection(posicionSpinner);
                                spiEspecialiad.setSelection(posicionSpinner);
                                txtRutJefe.setText(String.valueOf(""));
                            } else if (resp == 3) {
                                Toast.makeText(getApplicationContext(), "El Medico Ingresado ya existe", Toast.LENGTH_LONG).show();
                            } else if (resp == 0) {
                                Toast.makeText(getApplicationContext(), "El jefe del medico no existe", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            DAOMedico daoMedico = new DAOMedico();
                            resp = daoMedico.modificarMedico(medico);
                            if (resp == 1) {
                                Toast.makeText(getApplicationContext(), "El Medico se Modifico Correctamente", Toast.LENGTH_LONG).show();
                                // Timer
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                Intent i = new Intent(getApplicationContext(), MedicoActivity.class);
                                                startActivity(i);
                                            }
                                        },
                                        3000
                                );
                            } else if (resp == 0) {
                                Toast.makeText(getApplicationContext(), "El Jefe del Medico No Existe", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "No se pudo Ingresar el Medico", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error:" + e, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}

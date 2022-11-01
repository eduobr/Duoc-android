package com.example.clinicaandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaandroid.controlador.DAOMedico;
import com.example.clinicaandroid.modelo.Cl_Conexion;
import com.example.clinicaandroid.modelo.Cl_Medico;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MedicoActivity extends AppCompatActivity {
    Button btnListar, btnIngresar, btnBuscarMed;
    ListView lvMedico;
    DAOMedico daoMedico;
    TextView txtRutBuscarMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btnListar = findViewById(R.id.btnListarMed);
        btnIngresar = findViewById(R.id.btnIngresarMed);
        btnBuscarMed = findViewById(R.id.btnBuscarMed);

        txtRutBuscarMed = findViewById(R.id.txtRutBuscarMed);

        lvMedico = findViewById(R.id.lvMedico);
        daoMedico = new DAOMedico();
        final ArrayList<Cl_Medico> listaMedicos;
        try {
            listaMedicos = (ArrayList<Cl_Medico>) daoMedico.listarMedicos();
            AdaptadorMedico adaptadorMedico = new AdaptadorMedico(this, R.layout.list_item_medico, listaMedicos);
            lvMedico.setAdapter(adaptadorMedico);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GuardarMedicoActivity.class);
                startActivity(i);
            }
        });

        btnBuscarMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rut = txtRutBuscarMed.getText().toString();
                if (!txtRutBuscarMed.getText().toString().equals("")) {
                    rut = rut.toUpperCase();
                    rut = rut.replace(".", "");
                    rut = rut.replace("-", "");
                    final List<Cl_Medico> listaMedico;
                    try {
                        daoMedico = new DAOMedico();
                        System.out.println(rut);
                        listaMedico = (ArrayList<Cl_Medico>) daoMedico.obtenerMedico(rut.substring(0, rut.length() - 1));
                        AdaptadorMedico adaptadorMedico = new AdaptadorMedico(MedicoActivity.this.getApplicationContext(), R.layout.list_item_medico, listaMedico);
                        lvMedico.setAdapter(adaptadorMedico);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    txtRutBuscarMed.setError("Ingrese un rut");
                }
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoMedico = new DAOMedico();
                final ArrayList<Cl_Medico> listaMedicos;
                try {
                    listaMedicos = (ArrayList<Cl_Medico>) daoMedico.listarMedicos();
                    AdaptadorMedico adaptadorMedico = new AdaptadorMedico(MedicoActivity.this.getApplicationContext(), R.layout.list_item_medico, listaMedicos);
                    lvMedico.setAdapter(adaptadorMedico);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public class AdaptadorMedico extends BaseAdapter {
        private Context context;
        private int layout;
        private List<Cl_Medico> listaMedicos;

        public AdaptadorMedico(Context context, int layout, List<Cl_Medico> listaMedicos) {
            this.context = context;
            this.layout = layout;
            this.listaMedicos = listaMedicos;
        }

        @Override
        public int getCount() {
            return this.listaMedicos.size();
        }

        @Override
        public Object getItem(int position) {
            return this.listaMedicos.get(position).getRut();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolderMedico holder;

            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                convertView = layoutInflater.inflate(layout, parent, false);

                final String rut = listaMedicos.get(position).getRut();
                /*final String nombre = listaMedicos.get(position).getpNombre() + " " + listaMedicos.get(position).getaPaterno();
                final String FecContrato = listaMedicos.get(position).getFecContrato().toString();
                final long tel = listaMedicos.get(position).getTelefono();
                final int sueldo = listaMedicos.get(position).getSueldoBase();
                final Date fecContrato = listaMedicos.get(position).getFecContrato();*/

                holder = new ViewHolderMedico();

                holder.txtRut = (TextView) convertView.findViewById(R.id.txtRutMedItem);
                holder.txtNombre = (TextView) convertView.findViewById(R.id.txtNombreMedItem);
                holder.txtFecContrato = (TextView) convertView.findViewById(R.id.txtFecCtrMedItem);
                holder.txtTelefono = (TextView) convertView.findViewById(R.id.txtTelefonoMedItem);
                holder.txtSueldo = (TextView) convertView.findViewById(R.id.txtSueldoMedItem);
                holder.btnModificarMed = (Button) convertView.findViewById(R.id.btnModificarMed);
                holder.btnEliminarMed = (Button) convertView.findViewById(R.id.btnEliminarMed);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolderMedico) convertView.getTag();
            }

            holder.btnModificarMed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), GuardarMedicoActivity.class);
                    i.putExtra("modificar", "Modificar Paciente");
                    i.putExtra("rutMed", listaMedicos.get(position).getRut());
                    i.putExtra("pnombre", listaMedicos.get(position).getpNombre());
                    i.putExtra("snombre", listaMedicos.get(position).getsNombre());
                    i.putExtra("apaterno", listaMedicos.get(position).getaPaterno());
                    i.putExtra("amaterno", listaMedicos.get(position).getaMaterno());
                    i.putExtra("telefono", listaMedicos.get(position).getTelefono());
                    i.putExtra("sueldoBase", listaMedicos.get(position).getSueldoBase());
                    i.putExtra("fecContrato", listaMedicos.get(position).getFecContrato().toString());
                    i.putExtra("comision", listaMedicos.get(position).getComision());
                    i.putExtra("unidad", listaMedicos.get(position).getUnidad());
                    i.putExtra("cargo", listaMedicos.get(position).getCargo());
                    i.putExtra("especialidad", listaMedicos.get(position).getEspecialidad());
                    i.putExtra("jefe_rut", listaMedicos.get(position).getJefeRut());
                    i.putExtra("guardar", false);
                    startActivity(i);
                    //Toast.makeText(getApplicationContext(),"El boton fue clickeado"+listaPacientes.get(position).getRut(),Toast.LENGTH_LONG).show();
                }
            });

            holder.btnEliminarMed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String rut = listaMedicos.get(position).getRut();
                    daoMedico = new DAOMedico();
                    rut = rut.toUpperCase();
                    rut = rut.replace(".", "");
                    rut = rut.replace("-", "");
                    boolean resp = false;
                    try {
                        resp = daoMedico.eliminarMedico(rut.substring(0, rut.length() - 1));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (resp == true) {
                        Toast.makeText(getApplicationContext(), "Se elimino el Medico", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo eliminar el Medico", Toast.LENGTH_LONG).show();
                    }
                    listarMedicos();
                }
            });

            holder.txtRut.setText(listaMedicos.get(position).getRut());
            String nombre = listaMedicos.get(position).getpNombre() + " " + listaMedicos.get(position).getaPaterno();
            holder.txtNombre.setText(nombre);
            holder.txtFecContrato.setText(listaMedicos.get(position).getFecContrato().toString());
            holder.txtTelefono.setText(String.valueOf(listaMedicos.get(position).getTelefono()));
            holder.txtSueldo.setText(String.valueOf(listaMedicos.get(position).getSueldoBase()));

            return convertView;
        }

        public void listarMedicos() {
            ListView lvMedico;
            lvMedico = (ListView) findViewById(R.id.lvMedico);
            ArrayList<Cl_Medico> lista = new ArrayList<>();
            daoMedico = new DAOMedico();
            try {
                lista = (ArrayList<Cl_Medico>) daoMedico.listarMedicos();
            } catch (Exception e) {
                e.printStackTrace();
            }
            AdaptadorMedico adaptadorMedico = new AdaptadorMedico(MedicoActivity.this.getApplicationContext(), R.layout.list_item_medico, lista);
            lvMedico.setAdapter(adaptadorMedico);
        }

    }

    static class ViewHolderMedico {
        TextView txtRut;
        TextView txtNombre;
        TextView txtFecContrato;
        TextView txtTelefono;
        TextView txtSueldo;
        Button btnModificarMed;
        Button btnEliminarMed;
    }
}

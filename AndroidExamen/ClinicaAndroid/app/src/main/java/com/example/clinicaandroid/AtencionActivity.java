package com.example.clinicaandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaandroid.controlador.DAOAtencion;
import com.example.clinicaandroid.modelo.Cl_Atencion;

import java.util.ArrayList;
import java.util.List;

public class AtencionActivity extends AppCompatActivity {
    Button btnListar,btnIngresarAte,btnBuscarAte;
    TextView txtBuscarAte;
    ListView lvAtencion;
    ArrayList<Cl_Atencion> arregloAtenciones = new ArrayList<>();
    DAOAtencion daoAtencion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atencion);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        lvAtencion = findViewById(R.id.lvAtenciones);
        btnListar = findViewById(R.id.btnListarAtenciones);
        btnIngresarAte = findViewById(R.id.btnIngresarAtencion);
        btnBuscarAte = findViewById(R.id.btnBuscarAtencion);
        txtBuscarAte = findViewById(R.id.txtBuscarAte);

        listarAtenciones();

        btnBuscarAte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAOAtencion daoAtencion = new DAOAtencion();
                if (!txtBuscarAte.getText().toString().equals("")) {
                    List<Cl_Atencion> listaAtencion = new ArrayList<>();
                    String rut = txtBuscarAte.getText().toString();
                    try {
                        listaAtencion = daoAtencion.obtenerAtencion(rut);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    AdaptadorAtencion adaptadorAtencion = new AdaptadorAtencion(AtencionActivity.this.getApplicationContext(), R.layout.list_item_atencion, listaAtencion);
                    lvAtencion.setAdapter(adaptadorAtencion);
                }else{
                    txtBuscarAte.setError("Ingrese el Rut de un Medico o Paciente");
                }
            }
        });

        btnIngresarAte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GuardarAtencionActivity.class);
                startActivity(i);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarAtenciones();
            }
        });
    }

    public class AdaptadorAtencion extends BaseAdapter {
        private Context context;
        private int layout;
        private List<Cl_Atencion> listaAtenciones;

        public AdaptadorAtencion(Context context, int layout, List<Cl_Atencion> listaAtenciones) {
            this.context = context;
            this.layout = layout;
            this.listaAtenciones = listaAtenciones;
        }

        @Override
        public int getCount() {
            return this.listaAtenciones.size();
        }

        @Override
        public Object getItem(int position) {
            return this.listaAtenciones.get(position).getRutPaciente();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolderAtencion holder;

            if (convertView==null){
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                convertView = layoutInflater.inflate(layout,parent,false);

                final int idAtencion = listaAtenciones.get(position).getIdAtencion();
                /*final String rutMedico = listaAtenciones.get(position).getRutMedico();
                final String nombreMedico = listaAtenciones.get(position).getNombreMedico();
                final String rutPaciente = listaAtenciones.get(position).getRutPaciente();
                final String nombrePaciente = listaAtenciones.get(position).getNombrePaciente();
                final String fecAtencion=listaAtenciones.get(position).getFecAtencion().toString();
                final String horaAtencion=listaAtenciones.get(position).getHoraAtencion();*/

                holder = new ViewHolderAtencion();

                holder.txtRutMedico =(TextView) convertView.findViewById(R.id.txtRutAteMed);
                holder.txtNombreMedico =(TextView) convertView.findViewById(R.id.txtAteNomMed);
                holder.txtRutPaciente =(TextView) convertView.findViewById(R.id.txtRutAtePac);
                holder.txtNombrePaciente =(TextView) convertView.findViewById(R.id.txtAteNomPac);
                holder.txtFecAtencion =(TextView) convertView.findViewById(R.id.txtAteFec);
                holder.txtHoraAtencion =(TextView) convertView.findViewById(R.id.txtAteHora);
                holder.btnIngresarPago = (Button) convertView.findViewById(R.id.btnAtePago);
                holder.btnEliminarAte = (Button) convertView.findViewById(R.id.btnEliminarAte);



                convertView.setTag(holder);

            }else{
                holder = (ViewHolderAtencion) convertView.getTag();
            }

            holder.btnIngresarPago.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),GuardarPagoActivity.class);
                    int idAtencion = listaAtenciones.get(position).getIdAtencion();
                    i.putExtra("idAtencion",idAtencion);
                    startActivity(i);
                }
            });

            holder.btnEliminarAte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DAOAtencion daoAtencion = new DAOAtencion();
                    int idAtencion = listaAtenciones.get(position).getIdAtencion();
                    try {
                        boolean resp=daoAtencion.eliminar_atencion(idAtencion);
                        if (resp==true){
                            Toast.makeText(getApplicationContext(),"Se elimino la atención",Toast.LENGTH_LONG).show();
                            listarAtenciones();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.txtRutMedico.setText(listaAtenciones.get(position).getRutMedico());
            holder.txtNombreMedico.setText(listaAtenciones.get(position).getNombreMedico());
            holder.txtRutPaciente.setText(listaAtenciones.get(position).getRutPaciente());
            holder.txtNombrePaciente.setText(listaAtenciones.get(position).getNombrePaciente());
            String[] arrayFec = listaAtenciones.get(position).getFecAtencion().toString().split("-");
            holder.txtFecAtencion.setText(arrayFec[2]+"/"+arrayFec[1]+"/"+arrayFec[0]);
            holder.txtHoraAtencion.setText(String.valueOf(listaAtenciones.get(position).getHoraAtencion()));


            return convertView;
        }

        /*public void listarAtenciones(){
            ListView lvAtencion;
            lvAtencion = (ListView) findViewById(R.id.lvAtenciones);
            ArrayList<Cl_Atencion> lista = new ArrayList<>();
            daoAtencion = new DAOAtencion();
            try {
                lista= (ArrayList<Cl_Atencion>) daoAtencion.listarAtenciones();
            } catch (Exception e) {
                e.printStackTrace();
            }
            AdaptadorAtencion adaptadorAtencion = new AdaptadorAtencion(AtencionActivity.this.getApplicationContext(),R.layout.list_item_atencion,lista);
            lvAtencion.setAdapter(adaptadorAtencion);
        }*/
    }

    public void listarAtenciones(){
        ListView lvAtencion;
        lvAtencion = (ListView) findViewById(R.id.lvAtenciones);
        ArrayList<Cl_Atencion> lista = new ArrayList<>();
        daoAtencion = new DAOAtencion();
        try {
            lista= (ArrayList<Cl_Atencion>) daoAtencion.listarAtenciones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdaptadorAtencion adaptadorAtencion = new AdaptadorAtencion(AtencionActivity.this.getApplicationContext(),R.layout.list_item_atencion,lista);
        lvAtencion.setAdapter(adaptadorAtencion);
    }

    static class ViewHolderAtencion{
        TextView txtRutMedico;
        TextView txtNombreMedico;
        TextView txtRutPaciente;
        TextView txtNombrePaciente;
        TextView txtFecAtencion;
        TextView txtHoraAtencion;
        Button btnIngresarPago;
        Button btnEliminarAte;
    }
}



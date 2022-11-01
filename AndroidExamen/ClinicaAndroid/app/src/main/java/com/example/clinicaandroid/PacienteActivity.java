package com.example.clinicaandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicaandroid.controlador.DAOPaciente;
import com.example.clinicaandroid.modelo.Cl_Paciente;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PacienteActivity extends AppCompatActivity {

    ListView lvPaciente;
    Button btnIngresarPac,btnBuscarPac,btnListarPac;
    TextView txtRutBuscarPac;
    DAOPaciente daoPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btnIngresarPac = findViewById(R.id.btnIngresarPac);
        btnBuscarPac = findViewById(R.id.btnBuscarPac);
        btnListarPac = findViewById(R.id.btnListarPacientes);

        txtRutBuscarPac = findViewById(R.id.txtRutBuscarPac);
        lvPaciente = (ListView) findViewById(R.id.lvPaciente);

        daoPaciente = new DAOPaciente();
        final ArrayList<Cl_Paciente> listaPacientes;
        try {
            listaPacientes = (ArrayList<Cl_Paciente>) daoPaciente.listarPacientes();
            AdaptadorPaciente adaptadorPaciente = new AdaptadorPaciente(this,R.layout.list_item_paciente,listaPacientes);
            lvPaciente.setAdapter(adaptadorPaciente);
            lvPaciente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Button btnModificar = view.findViewById(R.id.btnModificarPac);
                    String rut = listaPacientes.get(position).getRut();
                    Toast.makeText(PacienteActivity.this,rut,Toast.LENGTH_LONG).show();
                }
            });

            btnIngresarPac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),GuardarPacienteActivity.class);
                    startActivity(i);
                }
            });

            btnBuscarPac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String rut = txtRutBuscarPac.getText().toString();
                    if (!txtRutBuscarPac.getText().toString().equals("")) {
                        //txtRutBuscarPac.setText("");
                        final List<Cl_Paciente> listaPaciente;
                        rut = rut.toUpperCase();
                        rut = rut.replace(".", "");
                        rut = rut.replace("-", "");
                        try {
                            daoPaciente = new DAOPaciente();
                            listaPaciente = (ArrayList<Cl_Paciente>) daoPaciente.obtenerPaciente(rut.substring(0, rut.length() - 1));
                            AdaptadorPaciente adaptadorPaciente = new AdaptadorPaciente(PacienteActivity.this.getApplicationContext(), R.layout.list_item_paciente, listaPaciente);
                            lvPaciente.setAdapter(adaptadorPaciente);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        txtRutBuscarPac.setError("Ingrese un Rut");
                    }

                }
            });

            btnListarPac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    daoPaciente = new DAOPaciente();
                    final ArrayList<Cl_Paciente> listaPacientes;
                    try {
                        listaPacientes= (ArrayList<Cl_Paciente>) daoPaciente.listarPacientes();
                        AdaptadorPaciente adaptadorPaciente = new AdaptadorPaciente(PacienteActivity.this.getApplicationContext(),R.layout.list_item_paciente,listaPacientes);
                        lvPaciente.setAdapter(adaptadorPaciente);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    public class AdaptadorPaciente extends BaseAdapter{
        private Context context;
        private int layout;
        private List<Cl_Paciente> listaPacientes;

        public AdaptadorPaciente(Context context, int layout, List<Cl_Paciente> listaPacientes) {
            this.context = context;
            this.layout = layout;
            this.listaPacientes = listaPacientes;
        }

        @Override
        public int getCount() {
            return this.listaPacientes.size();
        }

        @Override
        public Object getItem(int position) {
            return this.listaPacientes.get(position).getRut();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolderPaciente holder;

            if (convertView==null){
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                convertView = layoutInflater.inflate(layout,parent,false);

                final String rut = listaPacientes.get(position).getRut();
                /*final String nombre = listaPacientes.get(position).getpNombre()+" "+listaPacientes.get(position).getaPaterno();
                final String fecNacimiento=listaPacientes.get(position).getFecNacimiento().toString();
                final int tel=listaPacientes.get(position).getTelefono();
                final int salud=listaPacientes.get(position).getSaludId();*/

                holder = new ViewHolderPaciente();

                holder.txtRut =(TextView) convertView.findViewById(R.id.txtRut);
                holder.txtNombre =(TextView) convertView.findViewById(R.id.txtNombre);
                holder.txtFecNacimiento =(TextView) convertView.findViewById(R.id.txtFecNacimiento);
                holder.txtTelefono =(TextView) convertView.findViewById(R.id.txtTelefono);
                holder.txtSalud =(TextView) convertView.findViewById(R.id.txtSalud);
                holder.btnModificarPac = (Button) convertView.findViewById(R.id.btnModificarPac);
                holder.btnEliminarPac = (Button) convertView.findViewById(R.id.btnEliminarPac);

                convertView.setTag(holder);

            }else{
                holder = (ViewHolderPaciente) convertView.getTag();
            }

            holder.txtRut.setText(listaPacientes.get(position).getRut());
            //System.out.println(listaPacientes.get(position).getRut());
            String nombre = listaPacientes.get(position).getpNombre()+" "+listaPacientes.get(position).getaPaterno();
            holder.txtNombre.setText(nombre);
            String[] arrayFec = listaPacientes.get(position).getFecNacimiento().toString().split("-");
            holder.txtFecNacimiento.setText(arrayFec[2]+"/"+arrayFec[1]+"/"+arrayFec[0]);
            holder.txtTelefono.setText(String.valueOf(listaPacientes.get(position).getTelefono()));
            holder.txtSalud.setText(String.valueOf(listaPacientes.get(position).getSalud()));

            holder.btnModificarPac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),GuardarPacienteActivity.class);
                    i.putExtra("modificar","Modificar Paciente");
                    i.putExtra("rutPac", listaPacientes.get(position).getRut());
                    i.putExtra("pnombre", listaPacientes.get(position).getpNombre());
                    i.putExtra("snombre",listaPacientes.get(position).getsNombre());
                    i.putExtra("apaterno",listaPacientes.get(position).getaPaterno());
                    i.putExtra("amaterno",listaPacientes.get(position).getaMaterno());
                    i.putExtra("fecNacimiento",listaPacientes.get(position).getFecNacimiento().toString());
                    i.putExtra("telefono",listaPacientes.get(position).getTelefono());
                    i.putExtra("salud",listaPacientes.get(position).getSalud());
                    i.putExtra("guardar",false);
                    //i.putExtra("fecNacimientoPac", fecNacimiento);
                    //i.putExtra("telPac", tel);
                    //i.putExtra("telPac", salud);

                    startActivity(i);
                    //Toast.makeText(getApplicationContext(),"El boton fue clickeado"+listaPacientes.get(position).getRut(),Toast.LENGTH_LONG).show();
                }
            });

            holder.btnEliminarPac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String rut=listaPacientes.get(position).getRut();
                    daoPaciente = new DAOPaciente();
                    rut = rut.toUpperCase();
                    rut = rut.replace(".", "");
                    rut = rut.replace("-", "");
                    boolean resp=false;
                    try {
                        resp=daoPaciente.eliminarPaciente(rut.substring(0, rut.length() - 1));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (resp== true){
                        Toast.makeText(getApplicationContext(),"Se elimino el paciente",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"No se pudo eliminar el paciente",Toast.LENGTH_LONG).show();
                    }
                    listarPacientes();
                }
            });

            return convertView;
        }

        public void listarPacientes(){
            ListView lvPaciente;
            lvPaciente = (ListView) findViewById(R.id.lvPaciente);
            ArrayList<Cl_Paciente> lista = new ArrayList<>();
            daoPaciente = new DAOPaciente();
            try {
                lista= (ArrayList<Cl_Paciente>) daoPaciente.listarPacientes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            AdaptadorPaciente adaptadorPaciente = new AdaptadorPaciente(PacienteActivity.this.getApplicationContext(),R.layout.list_item_paciente,lista);
            lvPaciente.setAdapter(adaptadorPaciente);
        }


    }

    static class ViewHolderPaciente{
        TextView txtRut;
        TextView txtNombre;
        TextView txtFecNacimiento;
        TextView txtTelefono;
        TextView txtSalud;
        Button btnModificarPac;
        Button btnEliminarPac;
    }
}

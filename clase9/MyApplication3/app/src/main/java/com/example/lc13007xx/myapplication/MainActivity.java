package com.example.lc13007xx.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lc13007xx.myapplication.conexion.ConexionHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtPatente, txtNumChasis, txtColor;
    Spinner spModelo;
    Button btnGuardar, btnEliminar;
    ListView lvAuto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPatente = findViewById(R.id.txtPatente);
        txtNumChasis = findViewById(R.id.txtNumChasis);
        txtColor = findViewById(R.id.txtColor);

        spModelo = findViewById(R.id.spModelo);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnEliminar=findViewById(R.id.btnEliminar);

        lvAuto=findViewById(R.id.lvAuto);

        listarAuto();

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionHelper conexion=new ConexionHelper(getApplicationContext(),
                        "BDAutomotora", null, 1);
                SQLiteDatabase baseDatos=conexion.getWritableDatabase();

                if (baseDatos!=null){
                    String patente=txtPatente.getText().toString();
                    //Toast.makeText(getApplicationContext(), "DELETE FROM automovil WHERE patente='"+patente+"';", Toast.LENGTH_SHORT).show();
                    baseDatos.execSQL("DELETE FROM automovil WHERE patente='"+patente+"';");
                    baseDatos.close();
                    listarAuto();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConexionHelper conexion=new ConexionHelper(getApplicationContext(),
                        "BDAutomotora", null, 1);

                SQLiteDatabase bd=conexion.getWritableDatabase();

                if (bd!=null){
                    String patente=txtPatente.getText().toString();
                    int numChasis=Integer.parseInt(txtNumChasis.getText().toString());
                    String color=txtColor.getText().toString();
                    String modelo=spModelo.getSelectedItem().toString();

                    bd.execSQL("INSERT INTO AUTOMOVIL " +
                            "VALUES('"+patente+"', "+numChasis+", '"+color+"', '"+modelo+"')");
                    bd.close();
                    listarAuto();
                    txtPatente.setText("");
                    txtColor.setText("");
                    txtNumChasis.setText("");
                    spModelo.setSelection(0);
                    txtPatente.requestFocus();
                }


            }
        });




    }

    public void listarAuto(){
        ArrayList<String> arreglito=new ArrayList<String>();
        ConexionHelper conexion=new ConexionHelper(getApplicationContext(),
                "BDAutomotora", null, 1);
        SQLiteDatabase baseDatos=conexion.getReadableDatabase();
        Cursor cursor= baseDatos.rawQuery("SELECT patente, " +
                "numChasis, color, modelo FROM automovil", null);
        if (cursor.moveToFirst()){
            do {
                arreglito.add(cursor.getString(0)+
                        " - "+String.valueOf(cursor.getInt(1)));
            }while (cursor.moveToNext());
        }

        ArrayAdapter<String> adaptadocito=new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, arreglito);
        lvAuto.setAdapter(adaptadocito);

    }

}

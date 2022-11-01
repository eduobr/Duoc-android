package com.example.lc13007xx.myapplication.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionHelper extends SQLiteOpenHelper {


    private String  TABLA_AUTOMOVIL=
            "CREATE TABLE AUTOMOVIL(patente text PRIMARY KEY, " +
                    "numChasis INTEGER, " +
                    "color text, " +
                    "modelo text);";

    public ConexionHelper(Context context, String nombreBD,
                          SQLiteDatabase.CursorFactory cursor, int version){
        super(context, nombreBD, cursor, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_AUTOMOVIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE AUTOMOVIL");
        onCreate(db);
    }
}

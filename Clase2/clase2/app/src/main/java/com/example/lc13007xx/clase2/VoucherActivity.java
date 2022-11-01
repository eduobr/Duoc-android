package com.example.lc13007xx.clase2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VoucherActivity extends AppCompatActivity {
    TextView lblRut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        lblRut = findViewById(R.id.lblRut);
        //Bundle b = this.getIntent().getExtras();
        //String rutB = b.getString("rut");
        //lblRut.setText(rutB);

        String rut = getIntent().getStringExtra("rut");
        lblRut.setText(rut);
    }
}

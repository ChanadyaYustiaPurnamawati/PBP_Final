package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer.PdfCustomer;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;

public class HomeMotorcycle extends AppCompatActivity {

    private TextView mTextView;
    Button add, details, filepdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_motorcycle);
        add = findViewById(R.id.btnAddMotorcycle);
        details = findViewById(R.id.btnDetailsMotorcycle);
        filepdf = findViewById(R.id.btnFileMotorcycle);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateMotorActivity.class));
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShowListMotorActivity.class));
            }
        });

        filepdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdfMotor fragment = new PdfMotor();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().add(R.id.homeMotorcycle,fragment).commit();
            }
        });
    }
}
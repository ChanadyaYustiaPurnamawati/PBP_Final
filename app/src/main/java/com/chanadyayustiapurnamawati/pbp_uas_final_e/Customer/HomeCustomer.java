package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;

public class HomeCustomer extends AppCompatActivity {

    Button add, details, filepdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);
        add = findViewById(R.id.btnAddCustomer);
        details = findViewById(R.id.btnDetailsCustomer);
        filepdf = findViewById(R.id.btnFileCustomer);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreateUserActivity.class));
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShowListUserActivity.class));
            }
        });

        filepdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdfCustomer fragment = new PdfCustomer();
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().add(R.id.homeCustomer,fragment).commit();
            }
        });
    }
}
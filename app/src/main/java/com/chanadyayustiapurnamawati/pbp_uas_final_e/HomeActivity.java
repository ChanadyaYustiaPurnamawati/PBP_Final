package com.chanadyayustiapurnamawati.pbp_uas_final_e;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer.CreateUserActivity;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer.HomeCustomer;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor.CreateMotorActivity;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor.HomeMotorcycle;


public class HomeActivity extends AppCompatActivity {
    CardView company, user, motor, customer, logOut, location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        company = findViewById(R.id.company);
        user = findViewById(R.id.user);
        motor = findViewById(R.id.motor);
        location = findViewById(R.id.location);
        customer = findViewById(R.id.order);
        logOut = findViewById(R.id.logout);

        company.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ActivityCompany.class));
            }
        });
        customer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeCustomer.class));
            }
        });

        motor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeMotorcycle.class));
            }
        });

        user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

        location.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LocationActivity.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignOutActivity.class));
            }
        });




    }
}

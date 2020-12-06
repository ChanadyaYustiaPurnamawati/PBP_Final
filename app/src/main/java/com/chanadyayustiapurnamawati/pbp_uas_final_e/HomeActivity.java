package com.chanadyayustiapurnamawati.pbp_uas_final_e;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer.DetailCustomerFragment;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer.ReadCustomerFragment;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor.ReadMotorFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


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
                startActivity(new Intent(getApplicationContext(),DetailCustomerFragment.class));
            }
        });

        motor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ReadMotorFragment.class));
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task){
                        String mag = "Welcome!!";
                        if(!task.isSuccessful()){
                            mag = "Failed";
                        }
                        Toast.makeText(HomeActivity.this, mag, Toast.LENGTH_SHORT).show();
                    }
                });





    }
}

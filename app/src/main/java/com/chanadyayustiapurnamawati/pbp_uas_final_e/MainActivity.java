package com.chanadyayustiapurnamawati.pbp_uas_final_e;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(user !=null){
//                    Intent logged = new Intent(MainActivity.this, HomeActivity.class);
//                    startActivity(logged);
//                }
                if(user == null){
                    Intent login = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(login);
                }
                else{
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}
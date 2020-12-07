package com.chanadyayustiapurnamawati.pbp_uas_final_e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private String CHANNEL_ID = "channel 2";
    FirebaseAuth auth;
    EditText etEmail, etPassword;
    Button login;
    TextView gotoRegister;
    FirebaseUser newUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.inputEmail);
        etPassword = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btnLogin);
        gotoRegister = findViewById(R.id.btnGotoRegister);
        auth = FirebaseAuth.getInstance();
        newUser = auth.getCurrentUser();

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email is Required!", Toast.LENGTH_SHORT).show();
                }
                if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Password is Required!", Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user!=null){
                            boolean emailVerified = user.isEmailVerified();
                            if(user != null && emailVerified == true){
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                }else{
                                    Toast.makeText(LoginActivity.this, "Login Error!" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(com.chanadyayustiapurnamawati.pbp_uas_final_e.LoginActivity.this, "Email wasn't verified!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }
                        }else{
                            Toast.makeText(com.chanadyayustiapurnamawati.pbp_uas_final_e.LoginActivity.this, "Username or password invalid",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                    }
                });
            }
        });

        gotoRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Login Successful")
                .setContentText("")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent( this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
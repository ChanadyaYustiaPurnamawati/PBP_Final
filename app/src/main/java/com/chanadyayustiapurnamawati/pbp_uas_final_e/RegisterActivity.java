package com.chanadyayustiapurnamawati.pbp_uas_final_e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    ProgressBar progressBar;
    FirebaseAuth auth;
    EditText etEmail, etPassword, etNama, etNoktp, etNotelp;
    Button register;
    FirebaseFirestore store;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etNama = findViewById(R.id.inputNama);
        etEmail = findViewById(R.id.inputEmail);
        etPassword = findViewById(R.id.inputPassword);
        etNoktp = findViewById(R.id.inputNoktp);
        etNotelp = findViewById(R.id.inputNotelp);
        register = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                final String nama = etNama.getText().toString().trim();
                final String notelp = etNotelp.getText().toString().trim();
                final String noktp = etNoktp.getText().toString().trim();
                if(etNama.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Name is Required!", Toast.LENGTH_SHORT).show();
                }
                if(etNoktp.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Citizen Number is Required!", Toast.LENGTH_SHORT).show();
                }
                if(etNotelp.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Phone Number is Required!", Toast.LENGTH_SHORT).show();
                }
                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Email is Required!", Toast.LENGTH_SHORT).show();
                }
                if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Password is Required!", Toast.LENGTH_SHORT).show();
                }


                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser fbaseuser = auth.getCurrentUser();
                            fbaseuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Verification Email Has Been Sent!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Email couldn't been Sent!" + e.getMessage());
                                }
                            });


                            id = auth.getCurrentUser().getUid();
                            DocumentReference documentReference = store.collection("users").document(id);
                            Map<String,Object> user = new HashMap<>();
                            user.put("name",nama);
                            user.put("email",email);
                            user.put("phone",notelp);
                            user.put("citizen",noktp);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ id);
                                    Toast.makeText(RegisterActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " +e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });

    }
}
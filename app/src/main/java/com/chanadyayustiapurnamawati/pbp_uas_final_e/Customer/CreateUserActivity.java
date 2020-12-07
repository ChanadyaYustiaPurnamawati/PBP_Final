package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.LocationActivity;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.MainActivity;
import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity {
    private ImageButton ibBack;
    private EditText name, address, phone_number, cit_number, motorcycle, day;
    private Button btnSubmit;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        progressDialog = new ProgressDialog(this);

        ibBack = findViewById(R.id.btnBack);
        ibBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });

        name = findViewById(R.id.inputName);
        address = findViewById(R.id.inputAddress);
        phone_number = findViewById(R.id.inputPhoneNumber);
        cit_number = findViewById(R.id.inputCitizen);
        motorcycle = findViewById(R.id.inputMotor);
        day = findViewById(R.id.inputRent);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(name.getText().toString().isEmpty())
                {
                    name.setError("Isikan dengan benar");
                    name.requestFocus();
                }
                else if(address.getText().toString().isEmpty())
                {
                    address.setError("Isikan dengan benar");
                    address.requestFocus();
                }else if(phone_number.getText().toString().isEmpty())
                {
                    phone_number.setError("Isikan dengan benar");
                    phone_number.requestFocus();
                }else if(cit_number.getText().toString().isEmpty())
                {
                    cit_number.setError("Isikan dengan benar");
                    cit_number.requestFocus();
                }else if(motorcycle.getText().toString().isEmpty())
                {
                    motorcycle.setError("Isikan dengan benar");
                    motorcycle.requestFocus();
                }else if(day.getText().toString().isEmpty())
                {
                    day.setError("Isikan dengan benar");
                    day.requestFocus();
                }
                else
                {
                    progressDialog.show();
                    saveUser();
                }

                startActivity(new Intent(getApplicationContext(),ShowListUserActivity.class));

            }

        });
    }

    //    method untuk mengirimkan data ke Rest server
    private void saveUser() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.createUser(name.getText().toString(),
                address.getText().toString(), phone_number.getText().toString(), cit_number.getText().toString(), motorcycle.getText().toString(),
                Integer.parseInt(day.getText().toString()));

        add.enqueue(new Callback<UserResponse>() {
            //            menerima balasan dari server jika method yang kita panggil sudah benar
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                Toast.makeText(CreateUserActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(CreateUserActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Log.i("sesuatu", t.getMessage());
            }
        });
    }


}

package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private String id;
    private int status;
    private Button btnCancel, btnSubmit;
    private TextView twName, twAddress, twPhoneNumber, twCitizenNumber, twMotorcycle, twDay;
    private String sName, sAddress, sPhoneNumber, sCitizenNumber, sMotorcycle;
    private int sDay;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);
        twName = findViewById(R.id.edtName);
        twAddress = findViewById(R.id.edtAddress);
        twPhoneNumber = findViewById(R.id.edtPhoneNumber);
        twCitizenNumber = findViewById(R.id.edtCitizen);
        twMotorcycle = findViewById(R.id.edtMotor);
        twDay = findViewById(R.id.edtRent);
        btnCancel = findViewById(R.id.btnCancel);
        btnSubmit = findViewById(R.id.btnSubmit);

        progressDialog = new ProgressDialog(this);
        preferences = getSharedPreferences("SPE", Context.MODE_PRIVATE);
        id = preferences.getString("IDU", "0");

        preferences = getSharedPreferences("SPA", Context.MODE_PRIVATE);
        status = preferences.getInt("AC", 0);

        loadUserById(id);


        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(status==1)
                {
                    Intent intent = new Intent(EditUserActivity.this, ShowListUserActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(twName.getText().toString().isEmpty())
                {
                    twName.setError("Isikan dengan benar");
                    twName.requestFocus();
                }
                else if(twAddress.getText().toString().isEmpty())
                {
                    twAddress.setError("Isikan dengan benar");
                    twAddress.requestFocus();
                }else if(twPhoneNumber.getText().toString().isEmpty())
                {
                    twPhoneNumber.setError("Isikan dengan benar");
                    twPhoneNumber.requestFocus();
                }else if(twCitizenNumber.getText().toString().isEmpty())
                {
                    twCitizenNumber.setError("Isikan dengan benar");
                    twCitizenNumber.requestFocus();
                }else if(twMotorcycle.getText().toString().isEmpty())
                {
                    twMotorcycle.setError("Isikan dengan benar");
                    twMotorcycle.requestFocus();
                }else if(twDay.getText().toString().isEmpty())
                {
                    twDay.setError("Isikan dengan benar");
                    twDay.requestFocus();
                }
                else
                {
                    progressDialog.show();
                    updateUser();
                }
            }
        });
    }

    private void loadUserById(String id)
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getUserById(id, "data");
        progressDialog.show();
        add.enqueue(new Callback<UserResponse>()
        {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response)
            {
                sName = response.body().getUsers().get(0).getNama();
                sAddress = response.body().getUsers().get(0).getAlamat();
                sPhoneNumber = response.body().getUsers().get(0).getNo_telp();
                sCitizenNumber = response.body().getUsers().get(0).getNo_ktp();
                sMotorcycle = response.body().getUsers().get(0).getMotor();
                sDay = response.body().getUsers().get(0).getWaktu();
                twName.setText(sName);
                twAddress.setText(sAddress);
                twPhoneNumber.setText(sPhoneNumber);
                twCitizenNumber.setText(sCitizenNumber);
                twMotorcycle.setText(sMotorcycle);
                twDay.setText(sDay);
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t)
            {
                Toast.makeText(EditUserActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void updateUser()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<UserResponse> update = apiService.updateUser(id, twName.getText().toString(),
                twAddress.getText().toString(), twPhoneNumber.getText().toString(), twCitizenNumber.getText().toString(), twMotorcycle.getText().toString(),
                Integer.parseInt(twDay.getText().toString()));

        update.enqueue(new Callback<UserResponse>()
        {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response)
            {
                Toast.makeText(EditUserActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                if(status==1)
                {
                    Intent intent = new Intent(EditUserActivity.this, ShowListUserActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t)
            {
                Toast.makeText(EditUserActivity.this, "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void savePreferences(String id)
    {
        preferences = getApplicationContext().getSharedPreferences("SP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ID", id);
        editor.commit();
    }

}

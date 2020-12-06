package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserFragment extends DialogFragment {
    private SharedPreferences preferences;
    private TextView twName, twAddress, twPhoneNumber, twCitizenNumber, twMotorcycle, twDay;
    private String sIdCustomer, sName, sAddress, sPhoneNumber, sCitizenNumber, sMotorcycle, sDay;
    private ImageButton ibClose;
    private MaterialButton btnDelete, btnEdit;
    private ProgressDialog progressDialog;

    public static DetailUserFragment newInterface()
    {
        return new DetailUserFragment();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.detail_customer_fragment, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ibClose = v.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dismiss();
            }
        });

        twName = v.findViewById(R.id.twDay);
        twAddress = v.findViewById(R.id.twAddress);
        twPhoneNumber = v.findViewById(R.id.twPhoneNumber);
        twCitizenNumber = v.findViewById(R.id.twCitizenNumber);
        twMotorcycle = v.findViewById(R.id.twMotorcycle);
        twDay = v.findViewById(R.id.twDay);
        sIdCustomer = getArguments().getString("id", "");
        btnDelete = v.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new AlertDialog.Builder(getContext()).setTitle("Konfirmasi").setMessage("Yakin Ingin Menghapus Data Ini?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                progressDialog.show();
                                deleteUser();
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                            }
                        }).create().show();
            }
        });

        btnEdit = v.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                savePreferences();
                Intent intent = new Intent(getContext(), EditUserActivity.class);
                setAccount();
                getActivity().finish();
                startActivity(intent);
            }
        });

        loadCustomerById(sIdCustomer);
        return v;
    }

    private void loadCustomerById(String id)
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> add = apiService.getUserById(id, "data");
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
                sDay = String.valueOf(response.body().getUsers().get(0).getWaktu());
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
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void deleteUser()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> delete = apiService.deleteUser(sIdCustomer);
        delete.enqueue(new Callback<UserResponse>()
        {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response)
            {
                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ShowListUserActivity.class);
                progressDialog.dismiss();
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t)
            {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void savePreferences()
    {
        preferences = getContext().getSharedPreferences("SPE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("IDU", sIdCustomer);
        editor.commit();
    }

    private void setAccount()
    {
        preferences = getContext().getSharedPreferences("SPA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("AC", 1);
        editor.commit();
    }

}

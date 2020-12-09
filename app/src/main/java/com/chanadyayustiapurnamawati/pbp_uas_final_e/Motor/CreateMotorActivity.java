package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.chanadyayustiapurnamawati.pbp_uas_final_e.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMotorActivity extends AppCompatActivity {
    private TextInputEditText merk, kondisi, jenis, harga, tahun, foto;
    private Button btnSubmit;
    private String filePath="";
    private Bitmap bitmap;
    //private ImageView imageView;
    private final int MY_PERMISSION_REQUEST = 777;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_motorcycle);

        if(ContextCompat.checkSelfPermission(CreateMotorActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CreateMotorActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSION_REQUEST);

        }

        merk = findViewById(R.id.inputMerk);
        kondisi = findViewById(R.id.inputKondisi);
        jenis = findViewById(R.id.inputJenis);
        harga = findViewById(R.id.inputHarga);
        tahun = findViewById(R.id.inputTahun);
        foto = findViewById(R.id.inputFoto);
        btnSubmit = findViewById(R.id.btnSubmit);
        //btnUnggah = findViewById(R.id.btnGaleri);
        //imageView = findViewById(R.id.imageView4);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMotor();
            }
        });

//        btnUnggah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent();
//                i.setType("image/*");
//                i.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(i,MY_PERMISSION_REQUEST);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_PERMISSION_REQUEST && resultCode == RESULT_OK && data!=null){
            Uri path = data.getData();
//            try{
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
//                imageView.setImageBitmap(bitmap);
//            }catch (IOException e){
//                e.printStackTrace();
//            }

        }
    }

    private void saveMotor(){
        //String CarImage = imageToString();
        ApiInterfaceMotor apiService = ApiClient.getClient().create(ApiInterfaceMotor.class);
        Call<MotorResponse> add = apiService.createMotor(merk.getText().toString(),
                kondisi.getText().toString(), jenis.getText().toString(),
                Integer.parseInt(harga.getText().toString()),
                Integer.parseInt(tahun.getText().toString()), foto.getText().toString());
        add.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                Toast.makeText(CreateMotorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
                onBackPressed();
            }


            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                Toast.makeText(CreateMotorActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });
    }

}

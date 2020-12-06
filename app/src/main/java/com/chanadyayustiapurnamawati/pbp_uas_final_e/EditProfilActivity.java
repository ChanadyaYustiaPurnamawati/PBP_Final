package com.chanadyayustiapurnamawati.pbp_uas_final_e;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class EditProfilActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    public static final int GALLERY_REQUEST_CODE = 1002;
    public static final String TAG = "TAG";
    EditText etNama, etEmail, etNotelp, etNoktp;
    ImageView fotoProfil, camera;
    Button saveBtn;
    Uri imageUrl;
    FirebaseAuth auth;
    FirebaseFirestore store;
    FirebaseUser user;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        etNama = findViewById(R.id.etProfileName);
        etEmail = findViewById(R.id.etProfileEmail);
        etNotelp = findViewById(R.id.etProfilePhone);
        etNoktp = findViewById(R.id.etProfileCitizen);
        camera = findViewById(R.id.cameraButton);

        Intent data = getIntent();
        final String name = data.getStringExtra("name");
        final String email = data.getStringExtra("email");
        final String phone = data.getStringExtra("phone");
        final String citizen = data.getStringExtra("citizen");

        auth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();

        camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openCameraPermissions();
            }
        });






    }

    private void openCameraPermissions(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageUrl = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //CAMERA INTENT
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUrl);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }
}

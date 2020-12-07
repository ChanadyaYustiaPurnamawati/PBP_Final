package com.chanadyayustiapurnamawati.pbp_uas_final_e;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;


public class ProfileActivity extends AppCompatActivity {
    private static final int GALLERY_INTENT_CODE = 1023 ;
    FirebaseAuth auth;
    FirebaseFirestore store;
    FirebaseUser user;
    TextView tvNama, tvEmail, tvNotelp, tvNoktp;
    String id;
    Button changeP;
    ImageView imgP;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvNama = findViewById(R.id.etProfileName);
        tvEmail = findViewById(R.id.etProfileEmail);
        tvNotelp = findViewById(R.id.etProfilePhone);
        tvNoktp = findViewById(R.id.etProfileCitizen);
        imgP = findViewById(R.id.profileImage);
        changeP = findViewById(R.id.changeProfile);


        auth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        id = auth.getCurrentUser().getUid();
        user = auth.getCurrentUser();

        StorageReference profileRef = storageReference.child("users/"+auth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imgP);
            }

        });




        final DocumentReference documentReference = store.collection("users").document(id);
        documentReference.addSnapshotListener(ProfileActivity.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    tvNotelp.setText(documentSnapshot.getString("phone"));
                    tvNama.setText(documentSnapshot.getString("name"));
                    tvEmail.setText(documentSnapshot.getString("email"));
                    tvNoktp.setText(documentSnapshot.getString("citizen"));
                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

        changeP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditProfile.class));
            }
        });


    }


}
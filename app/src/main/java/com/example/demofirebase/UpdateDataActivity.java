package com.example.demofirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demofirebase.databinding.ActivityUpdatedataBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.example.demofirebase.databinding.ActivityUpdateDataBinding;

public class UpdateDataActivity extends AppCompatActivity {

    private DatabaseReference database;
//    private DataBindingComponent binding;


    private ActivityUpdatedataBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdatedataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent();
//        String name = intent.getStringExtra("name");
//        String pass = intent.getStringExtra("password");
        database = FirebaseDatabase.getInstance().getReference("users");
        database.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String name = String.valueOf(dataSnapshot.child("name").getValue());
                    String pass = String.valueOf(dataSnapshot.child("password").getValue());
                    binding.edtname.setText(name);
                    binding.edtpass.setText(pass);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(UpdateDataActivity.this,"failed",Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnupdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edtname.getText().toString();
                String pass = binding.edtpass.getText().toString();
                updateData(name,pass);
            }
        });


    }

    private void updateData(String name, String pass) {
        database = FirebaseDatabase.getInstance().getReference("users");
        user = new User();
        user.setName(name);
        user.setPassword(pass);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                database.setValue(user);
                Toast.makeText(UpdateDataActivity.this, "data updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDataActivity.this,ReadDataActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(UpdateDataActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });


    }
}

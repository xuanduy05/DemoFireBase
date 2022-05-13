package com.example.demofirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demofirebase.databinding.ActivityReaddataBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadDataActivity extends AppCompatActivity {
//    private ReadDataActivity binding;
    private DatabaseReference database;
//    private DataBindingComponent binding;

    private ActivityReaddataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReaddataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent();
        String data = intent.getStringExtra("data");


        readData(data);

    }

    private void readData(String userName) {
        database = FirebaseDatabase.getInstance().getReference("users");
        database.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String name = String.valueOf(dataSnapshot.child("name").getValue());
                    String pass = String.valueOf(dataSnapshot.child("password").getValue());
                    Toast.makeText(ReadDataActivity.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                    binding.tvFirstName.setText(name);
                    binding.tvpass.setText(pass);
                }else{
                    Toast.makeText(ReadDataActivity.this,"User doesn't Exist",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(ReadDataActivity.this,"failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

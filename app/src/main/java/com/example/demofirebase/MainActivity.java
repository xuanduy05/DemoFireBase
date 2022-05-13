package com.example.demofirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demofirebase.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseReference database;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edUsername.getText().toString();
                String pass = binding.edPassword.getText().toString();
                database = FirebaseDatabase.getInstance().getReference("users");
                user = new User();
                addData(name,pass);
//                user = new User(name,pass);

//                database.child(name).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Intent intent = new Intent(MainActivity.this,ReadDataActivity.class);
//                        intent.putExtra("data", String.valueOf(user));
//                        startActivity(intent);
//                        Toast.makeText(MainActivity.this,"Successfully Saved",Toast.LENGTH_SHORT).show();
////                        Toast.makeText(this, "sucess", Toast.LENGTH_SHORT).show();
//                    }
//
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
//                    }
//                });


            }
        });

    }

    private void addData(String name, String pass) {
        user.setName(name);
        user.setPassword(pass);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                database.setValue(user);
                Toast.makeText(MainActivity.this, "data added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ReadDataActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
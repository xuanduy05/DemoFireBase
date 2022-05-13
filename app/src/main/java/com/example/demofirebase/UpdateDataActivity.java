package com.example.demofirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demofirebase.databinding.ActivityUpdatedataBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        String name = intent.getStringExtra("name");
        String pass = intent.getStringExtra("password");

        binding.edtname.setText(name);
        binding.edtpass.setText(pass);

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


    }
}

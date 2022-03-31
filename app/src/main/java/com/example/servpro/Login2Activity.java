package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.servpro.databinding.ActivityLogin2Binding;

public class Login2Activity extends AppCompatActivity {

    ActivityLogin2Binding binding;
    Button btnViewServiceProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnViewServiceProvider = binding.btnViewProfile;

        btnViewServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login2Activity.this, ServProProfileActivity.class));
            }
        });







    }
}
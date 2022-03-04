package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        Button btnNextService = findViewById(R.id.btnEnterService);

        btnNextService.setOnClickListener((View view)-> {

            startActivity(new Intent(LogInActivity.this, UploadBusActivity.class));
        });

    }
}
package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ServiceDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        Bundle b= getIntent().getExtras();
        String name = b.getString("NAME");


        TextView txtTemp = findViewById(R.id.txtViewResult);


        txtTemp.setText(name);
    }
}
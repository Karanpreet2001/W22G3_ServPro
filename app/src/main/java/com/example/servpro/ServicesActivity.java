package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {

    String city, service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Bundle bun = getIntent().getExtras();
        city = getIntent().getExtras().getString("CITY", "error");
        service = getIntent().getExtras().getString("SERVICE", "error");

        RecyclerView recyclerViewServices = findViewById(R.id.recyclerViewServices);


    }
}
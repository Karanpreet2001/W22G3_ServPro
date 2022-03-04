package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {

    ArrayList<String>  proName, proCity, proPhone;

    DatabaseServ databaseServ;
    String city, service;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Bundle bun = getIntent().getExtras();
        city= getIntent().getExtras().getString("CITY", "error");
        service = getIntent().getExtras().getString("SERVICE", "error");

        RecyclerView recyclerViewServices= findViewById(R.id.recyclerViewServices);




        databaseServ = new DatabaseServ(ServicesActivity.this);
        proName = new ArrayList<>();
        proCity = new ArrayList<>();
        proPhone = new ArrayList<>();




        storeDataInArrays();

        customAdapter = new CustomAdapter(ServicesActivity.this,proName, proCity, proPhone);
        recyclerViewServices.setAdapter(customAdapter);
        recyclerViewServices.setLayoutManager(new LinearLayoutManager(ServicesActivity.this));



    }

    void storeDataInArrays(){
        Cursor cursor =  databaseServ.readAllData();
//        Toast.makeText(ServicesActivity.this, (CharSequence) cursor, Toast.LENGTH_SHORT).show();

        if(cursor.getCount()==0){
            Toast.makeText(ServicesActivity.this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){

                proName.add(cursor.getString(1));
                proCity.add(cursor.getString(2));
                proPhone.add(cursor.getString(6));



            }
        }
    }
}
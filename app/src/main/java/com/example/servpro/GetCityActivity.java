package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servpro.models.ServiceProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_city);

        EditText editTextCity = findViewById(R.id.editTextCity);
        Button btnNext = findViewById(R.id.btnNext);


        btnNext.setOnClickListener((View view)->{


            try{
                String city = editTextCity.getText().toString();

                if(city==null){
                    Toast.makeText(GetCityActivity.this, "Please Enter the name of City", Toast.LENGTH_SHORT).show();
                }else{

                    Intent myRes = new Intent(GetCityActivity.this,GetServiceActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("CITY", city);

                    myRes.putExtras(bundle);

                    startActivity(myRes);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }

}
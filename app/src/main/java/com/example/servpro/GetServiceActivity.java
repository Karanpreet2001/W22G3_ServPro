package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_service);


        EditText editTextService = findViewById(R.id.editTextService);
        TextView txtCity= findViewById(R.id.txtForCity);
        Button btnShow = findViewById(R.id.btnShow);

        String city;

            Bundle bun = getIntent().getExtras();
            city= getIntent().getExtras().getString("CITY", "error");
            txtCity.setText(city);




        btnShow.setOnClickListener((View view)-> {

            try{

                String service = editTextService.getText().toString();


                if(service==null){
                    Toast.makeText(GetServiceActivity.this, "Please Enter the name of Service", Toast.LENGTH_SHORT).show();
                }else{


                    Intent in = new Intent(GetServiceActivity.this, ServicesActivity.class);


                    Bundle bundle = new Bundle();
                    bundle.putString("CITY", city);
                    bundle.putString("SERVICE", service);

                    Log.d("city",city +service);

                    in.putExtras(bundle);

                    startActivity(in);
                }
            }catch (Exception e){
                e.printStackTrace();
            } });


    }
}
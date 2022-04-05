package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Final Project
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radGroupOption= findViewById(R.id.radGroupOption);

        Button btnCheck = findViewById(R.id.btnCheck);
        Button btnLogIn = findViewById(R.id.btnLogIn);

        btnCheck.setOnClickListener((View view)-> {

            if(radGroupOption.getCheckedRadioButtonId()==-1){
                Toast.makeText(MainActivity.this, "Please select one option to proceed", Toast.LENGTH_SHORT).show();
            }else if(radGroupOption.getCheckedRadioButtonId()==R.id.radBtnForService){
                startActivity(new Intent(MainActivity.this, Login2Activity.class));
            }else{
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });

        btnLogIn.setOnClickListener((View view)-> {

            startActivity(new Intent(MainActivity.this,LogInActivity.class));
        });

    }
}
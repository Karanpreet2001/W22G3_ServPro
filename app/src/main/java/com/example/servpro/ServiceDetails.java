package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceDetails extends AppCompatActivity {

    Button btnProfile, btnToContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        btnProfile= findViewById(R.id.btnServiceProfile);

        FragmentManager fragmentManager = getSupportFragmentManager();


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });

        btnToContact = findViewById(R.id.btnToContact);

        btnToContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//new
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ContactFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });
//        Bundle b= getIntent().getExtras();
//        String name = b.getString("NAME");
//
//
//        TextView txtTemp = findViewById(R.id.txtViewResult);
//
//
//        txtTemp.setText(name);


    }
}
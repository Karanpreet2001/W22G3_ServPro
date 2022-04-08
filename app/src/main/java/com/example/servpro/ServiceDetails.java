package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
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

        Bundle data= getIntent().getExtras();
        String name = data.getString("NAME", "error");
        String age = data.getString("AGE","error");
        String occupation = data.getString("OCCU", "error");
        String email = data.getString("EMAIL", "error");
        String phone = data.getString("PHONE", "error");
        String address = data.getString("ADDRESS", "error");
        String city = data.getString("CITY", "error");
        String wage = data.getString("WAGE", "error");
        String description = data.getString("DESCRIPTION", "error");

        Log.d("DES", description+wage);
        
        


        ProfileFragment profileFragment = new ProfileFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Bundle b = new Bundle();
        b.putString("NAME", name);
        b.putString("AGE", age);
        b.putString("OCCU", occupation);
        b.putString("WAGE", wage);
        b.putString("DES", description);

        profileFragment.setArguments(b);

        fragmentTransaction.replace(R.id.fragmentContainerView,profileFragment).commit();


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfileFragment profileFragment = new ProfileFragment();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                Bundle b = new Bundle();
                b.putString("NAME", name);
                b.putString("AGE", age);
                b.putString("OCCU", occupation);
                b.putString("WAGE", wage);
                b.putString("DES", description);

                profileFragment.setArguments(b);

                fragmentTransaction.replace(R.id.fragmentContainerView,profileFragment).commit();


            }
        });

        btnToContact = findViewById(R.id.btnToContact);

        btnToContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContactFragment cf = new ContactFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                Bundle dataToContact = new Bundle();
                dataToContact.putString("NAME", name);
                dataToContact.putString("EMAIL", email);
                dataToContact.putString("PHONE", phone);
                dataToContact.putString("ADDRESS", address);
                dataToContact.putString("CITY", city);

                cf.setArguments(dataToContact);

                ft.replace(R.id.fragmentContainerView,cf).commit();



//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragmentContainerView, ContactFragment.class, null)
//                        .setReorderingAllowed(true)
//                        .addToBackStack("name") // name can be null
//                        .commit();
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
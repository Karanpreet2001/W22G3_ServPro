package com.example.servpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityServProProfileBinding;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.ServiceProvider;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServProProfileActivity extends AppCompatActivity {

    ServPro db;
    ServiceProviderDao serviceProviderDao;

    ActivityServProProfileBinding binding;
    TextView txtTitle;
    TextView txtAllService;
    TextView txtEmail;
    TextView txtPhone;
    Button btnToViewAllServices;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServProProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txtTitle = binding.textServProName;
        txtEmail= binding.txtEmailOfServPro;
        txtAllService = binding.noOfServices;
        txtPhone= binding.txtPhoneOfServPro;
        btnToViewAllServices = binding.btnViewAllServices;

         username = "peterGabri@gmail.com";

        db= Room.databaseBuilder(getApplicationContext(), ServPro.class, "servpro.db").build();

        serviceProviderDao = db.serviceProviderDao();

        btnToViewAllServices.setOnClickListener((View view)-> {

            startActivity(new Intent(ServProProfileActivity.this, ServProAllServices.class));
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()-> {

            List<ServiceProvider> sp = serviceProviderDao.getServProName(username);
            int noOfServices = serviceProviderDao.getServProAllServices(username);


            runOnUiThread(() ->{

                txtTitle.setText("Hi, "+sp.get(0).getServiceProvider());
                txtPhone.setText(sp.get(0).getPhone());
                txtEmail.setText(sp.get(0).getEmail());
                txtAllService.setText("You have "+noOfServices+" registered services");
            });
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.service_pro_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.allServices:
                startActivity(new Intent(ServProProfileActivity.this, ServProAllServices.class));
                        return true;

            case R.id.seeAllDeals:
                startActivity(new Intent(ServProProfileActivity.this, ServProAllDeals.class));
                        return true;

            case R.id.addNewService:
                startActivity(new Intent(ServProProfileActivity.this, ServProAddNewService.class));
                return true;

            default: return super.onOptionsItemSelected(item);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("USERNAME", username);
        editor.commit();
    }
}
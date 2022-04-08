package com.example.servpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.Toast;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityServProProfileBinding;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.ServiceProvider;
import com.example.servpro.viewModel.ServProViewModel;

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
    String email;

    ServProViewModel servProViewModel;

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

        Bundle b = getIntent().getExtras();
        email = b.getString("USERNAME","error");
        Toast.makeText(ServProProfileActivity.this, email, Toast.LENGTH_SHORT).show();




        btnToViewAllServices.setOnClickListener((View view)-> {

            startActivity(new Intent(ServProProfileActivity.this, ServProAllServices.class));
        });


        servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getAllServPro(email).observe(this, new Observer<List<ServiceProvider>>() {
            @Override
            public void onChanged(List<ServiceProvider> sp) {

                txtTitle.setText("Hi, "+sp.get(0).getServiceProvider());
        txtPhone.setText(sp.get(0).getPhone());
        txtEmail.setText(sp.get(0).getEmail());
        txtAllService.setText("You have "+sp.size()+" registered services");
            }
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

            case R.id.allConnections:
                startActivity(new Intent(ServProProfileActivity.this, ConnectionActivity.class));
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

        editor.putString("USERNAME", email);
        editor.commit();
    }
}
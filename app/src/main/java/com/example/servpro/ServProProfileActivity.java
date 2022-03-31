package com.example.servpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.servpro.databinding.ActivityServProProfileBinding;

public class ServProProfileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_pro_profile);


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
}
package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityServProEditBinding;
import com.example.servpro.interfaces.ServiceProviderDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServProEditActivity extends AppCompatActivity {

    ActivityServProEditBinding binding;
    EditText editEmail, editWage, editDescription, editStreet, editCity;
    Button btnToUpdate, btnToDelete;

    ServPro db;
    ServiceProviderDao serviceProviderDao;
    String email, description, wage, city, address, street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServProEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle data= getIntent().getExtras();
        String name = data.getString("NAME", "error");
        String age = data.getString("AGE","error");
        String occupation = data.getString("OCCU", "error");
         email = data.getString("EMAIL", "error");
        String phone = data.getString("PHONE", "error");
         address = data.getString("ADDRESS", "error");
         city = data.getString("CITY", "error");
         wage = data.getString("WAGE", "error");
         description = data.getString("DESCRIPTION", "error");

        editEmail = binding.editServProEmail;
        editCity = binding.editServProCity;
        editWage = binding.editServProWage;
        editStreet = binding.editServProCity;
        editDescription= binding.editServProDescription;
        btnToDelete = binding.btnServProDelete;
        btnToUpdate= binding.btnSerProUpdate;

        editEmail.setText(email);
        editEmail.setEnabled(false);
        editDescription.setText(description);
        editCity.setText(city);
        editWage.setText(wage);
        editStreet.setText(address);

        description = editDescription.getText().toString();
        city = editCity.getText().toString();
        wage = editWage.getText().toString();
         street = editStreet.getText().toString();

        db = Room.databaseBuilder(getApplicationContext(),ServPro.class, "servpro.db").build();
        serviceProviderDao= db.serviceProviderDao();

        btnToUpdate.setOnClickListener((View view) ->{

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(() -> {
                    serviceProviderDao.servProUpdate( description,  street,  city,  wage,  email);

            });

        });

        btnToDelete.setOnClickListener((View view)-> {

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(() ->{

                serviceProviderDao.deleteServPro(email);
            });


        });






    }
}
package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UploadBusActivity extends AppCompatActivity {

    EditText editProName, editProCity, editProOccupation, editProAddress, editProPhone, editProImage, editProService;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bus);

        editProName = findViewById(R.id.editProviderName);
        editProCity = findViewById(R.id.editProviderCity);
        editProOccupation = findViewById(R.id.editOccupation);
        editProAddress = findViewById(R.id.editAddress);
        editProPhone = findViewById(R.id.editPhone);
        editProImage = findViewById(R.id.editImage);
        editProService = findViewById(R.id.editServiceMenu);

        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener((View view) -> {

            DatabaseServ db= new DatabaseServ(UploadBusActivity.this);

            db.addServiceProvider(editProName.getText().toString().trim(), editProCity.getText().toString().trim(),
                    editProOccupation.getText().toString().trim(), editProAddress.getText().toString().trim(),
                    editProPhone.getText().toString().trim(), editProImage.getText().toString().trim(),
                    editProService.getText().toString().trim());

        });



    }
}
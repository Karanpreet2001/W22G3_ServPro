package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servpro.databases.ServPro;
import com.example.servpro.models.ServiceProvider;
import com.example.servpro.viewModel.ServProViewModel;

public class ServProviderRegistration extends AppCompatActivity {

    Button servProRegister;

    EditText editname , editage , editaddress , editoccupation , editphone , editemail  , editpassword ;
    String name , age , address , occupation , phone , email , password;

    ServPro db;
    ServProViewModel servProViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_provider_registration);

        servProRegister = findViewById(R.id.btnServPRegister);

        editname = findViewById(R.id.editServPName);
        editage = findViewById(R.id.editServPAge);
        editaddress = findViewById(R.id.editServPAddress);
        editoccupation = findViewById(R.id.editServpPoccupation);
        editphone = findViewById(R.id.editServPPhone);
        editemail = findViewById(R.id.editServPPhone);
        editpassword = findViewById(R.id.editServPPass);


        servProRegister.setOnClickListener((View view) -> {

        name = editname.getText().toString();
        age = editage.getText().toString();
        address = editaddress.getText().toString();
        occupation = editoccupation.getText().toString();
        phone = editphone.getText().toString();
        email = editemail.getText().toString();
        password = editpassword.getText().toString();

        if(!name.isEmpty()&&!age.isEmpty()&&!address.isEmpty()&&!occupation.isEmpty()&&!phone.isEmpty()&&!email.isEmpty()&&!password.isEmpty()){

            ServiceProvider serviceProvider = new ServiceProvider(name,age,occupation,email,phone,address,password);
            
            servProViewModel = new ViewModelProvider(ServProviderRegistration.this).get(servProViewModel.getClass());
            
            servProViewModel.insert(serviceProvider);

            Toast.makeText(ServProviderRegistration.this, "You are Registered", Toast.LENGTH_SHORT).show();

        }

        });

    }
}
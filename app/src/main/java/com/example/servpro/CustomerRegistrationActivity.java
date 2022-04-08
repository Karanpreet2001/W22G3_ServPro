package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityCustomerRegistrationBinding;
import com.example.servpro.models.Customer;
import com.example.servpro.viewModel.ServProViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerRegistrationActivity extends AppCompatActivity {

    ActivityCustomerRegistrationBinding binding;
    EditText txtName, txtEmail, txtAge, txtPhone, txtAddress, txtPassword;
    String name, email, phone, address, password;
    int age;
    ServPro db;
    ServProViewModel servProViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txtName = binding.editCustName;
        txtAddress = binding.editCustAddress;
        txtAge = binding. editCustAge;
        txtPhone = binding.editCustPhone;
        txtPassword = binding.editTextTextPassword;
        txtEmail = binding.editCustEmail;

        Button btnRegistor = binding.btnCustRegister;
        Button btnLogin = binding.btnCustLogin;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerRegistrationActivity.this,Login2Activity.class));
            }
        });

        db = Room.databaseBuilder(getApplicationContext(), ServPro.class, "servpro.db").build();


        btnRegistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                name = txtName.getText().toString();
                address = txtAddress.getText().toString();
                age = Integer.parseInt(txtAge.getText().toString());
                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();
                phone = txtPhone.getText().toString();


                if(!name.isEmpty() && !address.isEmpty() && !email.isEmpty()&& !phone.isEmpty()&& !password.isEmpty()){
                    Customer customer = new Customer(name, age, email, phone, address, password);

                    servProViewModel = new ViewModelProvider(CustomerRegistrationActivity.this).get(ServProViewModel.class);
                    servProViewModel.insert(customer);
                    Toast.makeText(CustomerRegistrationActivity.this, "You are registered", Toast.LENGTH_SHORT).show();


//                    ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//                    executorService.execute(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            db.customerDao().insertCustomer(customer);
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(CustomerRegistrationActivity.this, "You are register", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//
//                        }
//                    });


                }else{
                    Toast.makeText(CustomerRegistrationActivity.this, "Please enter the details", Toast.LENGTH_SHORT).show();
                }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


    }
}
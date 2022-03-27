package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.servpro.databases.ServPro;
import com.example.servpro.interfaces.CustomerDao;
import com.example.servpro.models.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplashActivity extends AppCompatActivity {

    Button    btnStart;
    ServPro db;
    CustomerDao customerDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        List<Customer> customerList = readAllCustomers();

        Log.d("customer", customerList.size()+"");
        btnStart = findViewById(R.id.btnStart1);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db = Room.databaseBuilder(getApplicationContext(),ServPro.class, "servpro.db").build();
                customerDao=  db.customerDao();
                customerDao.insertIntoCustomer(customerList);


            }
        });
    }

    public List<Customer> readAllCustomers() {

        List<Customer> customerList= new ArrayList<>();

        InputStream inputStream = getResources().openRawResource((R.raw.customers));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


        try{

            String readCustomer = reader.readLine();

            while ((readCustomer= reader.readLine())!=null){
                    String[] eachCustomer = readCustomer.split(",");
                    Customer customer= new Customer(eachCustomer[0],Integer.parseInt(eachCustomer[1]),eachCustomer[2],eachCustomer[3],eachCustomer[4],eachCustomer[5]);

                    customerList.add(customer);
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }


        return customerList;
    }


}
package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.UiAutomation;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.servpro.adapters.ServicesRecyclerView;
import com.example.servpro.databases.ServPro;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.ServiceProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicesActivity extends AppCompatActivity {

    String city, service;
    ServPro servPro;
    ServiceProviderDao serviceProviderDao;
    List<ServiceProvider> extractList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Bundle b1= getIntent().getExtras();
        String city = b1.getString("CITY");
        String service = b1.getString("SERVICE");

        Log.d("city",city +service);

        RecyclerView recyclerViewServices = findViewById(R.id.recyclerViewServices);
        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewServices.setLayoutManager(ln);


        List<ServiceProvider> serviceProviderList = readAllServiceProviders();

        servPro = Room.databaseBuilder(getApplicationContext(),ServPro.class, "servpro.db").build();
        serviceProviderDao= servPro.serviceProviderDao();


        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() ->{

            serviceProviderDao.insertIntoServiceProvider(serviceProviderList);
             extractList = serviceProviderDao.getServiceProviderAccordingly(city, service);




            runOnUiThread(() -> {
                recyclerViewServices.setAdapter(new ServicesRecyclerView(extractList, new ServicesRecyclerView.OnClickItem() {
                    @Override
                    public void onClickItem(int index) {

                        Intent result = new Intent(ServicesActivity.this, ServiceDetails.class);
                        Bundle b = new Bundle();
                        b.putString("NAME",extractList.get(index).getServiceProvider());
                        b.putString("AGE", extractList.get(index).getAge());
                        b.putString("OCCU", extractList.get(index).getOccupation());
                        b.putString("EMAIL", extractList.get(index).getEmail());
                        b.putString("PHONE", extractList.get(index).getPhone());
                        b.putString("ADDRESS", extractList.get(index).getStreet());
                        b.putString("CITY", extractList.get(index).getCity());
                        b.putString("WAGE", extractList.get(index).getWage());
                        b.putString("DESCRIPTION", extractList.get(index).getDescription());

                        result.putExtras(b);
                        startActivity(result);
                    }
                }));
            });


        });






    }


    public List<ServiceProvider> readAllServiceProviders(){
        List<ServiceProvider> allServicePro= new ArrayList<>();

        InputStream inputStream = getResources().openRawResource((R.raw.students));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try{
            String studentLine= reader.readLine();

            while((studentLine= reader.readLine())!=null){

                String[] eachStudent = studentLine.split(",");
                ServiceProvider forStudent = new ServiceProvider(eachStudent[0], eachStudent[1], eachStudent[2],eachStudent[3],eachStudent[4],eachStudent[5],eachStudent[6],eachStudent[7],eachStudent[8],eachStudent[9]);

                allServicePro.add(forStudent);
            }



            Log.d("SIZE", allServicePro.size()+"");

        }catch (IOException ex){
            ex.printStackTrace();
        }

        return  allServicePro;
    }


}
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.servpro.adapters.ServicesRecyclerView;
import com.example.servpro.databases.ServPro;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.ServiceProvider;
import com.facebook.shimmer.ShimmerFrameLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicesActivity extends AppCompatActivity {

    String city, service;
    ServPro servPro;
    ServiceProviderDao serviceProviderDao;
    List<ServiceProvider> extractList = new ArrayList<>();
    ServicesRecyclerView servicesRecyclerView;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        shimmerFrameLayout = findViewById(R.id.shimmer);

        shimmerFrameLayout.startShimmer();



        Bundle b1= getIntent().getExtras();
        String city = b1.getString("CITY");
        String service = b1.getString("SERVICE");

        Log.d("city",city +service);

        RecyclerView recyclerViewServices = findViewById(R.id.recyclerViewServices);
        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewServices.setLayoutManager(ln);




        servPro = Room.databaseBuilder(getApplicationContext(),ServPro.class, "servpro.db").build();
        serviceProviderDao= servPro.serviceProviderDao();


        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() ->{


             extractList = serviceProviderDao.getServiceProviderAccordingly(city, service);




            runOnUiThread(() -> {

                 servicesRecyclerView = new ServicesRecyclerView(extractList, new ServicesRecyclerView.OnClickItem() {
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
                });

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerViewServices.setVisibility(View.VISIBLE);


                recyclerViewServices.setAdapter(servicesRecyclerView);
            });


        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_serachbar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                servicesRecyclerView.getFilter().filter(s);
                return false;
            }
        });

        return  true;
    }
}
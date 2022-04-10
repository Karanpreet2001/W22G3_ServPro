package com.example.servpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servpro.adapters.ServicesRecyclerView;
import com.example.servpro.databases.ServPro;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.ServiceProvider;
import com.example.servpro.viewModel.ServProViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

//import android.widget.SearchView;

public class ServicesActivity extends AppCompatActivity {

    String city, service;
    ServPro servPro;
    ServiceProviderDao serviceProviderDao;
    final String TAG = "CRASHTAG" ;

    ServProViewModel servProViewModel;
    ServicesRecyclerView servicesRecyclerView;
    ShimmerFrameLayout shimmerFrameLayout;
 Button buttonGoogleMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        shimmerFrameLayout = findViewById(R.id.shimmer);

        shimmerFrameLayout.startShimmer();



        Bundle b1= getIntent().getExtras();
        String city = b1.getString("CITY");
        String service = b1.getString("SERVICE");
        String username = b1.getString("EMAIL");

        Log.d("city",city +service);

        RecyclerView recyclerViewServices = findViewById(R.id.recyclerViewServices);
        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewServices.setLayoutManager(ln);


        servProViewModel= new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getServicesByCAS(city,service).observe(this, new Observer<List<ServiceProvider>>() {
            @Override
            public void onChanged(List<ServiceProvider> extractList) {

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerViewServices.setVisibility(View.VISIBLE);
                Toast.makeText(ServicesActivity.this,extractList.size()+"" , Toast.LENGTH_SHORT).show();
                Log.d(TAG,"THIS IS RUNNING");

                recyclerViewServices.setAdapter(new ServicesRecyclerView(extractList, new ServicesRecyclerView.OnClickItem() {
                    @Override
                    public void onClickItem(int index) {


                        Intent result = new Intent(ServicesActivity.this, ServiceDetails.class);
                        Bundle b = new Bundle();
                        b.putString("USERNAME", username);
                        b.putString("NAME", extractList.get(index).getServiceProvider());
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
                }, new ServicesRecyclerView.OnClickMapItem() {
                    @Override
                    public void onClickMapItem(int index) {
                        Intent i = new Intent(ServicesActivity.this, googleMaps.class);
                        Bundle b = new Bundle();
                        b.putDouble("LAT",extractList.get(index).getLatitude());
                        b.putDouble("LON",extractList.get(index).getLongitude());
                        i.putExtras(b);
                        startActivity(i);

                    }
                }));



            }


        });







//
//        servPro = Room.databaseBuilder(getApplicationContext(),ServPro.class, "servpro.db").build();
//        serviceProviderDao= servPro.serviceProviderDao();








        buttonGoogleMaps=findViewById(R.id.buttonGoogleMaps);

        buttonGoogleMaps.setOnClickListener((View view)->{

            startActivity(new Intent(ServicesActivity.this,googleMaps.class));

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
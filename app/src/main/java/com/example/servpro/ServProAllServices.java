package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;

import com.example.servpro.adapters.AllServicesForServPro;
import com.example.servpro.adapters.ServicesRecyclerView;
import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityServProAllServicesBinding;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.ServiceProvider;
import com.example.servpro.viewModel.ServProViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServProAllServices extends AppCompatActivity {


    ServPro db;
    ServiceProviderDao dao;
    ActivityServProAllServicesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServProAllServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.recyclerViewAllServices;
        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(ln);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("USERNAME", "error");

//        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        db = Room.databaseBuilder(getApplicationContext(), ServPro.class, "servpro.db").build();
//        dao = db.serviceProviderDao();
        ServProViewModel servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getAllServPro(username).observe(this, new Observer<List<ServiceProvider>>() {
            @Override
            public void onChanged(List<ServiceProvider> serviceProviderList) {
                recyclerView.setAdapter(new AllServicesForServPro(serviceProviderList, new AllServicesForServPro.OnClickIndex() {


                    @Override
                    public void onCickIndex(int index) {

                        Intent result = new Intent(ServProAllServices.this, ServProEditActivity.class);
                        Bundle b = new Bundle();
                        b.putString("NAME",serviceProviderList.get(index).getServiceProvider());
                        b.putString("AGE", serviceProviderList.get(index).getAge());
                        b.putString("OCCU", serviceProviderList.get(index).getOccupation());
                        b.putString("EMAIL", serviceProviderList.get(index).getEmail());
                        b.putString("PHONE", serviceProviderList.get(index).getPhone());
                        b.putString("ADDRESS", serviceProviderList.get(index).getStreet());
                        b.putString("CITY", serviceProviderList.get(index).getCity());
                        b.putString("WAGE", serviceProviderList.get(index).getWage());
                        b.putString("DESCRIPTION", serviceProviderList.get(index).getDescription());
                        b.putString("PASSWORD", serviceProviderList.get(index).getPassword());

                        result.putExtras(b);
                        startActivity(result);
                    }


                }));
            }
        });




//        executorService.execute(()-> {
//
//            List<ServiceProvider> serviceProviderList = dao.getAllServPro(username);
//
//
//            runOnUiThread(()-> {
//
//
//
//                recyclerView.setAdapter(new AllServicesForServPro(serviceProviderList, new AllServicesForServPro.OnClickIndex() {
//
//
//                    @Override
//                    public void onCickIndex(int index) {
//
//                        Intent result = new Intent(ServProAllServices.this, ServProEditActivity.class);
//                        Bundle b = new Bundle();
//                        b.putString("NAME",serviceProviderList.get(index).getServiceProvider());
//                        b.putString("AGE", serviceProviderList.get(index).getAge());
//                        b.putString("OCCU", serviceProviderList.get(index).getOccupation());
//                        b.putString("EMAIL", serviceProviderList.get(index).getEmail());
//                        b.putString("PHONE", serviceProviderList.get(index).getPhone());
//                        b.putString("ADDRESS", serviceProviderList.get(index).getStreet());
//                        b.putString("CITY", serviceProviderList.get(index).getCity());
//                        b.putString("WAGE", serviceProviderList.get(index).getWage());
//                        b.putString("DESCRIPTION", serviceProviderList.get(index).getDescription());
//
//                        result.putExtras(b);
//                        startActivity(result);
//                    }
//
//
//                }));
//            });
//
//        });



    }
}
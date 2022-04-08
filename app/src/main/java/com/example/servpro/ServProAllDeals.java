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

import com.example.servpro.adapters.DealsAdapter;
import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityServProAllDealsBinding;
import com.example.servpro.models.Deals;
import com.example.servpro.viewModel.ServProViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServProAllDeals extends AppCompatActivity {


    ActivityServProAllDealsBinding binding;
    ServPro db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_pro_all_deals);

        RecyclerView recyclerView = binding.recyclerViewDeals;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("USERNAME", "error");

        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(ln);

        ServProViewModel servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getDealsAccToUserName(username).observe(this, new Observer<List<Deals>>() {
            @Override
            public void onChanged(List<Deals> dealsList) {
                recyclerView.setAdapter(new DealsAdapter(dealsList, new DealsAdapter.OnClickListener() {
                    @Override
                    public void onItemClick(int index) {
                        Intent intent = new Intent(ServProAllDeals.this,ServProAllDealsDetails.class );
                        Bundle b = new Bundle();
                        b.putString("CustEmail",dealsList.get(index).getCustemail());
                        b.putString("Date", dealsList.get(index).getDate());
                        b.putString("Message",dealsList.get(index).getMessage());

                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }));
            }
        });




    }
}
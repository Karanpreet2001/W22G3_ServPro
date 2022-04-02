package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.servpro.adapters.ConnectionAdapter;
import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityConnectionBinding;
import com.example.servpro.interfaces.ConnectionDao;
import com.example.servpro.models.Connection;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.internal.operators.observable.ObservableDoAfterNext;

public class ConnectionActivity extends AppCompatActivity {

    ServPro db;
    ConnectionDao dao;

    ActivityConnectionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.recyclerView;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sp.getString("USERNAME", "error");

        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(ln);

        db = Room.databaseBuilder(getApplicationContext(),ServPro.class, "servpro.db").build();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(()-> {

            dao = db.connectionDao();
            List<Connection> extractList = dao.getConnections(username);
            Log.d("EXTRACT", extractList.size()+" "+extractList.get(0).getCustemail());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    recyclerView.setAdapter(new ConnectionAdapter(extractList, new ConnectionAdapter.OnClickListener() {
                        @Override
                        public void onClickItem(int index) {
                            Intent intent = new Intent(ConnectionActivity.this, ViewConnectionDetailActivity.class);
                            Bundle b = new Bundle();
                            b.putString("CustomerEmail", extractList.get(index).getCustemail());
                            
                            intent.putExtras(b);
                            startActivity(intent);

                        }
                    }));
                }
            });
        });

    }
}
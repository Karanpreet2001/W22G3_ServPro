package com.example.servpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class GetCityActivity extends AppCompatActivity {
            ListView listView;
            String[] cityName= {"Vancouver","Victoria","Kelowna","Abbotsford","Nanaimo","Kamloops","Chilliwack","Surrey","Burnaby","Squamish",
            "Prince George","Nelson","Campbell River"};

            ArrayAdapter<String> arrayAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_city);

        Bundle b = getIntent().getExtras();
        String username = b.getString("USERNAME", "error");



        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,cityName);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String temp = cityName[i].toString();

                Intent myRes = new Intent(GetCityActivity.this,GetServiceActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("CITY", temp);
                bundle.putString("EMAIL", username);

                myRes.putExtras(bundle);
                startActivity(myRes);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.citymenu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setQueryHint("Enter the city ");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                 arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
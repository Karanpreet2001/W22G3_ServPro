package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.servpro.models.ServiceProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetCityActivity extends AppCompatActivity {
            ListView listView;
            String[] cityName= {"Vancouver","Victoria","Kelowna","Abbotsford","Nanaimo","Kamloops","Chilliwack","Surrey","Burnaby","Squamish",
            "Prince George","Nelson","Campbell River"};

            ArrayAdapter<String> arrayAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_city);

   //     EditText editTextCity = findViewById(R.id.editTextCity);
       // Button btnNext = findViewById(R.id.btnNext);

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

                myRes.putExtras(bundle);
                startActivity(myRes);
            }
        });
       /*btnNext.setOnClickListener((View view)->{


            try{
          //      String city = editTextCity.getText().toString();

           //     if(city==null){
                    Toast.makeText(GetCityActivity.this, "Please Enter the name of City", Toast.LENGTH_SHORT).show();
               // }else{

                //    Intent myRes = new Intent(GetCityActivity.this,GetServiceActivity.class);

                    Bundle bundle = new Bundle();
                  //  bundle.putString("CITY", city);

               //     myRes.putExtras(bundle);

                //    startActivity(myRes);
              //  }

            }catch (Exception e){
                e.printStackTrace();
            }

        });*/
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
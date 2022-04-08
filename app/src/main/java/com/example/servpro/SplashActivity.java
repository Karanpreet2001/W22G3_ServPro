package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.servpro.databases.ServPro;
import com.example.servpro.interfaces.ConnectionDao;
import com.example.servpro.interfaces.CustomerDao;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.Connection;
import com.example.servpro.models.Customer;
import com.example.servpro.models.ServiceProvider;

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
    ServiceProviderDao serviceProviderDao;
    ConnectionDao connectionDao;
    MotionLayout motionLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        List<Customer> customerList = readAllCustomers();
//        List<ServiceProvider> serviceProviderList = readAllServiceProviders();
//        List<Connection> connectionList = readAllConnection();

//        Log.d("customer", customerList.size()+"");


        motionLayout = findViewById(R.id.motionLay);
        motionLayout.startLayoutAnimation();


       motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
           @Override
           public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

           }

           @Override
           public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

           }

           @Override
           public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {

               startActivity(new Intent(SplashActivity.this, Login2Activity.class));
           }

           @Override
           public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

           }
       });

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                db = Room.databaseBuilder(getApplicationContext(),ServPro.class, "servpro.db").build();
//                customerDao=  db.customerDao();
//                customerDao.insertIntoCustomer(customerList);
//
//
//                serviceProviderDao= db.serviceProviderDao();
//                serviceProviderDao.insertIntoServiceProvider(serviceProviderList);
//
//                connectionDao = db.connectionDao();
//                connectionDao.insertConnections(connectionList);
//
//
//            }
//        });
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

//    public List<ServiceProvider> readAllServiceProviders(){
//        List<ServiceProvider> allServicePro= new ArrayList<>();
//
//        InputStream inputStream = getResources().openRawResource((R.raw.students));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//        try{
//            String studentLine= reader.readLine();
//
//            while((studentLine= reader.readLine())!=null){
//
//                String[] eachStudent = studentLine.split(",");
//                ServiceProvider forStudent = new ServiceProvider(eachStudent[0], eachStudent[1], eachStudent[2],eachStudent[3],eachStudent[4],eachStudent[5],eachStudent[6],eachStudent[7],eachStudent[8],eachStudent[9]);
//
//                allServicePro.add(forStudent);
//            }
//
//
//
//            Log.d("SIZE", allServicePro.size()+"");
//
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }
//
//        return  allServicePro;
//    }


    public List<Connection> readAllConnection(){
        List<Connection> allConnection= new ArrayList<>();

        InputStream inputStream = getResources().openRawResource((R.raw.connections));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try{
            String connectionLine= reader.readLine();

            while((connectionLine= reader.readLine())!=null){

                String[] eachConnection = connectionLine.split(",");
                Connection forConnect = new Connection(eachConnection[0], eachConnection[1], eachConnection[2],eachConnection[3]);

                allConnection.add(forConnect);
            }



            Log.d("SIZE", allConnection.size()+"");

        }catch (IOException ex){
            ex.printStackTrace();
        }

        return  allConnection;
    }
}
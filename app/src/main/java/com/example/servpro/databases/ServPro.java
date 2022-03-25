package com.example.servpro.databases;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.servpro.interfaces.CustomerDao;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.Customer;
import com.example.servpro.models.ServiceProvider;

@Database(entities = {Customer.class, ServiceProvider.class}, version = 1, exportSchema = false)


public abstract class ServPro extends RoomDatabase {

    public abstract CustomerDao customerDao();

    public  abstract ServiceProviderDao serviceProviderDao();
}

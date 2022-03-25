package com.example.servpro.interfaces;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.servpro.models.ServiceProvider;

import java.util.List;

@Dao
public interface ServiceProviderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIntoServiceProvider(List<ServiceProvider> serviceProviderList);

    @Query("SELECT * from serviceProvider ")
    List<ServiceProvider> getServiceProviderAccordingToCAO();

    @Query("SELECT * from serviceProvider where city=(:city) and occupation=(:service)")
    List<ServiceProvider> getServiceProviderAccordingly(String city, String service);


}

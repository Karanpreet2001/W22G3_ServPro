package com.example.servpro.interfaces;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.servpro.models.ServiceProvider;

import java.util.List;

@Dao
public interface ServiceProviderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIntoServiceProvider(List<ServiceProvider> serviceProviderList);

    @Query("SELECT * from serviceProvider where city = (:city) and occupation=(:occupation)")
    List<ServiceProvider> getServiceProviderAccordingToCAO( String city, String occupation);


}

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

    @Query("Select * from serviceProvider where email= (:email)")
    List<ServiceProvider> getServProName(String email);

    @Query("Select count(*) from serviceProvider where email= (:email)")
    int getServProAllServices(String email);

    @Query("Select * from serviceProvider where email = (:username)")
    List<ServiceProvider> getAllServPro(String username);

    @Query("Update serviceProvider set description = :description and street = :street and city = :city and wage =:wage where email=:email")
    int servProUpdate(String description, String street, String city, String wage, String email);

    @Query("Delete from serviceProvider where email = (:username)")
    int deleteServPro(String username);
}

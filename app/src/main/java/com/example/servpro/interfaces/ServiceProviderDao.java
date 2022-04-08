package com.example.servpro.interfaces;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.servpro.models.ServiceProvider;
import com.example.servpro.viewModel.ServProViewModel;

import java.util.List;

@Dao
public interface ServiceProviderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ServiceProvider serviceProvider);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIntoServiceProvider(List<ServiceProvider> serviceProviderList);

    @Query("SELECT * from serviceProvider ")
    LiveData<List<ServiceProvider>> getServiceProviderAccordingToCAO();

    @Query("SELECT * from serviceProvider where city=(:city) and occupation=(:service)")
    List<ServiceProvider> getServiceProviderAccordingly(String city, String service);

    @Query("Select * from serviceProvider where email= (:email)")
    LiveData<List<ServiceProvider>> getServProName(String email);

    @Query("Select count(*) from serviceProvider where email= (:email)")
    int getServProAllServices(String email);

    @Query("Select * from serviceProvider where email = (:username)")
    LiveData<List<ServiceProvider>> getAllServPro(String username);



    @Query("Update serviceProvider set description = :description, street = :street, city = :city, wage =:wage where email=:email")
    int servProUpdate(String description, String street, String city, String wage, String email);

    @Update
    void serProUpdate(ServiceProvider serviceProvider);

    @Delete
    int serProDelete(ServiceProvider serviceProvider);

    @Query("Delete from serviceProvider where email = (:username)")
    int deleteServPro(String username);




    @Query("SELECT * from serviceProvider where city=(:city) and occupation=(:service)")
    LiveData<List<ServiceProvider>> getSPAccordingToCAO(String city, String service);
}

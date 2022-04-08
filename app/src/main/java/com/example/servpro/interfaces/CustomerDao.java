package com.example.servpro.interfaces;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.servpro.models.Customer;
import com.example.servpro.models.ServiceProvider;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
    void insert(Customer c);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIntoCustomer(List<Customer> customerList);

    @Query("select * from customer")
    List<Customer> getAllCustomer();


    @Insert
    void insertCustomer(Customer customer);


    @Query("Select * from customer where email =:custemail")
    LiveData<Customer> getACustomer(String custemail);


    @Query("SELECT * from customer")
    LiveData<List<Customer>> getCustomers();


    @Query("Select * from customer where email =:email")
    LiveData<Customer> getCustomer(String email);




}

package com.example.servpro.interfaces;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.servpro.models.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIntoCustomer(List<Customer> customerList);

    @Query("select * from customer")
    List<Customer> getAllCustomer();


}

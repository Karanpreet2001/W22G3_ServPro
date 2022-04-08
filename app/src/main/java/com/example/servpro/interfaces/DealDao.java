package com.example.servpro.interfaces;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.servpro.models.Deals;

import java.util.List;

@Dao
public interface DealDao {

    @Insert
    void insertADeal(Deals deal);

    @Query("Select * from deals where servemail=:username")
    LiveData<List<Deals>> getDealsAccToUserName(String username);

    @Insert
    void insert(Deals deal);
}

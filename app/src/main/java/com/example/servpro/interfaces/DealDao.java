package com.example.servpro.interfaces;


import androidx.room.Dao;
import androidx.room.Insert;

import com.example.servpro.models.Deals;

@Dao
public interface DealDao {

    @Insert
    void insert(Deals deal);
}

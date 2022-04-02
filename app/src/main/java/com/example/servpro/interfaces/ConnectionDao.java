package com.example.servpro.interfaces;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.servpro.models.Connection;

import java.util.List;

@Dao
public interface ConnectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertConnections(List<Connection> connectionList);

    @Query("SELECT * from connection where servemail=:username")
    List<Connection> getConnections(String username);


}

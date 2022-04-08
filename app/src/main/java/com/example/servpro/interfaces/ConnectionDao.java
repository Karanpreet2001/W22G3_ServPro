package com.example.servpro.interfaces;


import androidx.lifecycle.LiveData;
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


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertConnection(Connection connection);

    @Query("SELECT * from connection where servemail=:username")
    LiveData<List<Connection>> getConnections(String username);

    @Insert
    void insert(Connection connection);

}

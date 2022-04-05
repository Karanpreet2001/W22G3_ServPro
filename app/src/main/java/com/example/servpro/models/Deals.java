package com.example.servpro.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "deals")
public class Deals {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "servemail")
    private  String servemail;

    @ColumnInfo(name = "custemail")
    private  String custemail;

    @ColumnInfo(name = "date")
    private  String date;

    @ColumnInfo(name = "message")
    private  String message;

    public Deals(String servemail, String custemail, String date, String message) {
        this.servemail = servemail;
        this.custemail = custemail;
        this.date = date;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServemail() {
        return servemail;
    }

    public void setServemail(String servemail) {
        this.servemail = servemail;
    }

    public String getCustemail() {
        return custemail;
    }

    public void setCustemail(String custemail) {
        this.custemail = custemail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

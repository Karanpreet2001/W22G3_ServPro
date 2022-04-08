package com.example.servpro.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "connection", primaryKeys = {"custemail", "servemail"})
public class Connection {


    @NonNull
    @ColumnInfo(name = "customername")
    private String customername;

    @NonNull
    @ColumnInfo(name = "custemail")
    private String custemail;

    @NonNull
    @ColumnInfo(name = "servemail")
    private String servemail;

    @NonNull
    @ColumnInfo(name = "servproname")
    private String servproname;

    public Connection(@NonNull String customername, @NonNull String custemail, @NonNull String servemail, @NonNull String servproname) {
        this.customername = customername;
        this.custemail = custemail;
        this.servemail = servemail;
        this.servproname = servproname;
    }

    @NonNull
    public String getCustomername() {
        return customername;
    }

    public void setCustomername(@NonNull String customername) {
        this.customername = customername;
    }

    @NonNull
    public String getCustemail() {
        return custemail;
    }

    public void setCustemail(@NonNull String custemail) {
        this.custemail = custemail;
    }

    @NonNull
    public String getServemail() {
        return servemail;
    }

    public void setServemail(@NonNull String servemail) {
        this.servemail = servemail;
    }

    @NonNull
    public String getServproname() {
        return servproname;
    }

    public void setServproname(@NonNull String servproname) {
        this.servproname = servproname;
    }
}

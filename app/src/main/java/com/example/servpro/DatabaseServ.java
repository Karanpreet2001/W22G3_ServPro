package com.example.servpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseServ extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="SerPro.db";
    private static final int DATABASE_VERSION=1;

    private final String TABLE_NAME="service_provider";
    private final String COLUMN_ID="_id";
    private final String COLUMN_NAME="provider_name";
    private final String COLUMN_CITY="city";
    private final String COLUMN_OCCUPATION="occupation";
    private final String COLUMN_ADDRESS="address";
    private final String COLUMN_SERVICES="services";
    private final String COLUMN_PHONE="phone";
    private final String COLUMN_IMAGE = "image";
//    private final String COLUMN_CHECK_DEALS="deals";
//    private final String COLUMN_MSG="msg";



    public DatabaseServ(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE "+TABLE_NAME+
                " ("+COLUMN_ID+" Integer primary key autoincrement, "+
                COLUMN_NAME+" text, "+
                COLUMN_CITY+ " text, "+
                COLUMN_OCCUPATION+" text, "+
                COLUMN_ADDRESS+" text, "+
                COLUMN_SERVICES+ " text, "+
                COLUMN_PHONE + " text, "+
                COLUMN_IMAGE+ " text);";
//                COLUMN_MSG+" text);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);

    }


    void addServiceProvider(String name, String city, String occupation, String address, String phone , String image, String services){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues ev = new ContentValues();

        ev.put(COLUMN_NAME, name);
        ev.put(COLUMN_CITY,city);
        ev.put(COLUMN_OCCUPATION, occupation);
        ev.put(COLUMN_ADDRESS, address);
        ev.put(COLUMN_SERVICES, services);
        ev.put(COLUMN_PHONE, phone);
        ev.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME, null, ev);

        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query ="Select * from "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db!=null){
            cursor = db.rawQuery(query, null);
        }


        return cursor;
    }

}

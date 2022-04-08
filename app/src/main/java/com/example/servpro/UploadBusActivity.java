package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.servpro.databases.ServPro;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UploadBusActivity extends AppCompatActivity {

    EditText editProName, editProCity, editProOccupation, editProAddress, editProPhone, editProImage, editProService;
    Button btnRegister , btnUpload;
    ImageView imgViewServiceUpload;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap servicePic ;
    byte[] servicePicByte;
    ServPro db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bus);

        editProName = findViewById(R.id.editProviderName);
        editProCity = findViewById(R.id.editProviderCity);
        editProOccupation = findViewById(R.id.editOccupation);
        editProAddress = findViewById(R.id.editAddress);
        editProPhone = findViewById(R.id.editPhone);
       // editProImage = findViewById(R.id.editImage);
        editProService = findViewById(R.id.editServiceMenu);
        imgViewServiceUpload = findViewById(R.id.imgViewServiceUpload);

        btnRegister=findViewById(R.id.btnUploadImage);
        btnUpload=findViewById(R.id.btnUploadImage);


        db = Room.databaseBuilder(getApplicationContext(), ServPro.class,"servpro.db").build();



        btnUpload.setOnClickListener((View view) -> {


            Intent test = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(test,REQUEST_IMAGE_CAPTURE);


        });


        btnRegister.setOnClickListener((View view) -> {


            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {


            });


        });



    }

    protected void onActivityResult(int requestCode  , int resultCode , Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            servicePic = (Bitmap) data.getExtras().get("data");
            imgViewServiceUpload.setImageBitmap(servicePic);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            servicePic.compress(Bitmap.CompressFormat.PNG, 0, stream);

            servicePicByte = stream.toByteArray();
        }else{

            servicePic = BitmapFactory.decodeResource(getResources(),R.drawable.defaultimage);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            servicePic.compress(Bitmap.CompressFormat.PNG, 0, stream);

            servicePicByte = stream.toByteArray();
        }

    }
}
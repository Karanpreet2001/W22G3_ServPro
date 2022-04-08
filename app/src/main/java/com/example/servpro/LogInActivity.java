/*package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    Dialog dialog;
    Button btnNextService = findViewById(R.id.btnAlreadyRegistered);
    Button btnRegister = findViewById(R.id.btnRegister);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
      /*  dialog = new Dialog(LogInActivity.this);
        dialog.setContentView(R.layout.dialogbox_layout);


            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));


        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        Button okey = dialog.findViewById(R.id.okayButton);
        Button cancel = dialog.findViewById(R.id.cancelButton);

        okey.setOnClickListener((View view) -> {

            Toast.makeText(LogInActivity.this,"okay",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        cancel.setOnClickListener((View view) -> {

            Toast.makeText(LogInActivity.this,"cancel",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });



        btnNextService.setOnClickListener((View view)-> {

            startActivity(new Intent(LogInActivity.this, Login2Activity.class));
        });

        btnRegister.setOnClickListener((View view) -> {


            dialog.show();
        });

    }
}*/
package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Dialog dialog;
        Button btnLogin = findViewById(R.id.btnAlreadyRegistered);
        Button btnRegister = findViewById(R.id.btnUploadImage);


        dialog = new Dialog(LogInActivity.this);
        dialog.setContentView(R.layout.dialogbox_layout);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        Button okey = dialog.findViewById(R.id.okayButton);
        Button cancel = dialog.findViewById(R.id.cancelButton);

        okey.setOnClickListener((View view) -> {

            Toast.makeText(LogInActivity.this,"Okay",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        cancel.setOnClickListener((View view) -> {

            Toast.makeText(LogInActivity.this,"cancel",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });


        btnLogin.setOnClickListener((View view)-> {


            startActivity(new Intent(LogInActivity.this, Login2Activity.class));

        });


       btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dialog.show();

            }
        });

    }
}
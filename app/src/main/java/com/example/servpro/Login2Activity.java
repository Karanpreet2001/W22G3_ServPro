package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityLogin2Binding;
import com.example.servpro.interfaces.CustomerDao;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.Customer;
import com.example.servpro.models.ServiceProvider;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Login2Activity extends AppCompatActivity {

    String email;
    String password;
    ActivityLogin2Binding binding;
    Button btnViewServiceProvider ,   btnGoogle;
    private static final int RC_SIGN_IN = 1;
    ServPro db;
    ServiceProviderDao serviceProviderDao;
    CustomerDao customerDao;
    String selection="C";
    boolean bool= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnViewServiceProvider = binding.btnViewProfile;
        EditText txtEmail = binding.editTextEmail;
        EditText txtPassword= binding.editTextPassword;
        CheckBox check = binding.checkBox;






        db = Room.databaseBuilder(getApplicationContext(), ServPro.class, "servpro.db").build();
        serviceProviderDao = db.serviceProviderDao();
        customerDao = db.customerDao();




        btnViewServiceProvider.setOnClickListener((View view)-> {

            startActivity(new Intent(Login2Activity.this,ServProProfileActivity.class));


        });

        // Google Sign in

        GoogleSignInClient mGoogleSignInClient;


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnGoogle = findViewById(R.id.btnGoogleSign);

        btnGoogle.setOnClickListener((View view) -> {

            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);

        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();

                Toast.makeText(this, "Signed in as " + personName, Toast.LENGTH_SHORT).show();

            }
            else{

                Toast.makeText(this, "DIDNOT RUN", Toast.LENGTH_SHORT).show();
            }

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {


        }
    }
}
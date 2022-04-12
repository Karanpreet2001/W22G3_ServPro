package com.example.servpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.servpro.databinding.ActivityLogin2Binding;
import com.example.servpro.models.Customer;
import com.example.servpro.models.ServiceProvider;
import com.example.servpro.viewModel.ServProViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class Login2Activity extends AppCompatActivity {

    String email;
    String password;
    ActivityLogin2Binding binding;
    Button btnViewServiceProvider ,   btnGoogle;
    private static final int RC_SIGN_IN = 1;

    String selection="C";
    String TAG = "logintest";
    String flag = "F";
    ServProViewModel servProViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnViewServiceProvider = binding.btnViewProfile;
        EditText txtEmail = binding.editTextEmail;
        EditText txtPassword= binding.editTextPassword;
        CheckBox check = binding.checkBox;
        Button btnToCustomerRegister = binding.btnCustomerRegistration;

        btnToCustomerRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login2Activity.this, CustomerRegistrationActivity.class));
            }
        });


        servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);




        btnViewServiceProvider.setOnClickListener((View view)-> {

                    email = txtEmail.getText().toString().toLowerCase();
                    password = txtPassword.getText().toString();

                    if (check.isChecked()) {
                        selection = "S";


                    }
//                    Toast.makeText(Login2Activity.this, email + " " + password + " " + selection, Toast.LENGTH_SHORT).show();


                    if (selection == "S") {
//                        ToastLogin2Activity.this.makeText(Login2Activity.this, "In S", Toast.LENGTH_SHORT).show();
                        servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);

                        servProViewModel.getServicePros().observe(this, new Observer<List<ServiceProvider>>() {
                            @Override
                            public void onChanged(List<ServiceProvider> allServiceProvider) {
                        for (int i = 0; i < allServiceProvider.size(); i++) {
                            Log.d("CHECK", allServiceProvider.get(i).getEmail());
                            if (email.equals(allServiceProvider.get(i).getEmail().toLowerCase().trim()) && password.equals(allServiceProvider.get(i).getPassword().trim())) {

                                Intent intent = new Intent(Login2Activity.this, ServProProfileActivity.class);
                                Bundle b = new Bundle();
                                b.putString("USERNAME",allServiceProvider.get(i).getEmail());
                                intent.putExtras(b);
                                startActivity(intent);

                                flag = "T";
                                break;
                            }

                            if (allServiceProvider.size() - i == 1) {

                                Toast.makeText(Login2Activity.this, "Username does not exit", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, flag);
                            }
                        }

                            }
                        });

//                        List<ServiceProvider> allServiceProvider = serviceProviderDao.getServiceProviderAccordingToCAO();

                    }

            if (selection == "C") {
                servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
                servProViewModel.getCustomers().observe(this, new Observer<List<Customer>>() {
                    @Override
                    public void onChanged(List<Customer> allCustomer) {
                        for (int i = 0; i < allCustomer.size(); i++) {
                            //  Log.d("CHECKLIST", allCustomer.get(i).getEmail()+allCustomer.get(i).getPassword());
                            Log.d(TAG, "incorrect password : " + email + "-" + allCustomer.get(i).getEmail().toString().toLowerCase()
                                    + "//" + password + "-" + allCustomer.get(i).getPassword().toString().trim());
                            if (email.equals(allCustomer.get(i).getEmail().toLowerCase()) && password.equals(allCustomer.get(i).getPassword())) {
                                flag = "T";
                                Intent intent = new Intent(Login2Activity.this, GetCityActivity.class);
                                Bundle b = new Bundle();
                                b.putString("USERNAME",allCustomer.get(i).getEmail());
                                intent.putExtras(b);
                                startActivity(intent);

                                break;
                            }
                        }
                    }
                });






            }




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

                if(selection =="S"){
                    servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
                    servProViewModel.getServicePros().observe(this, new Observer<List<ServiceProvider>>() {
                        @Override
                        public void onChanged(List<ServiceProvider> allServiceProvider) {

                            for(int i = 0; i<allServiceProvider.size();i++){

                                Log.d(TAG,"incorrect password : "+email+"-"+allServiceProvider.get(i).getEmail().toString().toLowerCase()
                                        +"//"+ password + "-" + allServiceProvider.get(i).getPassword().toString().trim() );

                                if(email.equals(allServiceProvider.get(i).getEmail().toLowerCase().trim())&&password.equals(allServiceProvider.get(i).getPassword().trim())){
                                    startActivity(new Intent(Login2Activity.this, ServProProfileActivity.class));
                                    flag = "T";
                                    break;
                                }

                                if(allServiceProvider.size() - i == 1){

                                    Toast.makeText(Login2Activity.this, "Username does not exit", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG,flag);
                                }
                            }
                        }
                    });
//                    List<ServiceProvider> allServiceProvider = serviceProviderDao.getServiceProviderAccordingToCAO();


                }

                if(selection=="C"){

                    servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
                    servProViewModel.getCustomers().observe(this, new Observer<List<Customer>>() {
                        @Override
                        public void onChanged(List<Customer> allCustomer) {

                            for(int i = 0; i<allCustomer.size();i++){
                                //  Log.d("CHECKLIST", allCustomer.get(i).getEmail()+allCustomer.get(i).getPassword());
                                Log.d(TAG,"incorrect password : "+email+"-"+allCustomer.get(i).getEmail().toString().toLowerCase()
                                        +"//"+ password + "-" + allCustomer.get(i).getPassword().toString().trim() );
                                if(email.equals(allCustomer.get(i).getEmail().toLowerCase())&&password.equals(allCustomer.get(i).getPassword())){
                                    startActivity(new Intent(Login2Activity.this,GetCityActivity.class));
                                    break;
                                }
                                if(allCustomer.size() - i == 1){

                                    Toast.makeText(Login2Activity.this, "Username does not exit", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG,flag);
                                }
                            }
                        }
                    });
                }


            }
            else{

                Toast.makeText(this, "DIDNOT RUN", Toast.LENGTH_SHORT).show();
            }

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {


        }
    }
}
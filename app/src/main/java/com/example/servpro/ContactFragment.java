package com.example.servpro;

import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import androidx.biometric.BiometricPrompt;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.FragmentContactBinding;
import com.example.servpro.interfaces.ConnectionDao;
import com.example.servpro.models.Connection;
import com.example.servpro.models.ServiceProvider;
import com.example.servpro.viewModel.ServProViewModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.biometric.BiometricPrompt;


/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    FragmentContactBinding binding;

    ServPro db;
    ServProViewModel servProViewModel;


    String email, phone, address, city;
    TextView txtEmail, txtPhone, txtAddress;
    Button btnToConnect, btnSendAMessage;
    String custName;
    String username;
    ConnectionDao dao;
    String name;
    final String TAG = "FACEBUTTON";

    public BiometricPrompt biometricPrompt;
    private Executor executor;
    private BiometricPrompt.PromptInfo promptInfo;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        executor = ContextCompat.getMainExecutor(inflater.getContext());

        // Inflate the layout for this fragment


        binding= FragmentContactBinding.inflate(getLayoutInflater(), container, false);

        txtAddress= binding.txtServiceAddress;
        txtEmail = binding.txtServiceEmail;
        txtPhone= binding.txtServicePhone;
        btnToConnect=binding.btnConnectToServicePro;
        btnSendAMessage = binding.btnSendAMessage;

        Bundle data = getArguments();

        if(data != null){
            name = data.getString("NAME");
            email = data.getString("EMAIL", "error");
            phone = data.getString("PHONE");
            address = data.getString("ADDRESS");
            city = data.getString("CITY");
            username = data.getString("USERNAME");

        }

        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address+", "+city);


        servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getServPro(username).observe(getViewLifecycleOwner(), new Observer<List<ServiceProvider>>() {
            @Override
            public void onChanged(List<ServiceProvider> serviceProviderList) {
                custName = serviceProviderList.get(0).getServiceProvider();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setDeviceCredentialAllowed(true)
                .setTitle("Biometric login for Connecting")
                .setSubtitle("Log in using your biometric credential")
                .setConfirmationRequired(false)
                .build();


        btnToConnect.setOnClickListener((View view)-> {

            Log.d(TAG,"asdasd");

            biometricPrompt = new BiometricPrompt(this,
                    executor, new BiometricPrompt.AuthenticationCallback() {

                @Override
                public void onAuthenticationError(int errorCode,
                                                  @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(getContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT)
                            .show();
                }

                @Override
                public void onAuthenticationSucceeded(
                        @NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getContext(),
                            "Authentication succeeded!", Toast.LENGTH_SHORT).show();

                    Connection con = new Connection(username,custName,email,name);

                    servProViewModel = new ViewModelProvider(ContactFragment.this).get(ServProViewModel.class);
                    servProViewModel.insert(con);

                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getContext(), "Authentication failed",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });


        });



        btnSendAMessage.setOnClickListener((View vie)-> {

            Intent intent = new Intent(getActivity(), FixADealActivity.class);
            Bundle b = new Bundle();

            b.putString("CostEmail",username);
            b.putString("ServEmail", email);

            intent.putExtras(b);
            startActivity(intent);

        });

        return  binding.getRoot();

    }
}
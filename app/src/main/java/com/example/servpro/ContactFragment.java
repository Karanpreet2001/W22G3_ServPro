package com.example.servpro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.servpro.databinding.FragmentContactBinding;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    FragmentContactBinding binding;

    public ContactFragment() {
        // Required empty public constructor
    }


    String email, phone, address, city;
    TextView txtEmail, txtPhone, txtAddress;
    Button btnToConnect, btnSendAMessage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding= FragmentContactBinding.inflate(getLayoutInflater(), container, false);

        txtAddress= binding.txtServiceAddress;
        txtEmail = binding.txtServiceEmail;
        txtPhone= binding.txtServicePhone;
        btnToConnect=binding.btnConnectToServicePro;
        btnSendAMessage = binding.btnSendAMessage;

        Bundle data = getArguments();

        if(data != null){

            email = data.getString("EMAIL", "error");
            phone = data.getString("PHONE");
            address = data.getString("ADDRESS");
            city = data.getString("CITY");

        }

        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address+", "+city);


        return  binding.getRoot();


    }
}
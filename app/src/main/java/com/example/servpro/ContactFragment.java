package com.example.servpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.FragmentContactBinding;
import com.example.servpro.interfaces.ConnectionDao;
import com.example.servpro.models.Connection;
import com.example.servpro.models.Customer;
import com.example.servpro.viewModel.ServProViewModel;

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
            name = data.getString("NAME");
            email = data.getString("EMAIL", "error");
            phone = data.getString("PHONE");
            address = data.getString("ADDRESS");
            city = data.getString("CITY");
            username = data.getString("USERNAME");

        }


        txtEmail.setText("Email: "+email);
        txtPhone.setText("Phone: "+phone);
        txtAddress.setText("Address: "+address+", "+city);


        servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getACustomer(username).observe(getActivity(), new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {
                custName = customer.getCustomerName();
            }
        });


        btnToConnect.setOnClickListener((View vie)-> {
            Toast.makeText(getActivity(),custName+username , Toast.LENGTH_SHORT).show();

            Connection con = new Connection(custName,username,email,name);

            servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
            servProViewModel.insert(con);

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
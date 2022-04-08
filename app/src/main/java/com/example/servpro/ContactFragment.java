package com.example.servpro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.FragmentContactBinding;
import com.example.servpro.interfaces.ConnectionDao;
import com.example.servpro.models.Connection;
import com.example.servpro.viewModel.ServProViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    String tempEmail="new@gmail.com";
    String tempName = "new";
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

        }

        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtAddress.setText(address+", "+city);


//        db = Room.databaseBuilder(getActivity(),ServPro.class,"servpro.db").build();
//        dao = db.connectionDao();

        btnToConnect.setOnClickListener((View vie)-> {

            Connection con = new Connection(tempName,tempEmail,email,name);

            servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
            servProViewModel.insert(con);

//            ExecutorService executorService = Executors.newSingleThreadExecutor();
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//
//                    dao.insertConnection(con);
//                }
//            });

        });

        btnSendAMessage.setOnClickListener((View vie)-> {

            Intent intent = new Intent(getActivity(), FixADealActivity.class);
            Bundle b = new Bundle();

            b.putString("CostEmail","karanpreet@gmail.com");
            b.putString("ServEmail", email);

            intent.putExtras(b);
            startActivity(intent);

        });

        return  binding.getRoot();

    }
}
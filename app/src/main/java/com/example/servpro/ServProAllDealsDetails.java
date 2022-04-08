package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityServProAllDealsBinding;
import com.example.servpro.databinding.ActivityServProAllDealsDetailsBinding;
import com.example.servpro.models.Customer;
import com.example.servpro.viewModel.ServProViewModel;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServProAllDealsDetails extends AppCompatActivity {

    ActivityServProAllDealsDetailsBinding binding;
    ServPro db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServProAllDealsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView txtName = binding.tttCustName;
        TextView txtPhone = binding.tttCustPhone;
        TextView txtAddress = binding.tttCustAddress;
        TextView txtdate = binding.tttForDate;
        TextView txtMessage = binding.tttForMessage;
        TextView txtEmail = binding.tttCustEmail;

        Bundle b = getIntent().getExtras();
        String custName = b.getString("CustEmail");
        String date = b.getString("Date");
        String message = b.getString("Message");

        ServProViewModel servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getACustomer(custName).observe(this, new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {

                txtName.setText(customer.getCustomerName());
                txtEmail.setText(customer.getEmail());
                txtPhone.setText(customer.getPhone());
                txtAddress.setText(customer.getAddress());
                txtdate.setText(date);
                txtMessage.setText(message);

            }
        });


    }
}
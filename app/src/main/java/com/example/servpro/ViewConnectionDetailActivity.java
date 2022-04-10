package com.example.servpro;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityViewConnectionDetailBinding;
import com.example.servpro.models.Customer;
import com.example.servpro.viewModel.ServProViewModel;

public class ViewConnectionDetailActivity extends AppCompatActivity {

    ActivityViewConnectionDetailBinding binding;
    ServPro db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewConnectionDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
        String custEmail = b.getString("SE");
        Toast.makeText(ViewConnectionDetailActivity.this, custEmail, Toast.LENGTH_SHORT).show();

        TextView txtName = binding.txtViewDealsName;
        TextView txtEmail = binding.txtViewDealsEmail;
        TextView txtAddress = binding.txtViewDealsAddress;
        TextView txtPhone = binding.txtViewDealsPhone;

        ServProViewModel servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getACustomer(custEmail).observe(this, new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {

                txtName.setText(customer.getCustomerName());
                txtEmail.setText(customer.getEmail());
                txtPhone.setText(customer.getPhone());
                txtAddress.setText(customer.getAddress());
            }
        });


    }
}
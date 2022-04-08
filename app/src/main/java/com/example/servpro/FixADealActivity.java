package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servpro.databases.ServPro;
import com.example.servpro.databinding.ActivityFixAdealBinding;
import com.example.servpro.models.Deals;
import com.example.servpro.viewModel.ServProViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixADealActivity extends AppCompatActivity {

    ActivityFixAdealBinding binding;
    String selectedDate;
    EditText editMessage;
    String msg;
    ServPro db;
    ServProViewModel servProViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFixAdealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle b = getIntent().getExtras();
        String servEmail = b.getString("ServEmail");
        String costEmail = b.getString("CostEmail");

        TextView txtSetDate = binding.txtViewSetDate;
        Button btnSetDate = binding.btnPickADate;
        Button btnMakeADeal = binding.btnMakeADeal;
        editMessage = binding.editTextMessage;

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())).build();


        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "Material_Range");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        txtSetDate.setText(materialDatePicker.getHeaderText());
                        selectedDate = materialDatePicker.getHeaderText();
                    }
                });
            }
        });


        btnMakeADeal.setOnClickListener((View vie) -> {

            if (!selectedDate.isEmpty()) {
                msg = editMessage.getText().toString();
                Deals deal = new Deals(servEmail, costEmail, selectedDate, msg);

                servProViewModel = new ViewModelProvider(this).get(ServProViewModel.class);
                servProViewModel.insert(deal);
            } else {
                Toast.makeText(FixADealActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
            }


        });


    }
}
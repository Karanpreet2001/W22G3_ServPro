package com.example.servpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.servpro.databinding.FragmentProfileBinding;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    FragmentProfileBinding binding;
    public ProfileFragment() {
        // Required empty public constructor
    }


    String name, occupation, age, description, wage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentProfileBinding.inflate(getLayoutInflater(), container, false);

        TextView txtName= binding.txtServiceName;
        TextView txtOccupation = binding.textServiceOccupation;
        TextView txtAge = binding.txtServiceAge;
        TextView txtDescription = binding.txtServiceDescription2;
        TextView txtWage = binding.txtServiceWage;

        Bundle data = getArguments();
        if(data != null){
            name = data.getString("NAME");
            wage = data.getString("WAGE");
            description = data.getString("DES","error");
            occupation = data.getString("OCCU", "error");
            age = data.getString("AGE", "error");

        }
        txtName.setText("Name: "+name);
        txtAge.setText("Age: "+age);
        txtOccupation.setText("Experienced in : "+occupation);
        txtWage.setText("Wage per hour: "+wage);
        txtDescription.setText("Description:\n"+description);

        return binding.getRoot();
    }
}
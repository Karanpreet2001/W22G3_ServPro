package com.example.servpro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.servpro.databinding.FragmentContactBinding;
import com.example.servpro.databinding.FragmentProfileBinding;

import org.w3c.dom.Text;

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
        txtName.setText(name);
        txtAge.setText(age);
        txtOccupation.setText(occupation);
        txtWage.setText(wage);
        txtDescription.setText(description);

        return binding.getRoot();
    }
}
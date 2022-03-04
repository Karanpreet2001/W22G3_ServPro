package com.example.servpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{


    Context context;

    ArrayList name, city, phone;

    public CustomAdapter(Context context, ArrayList name, ArrayList city, ArrayList phone) {
        this.context = context;
        this.name = name;
        this.city = city;
        this.phone = phone;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        holder.proName.setText(String.valueOf(name.get(position)));
        holder.proCity.setText(String.valueOf(city.get(position)));
        holder.proPhone.setText(String.valueOf(phone.get(position)));

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView proName, proPhone, proCity;
        public MyViewHolder(@NonNull View itemView) {


            super(itemView);

            proName = itemView.findViewById(R.id.txtProName);
            proCity =itemView.findViewById(R.id.txtProCity);
            proPhone = itemView.findViewById(R.id.txtProPhone);

        }
    }
}

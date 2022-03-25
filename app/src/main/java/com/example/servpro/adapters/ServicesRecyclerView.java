package com.example.servpro.adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servpro.R;
import com.example.servpro.models.ServiceProvider;

import java.util.List;

public class ServicesRecyclerView extends RecyclerView.Adapter<ServicesRecyclerView.ServiceProviderHolder> {

    List<ServiceProvider> serviceProviderList;

    OnClickItem onClickItemListener;

    public ServicesRecyclerView(List<ServiceProvider> serviceProviderList, OnClickItem onClickItem) {
        this.serviceProviderList = serviceProviderList;
        this.onClickItemListener = onClickItem;
    }

    @NonNull
    @Override
    public ServiceProviderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater lf = LayoutInflater.from(parent.getContext());
        View view = lf.inflate(R.layout.layout_row, parent, false);

        ServiceProviderHolder sph = new ServiceProviderHolder(view);

        sph.txtProName = view.findViewById(R.id.txtProName);
        sph.txtProCity = view.findViewById(R.id.txtProCity);
        sph.txtProWage = view.findViewById(R.id.txtProWage);
        sph.txtProPhone = view.findViewById(R.id.txtProPhone);

        sph.itemView.setOnClickListener((View vie)-> {
            onClickItemListener.onClickItem(sph.getAdapterPosition());

        });

        return sph;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceProviderHolder holder, int i) {

        holder.txtProPhone.setText(serviceProviderList.get(i).getPhone());
        holder.txtProName.setText(serviceProviderList.get(i).getServiceProvider());
        holder.txtProCity.setText(serviceProviderList.get(i).getCity());
        holder.txtProWage.setText(serviceProviderList.get(i).getWage());
    }

    @Override
    public int getItemCount() {
        return serviceProviderList.size();
    }

    public class ServiceProviderHolder extends RecyclerView.ViewHolder{

        TextView txtProName;
        TextView txtProCity;
        TextView txtProPhone;
        TextView txtProWage;
        ImageView imgViewSerPro;
        public ServiceProviderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnClickItem{

        void onClickItem(int index);
    }
}

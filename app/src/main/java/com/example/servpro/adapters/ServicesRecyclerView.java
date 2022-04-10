package com.example.servpro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servpro.R;
import com.example.servpro.models.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class ServicesRecyclerView extends RecyclerView.Adapter<ServicesRecyclerView.ServiceProviderHolder> implements Filterable {

    List<ServiceProvider> serviceProviderList;
    List<ServiceProvider> serviceProviderListFull;

    OnClickItem onClickItemListener;
    OnClickMapItem onClickMapItem;

    public ServicesRecyclerView(List<ServiceProvider> serviceProviderList, OnClickItem onClickItemListener, OnClickMapItem onClickMapItem) {
        this.serviceProviderList = serviceProviderList;
        this.onClickItemListener = onClickItemListener;
        this.onClickMapItem = onClickMapItem;
        serviceProviderListFull= new ArrayList<>(serviceProviderList);
    }

//    public ServicesRecyclerView(List<ServiceProvider> serviceProviderList, OnClickItem onClickItemListener) {
//        this.serviceProviderList = serviceProviderList;
//        this.onClickItemListener = onClickItemListener;
//        serviceProviderListFull= new ArrayList<>(serviceProviderList);
//    }

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
        sph.imgViewSerPro=view.findViewById(R.id.imgViewSerPro);

        sph.itemView.setOnClickListener((View vie)-> {
            onClickItemListener.onClickItem(sph.getAdapterPosition());

        });

        sph.txtProCity.setOnClickListener((View vie)-> {
            onClickMapItem.onClickMapItem(sph.getAdapterPosition());

        });



        return sph;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceProviderHolder holder, int i) {

        holder.txtProPhone.setText(serviceProviderList.get(i).getPhone());
        holder.txtProName.setText(serviceProviderList.get(i).getServiceProvider());
        holder.txtProCity.setText(serviceProviderList.get(i).getCity());
        holder.txtProWage.setText("$ "+serviceProviderList.get(i).getWage());
        holder.imgViewSerPro.setImageResource(serviceProviderList.get(i).getImages());
//        holder.imgViewSerPro.set
    }

    @Override
    public int getItemCount() {
        return serviceProviderList.size();
    }

    @Override
    public Filter getFilter() {
        return runFilterable;
    }

    private Filter runFilterable = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ServiceProvider> filteredList = new ArrayList<>();

            if(charSequence==null || charSequence.length()==0){
                filteredList.addAll(serviceProviderListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (ServiceProvider sp : serviceProviderListFull){
                    if(sp.getServiceProvider().toLowerCase().contains(filterPattern)){
                        filteredList.add(sp);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values=filteredList;

            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            serviceProviderList.clear();
            serviceProviderList.addAll((List)filterResults.values);
            notifyDataSetChanged();

        }
    };

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

    public interface OnClickMapItem{

        void onClickMapItem(int index);
    }
}

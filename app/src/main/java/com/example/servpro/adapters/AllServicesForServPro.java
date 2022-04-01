package com.example.servpro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servpro.R;
import com.example.servpro.models.ServiceProvider;

import org.w3c.dom.Text;

import java.util.List;

public class AllServicesForServPro extends RecyclerView.Adapter<AllServicesForServPro.ServiceHolder> {

    List<ServiceProvider> sp;
    OnClickIndex onClickIndex;

    public AllServicesForServPro(List<ServiceProvider> sp) {
        this.sp = sp;
    }

    public AllServicesForServPro(List<ServiceProvider> sp, OnClickIndex onClickIndex) {
        this.sp = sp;
        this.onClickIndex = onClickIndex;
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_all_services, parent, false);

        ServiceHolder serviceHolder = new ServiceHolder(view);

        serviceHolder.txtPhone = view.findViewById(R.id.txtAllServPhone);
        serviceHolder.txtOccupation = view.findViewById(R.id.txtAllServOccupation);
        serviceHolder.txtAddress = view.findViewById(R.id.txtAllServAddress);
        serviceHolder.txtWage = view.findViewById(R.id.txtAllServWage);

        serviceHolder.itemView.setOnClickListener((View vie)-> {

            onClickIndex.onCickIndex(serviceHolder.getAdapterPosition());
        });
        return serviceHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder holder, int position) {

        holder.txtWage.setText(sp.get(position).getWage());
        holder.txtOccupation.setText(sp.get(position).getOccupation());
        holder.txtPhone.setText(sp.get(position).getPhone());
        holder.txtAddress.setText(sp.get(position).getStreet());
    }

    @Override
    public int getItemCount() {
        return sp.size();
    }


    public class ServiceHolder extends RecyclerView.ViewHolder {

        TextView txtPhone;
        TextView txtAddress;
        TextView txtWage;
        TextView txtOccupation;
        public ServiceHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnClickIndex{
        void onCickIndex(int index);
    }
}

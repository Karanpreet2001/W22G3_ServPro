package com.example.servpro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servpro.databinding.LayoutDealBinding;
import com.example.servpro.models.Deals;

import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealHolder> {

    List<Deals> dealsList;
    OnClickListener onClickListener;

    public DealsAdapter(List<Deals> dealsList, OnClickListener onClickListener) {
        this.dealsList = dealsList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public DealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutDealBinding binding= LayoutDealBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        DealHolder dealHolder = new DealHolder(binding);


        dealHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onItemClick(dealHolder.getAdapterPosition());
            }
        });

        return dealHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull DealHolder holder, int position) {

        holder.holderBinding.txtDealEmail.setText(dealsList.get(position).getCustemail());
        holder.holderBinding.txtDealDate.setText(dealsList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return dealsList.size();
    }


    public class DealHolder extends RecyclerView.ViewHolder{

        LayoutDealBinding holderBinding;

        public DealHolder(LayoutDealBinding binding) {
            super(binding.getRoot());
            holderBinding=binding;
        }
    }
    public interface OnClickListener{
        void onItemClick(int index);
    }
}

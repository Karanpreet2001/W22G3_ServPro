package com.example.servpro.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servpro.R;
import com.example.servpro.models.Connection;

import java.util.List;

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.ConnectionHolder> {

    List<Connection> connectionList;
    OnClickListener onClickListener;

    public ConnectionAdapter(List<Connection> connectionList, OnClickListener onClickListener) {
        this.connectionList = connectionList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ConnectionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_connect, parent, false);

        ConnectionHolder ch = new ConnectionHolder(view);

        ch.txtName = view.findViewById(R.id.txtConnectionName);
        ch.txtPhone = view.findViewById(R.id.txtConnectionPhone);

        ch.itemView.setOnClickListener((View viw) ->{
                onClickListener.onClickItem(ch.getAdapterPosition());

        });

        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull ConnectionHolder holder, int position) {

        holder.txtPhone.setText(connectionList.get(position).getCustemail());
        holder.txtName.setText(connectionList.get(position).getCustomername());
    }

    @Override
    public int getItemCount() {
        return connectionList.size();
    }

    public  class ConnectionHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtPhone;
        public ConnectionHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnClickListener{

        void onClickItem(int index);
    }
}

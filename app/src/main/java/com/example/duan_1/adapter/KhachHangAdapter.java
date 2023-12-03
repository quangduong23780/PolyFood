package com.example.duan_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.R;
import com.example.duan_1.modul.User;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder>{
    private Context context;
    private ArrayList<User> list;

    public KhachHangAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaKH.setText("Mã: "+list.get(position).getMauser());
        holder.txtTenKH.setText("Tên: "+list.get(position).getName());
        holder.txtSDTKH.setText("SDT: "+list.get(position).getPhone());
        holder.txtDiaChiKH.setText("Địa chỉ: "+list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaKH,txtTenKH,txtSDTKH,txtDiaChiKH;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaKH=itemView.findViewById(R.id.txtMaKH);
            txtTenKH=itemView.findViewById(R.id.txtTenKH);
            txtSDTKH=itemView.findViewById(R.id.txtSDTKH);
            txtDiaChiKH=itemView.findViewById(R.id.txtDiaChiKH);
        }
    }
}

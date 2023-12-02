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
import com.example.duan_1.modul.Donhang;
import com.example.duan_1.modul.GioHang;
import com.example.duan_1.modul.HoaDon;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder>{
    private Context context;
    private ArrayList<HoaDon> list;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.txtid.setText(String.valueOf(list.get(position).getId()));
         holder.txtMasp.setText(String.valueOf(list.get(position).getMasp()));
         holder.txtSoluong.setText(String.valueOf(list.get(position).getSoluong()));
         holder.txtprice.setText(String.valueOf(list.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtid,txtprice,txtMasp,txtSoluong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.txtMakh);
            txtprice = itemView.findViewById(R.id.txtGia);
            txtMasp = itemView.findViewById(R.id.txtMasp);
            txtSoluong  = itemView.findViewById(R.id.txtSoluong);
        }
    }
}

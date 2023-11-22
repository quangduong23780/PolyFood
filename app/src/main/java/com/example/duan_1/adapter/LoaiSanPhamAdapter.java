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
import com.example.duan_1.modul.LoaiProduct;

import java.util.ArrayList;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.ViewHolder>{
    Context context;
    ArrayList<LoaiProduct> list;

    public LoaiSanPhamAdapter(Context context, ArrayList<LoaiProduct> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_qlloaiprduct, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtmaloai.setText("Mã loại: "+list.get(position).getId());
        holder.txttenloai.setText("Tên loại: "+list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView  txtmaloai,txttenloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaloai=itemView.findViewById(R.id.txt_MaLoai);
            txttenloai=itemView.findViewById(R.id.txt_TenLoai);
        }
    }
}

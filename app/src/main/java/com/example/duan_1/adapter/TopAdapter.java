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
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Product> list;

    public TopAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_top,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txtMaProduct.setText("Mã sản phẩm: "+String.valueOf(list.get(position).getIdproduct()));
       holder.txtNameProduct.setText("Tên sản phẩm: "+list.get(position).getNameproduct());
       holder.txtSoLuongMua.setText("Số lượng mua: "+String.valueOf(list.get(position).getSoluongmua()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaProduct,txtNameProduct,txtSoLuongMua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaProduct = itemView.findViewById(R.id.txtMaProduct);
            txtNameProduct= itemView.findViewById(R.id.txtNameProduct);
            txtSoLuongMua = itemView.findViewById(R.id.txtSoLuongMua);
        }
    }
}

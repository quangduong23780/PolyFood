package com.example.duan_1.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.InterfaceRecycle;
import com.example.duan_1.R;
import com.example.duan_1.activity.ChiTietProductActivity;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class TrangChuAdapter extends RecyclerView.Adapter<TrangChuAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Product> list;
    InterfaceRecycle interfaceRecycle;
    public TrangChuAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnclickRecycle(InterfaceRecycle interfaceRecycle) {
        this.interfaceRecycle = interfaceRecycle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Product product = list.get(position);
        holder.txtName.setText(product.getNameproduct());
        holder.txtPrice.setText(String.valueOf(product.getPriceproduct()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(interfaceRecycle!= null){
                   interfaceRecycle.setOnclick(position);
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgproduct,imgadd;
        TextView txtName,txtPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.txtName);
            imgproduct=itemView.findViewById(R.id.imgproduct);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            imgadd=itemView.findViewById(R.id.imgadd);
        }
    }

}

package com.example.duan_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan_1.R;
import com.example.duan_1.modul.GioHang;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends BaseAdapter {
       Context context;
       ArrayList<GioHang> list;

    public GioHangAdapter(Context context, ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_giohang, null);
            viewHolder.txtName = view.findViewById(R.id.txt_name_giohang);
            viewHolder.txtSoluong = view.findViewById(R.id.txt_soluong_giohang);
            viewHolder.txtGia = view.findViewById(R.id.txt_price_giohang);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        GioHang gioHang = list.get(i);

        viewHolder.txtName.setText(gioHang.getNameproduct());
        viewHolder.txtSoluong.setText(String.valueOf(gioHang.getSoluong()));
        viewHolder.txtGia.setText(String.valueOf(gioHang.getPrirceproduct()));
        return view;
    }
    private class ViewHolder{
        TextView txtName, txtSoluong,txtGia;
    }
}
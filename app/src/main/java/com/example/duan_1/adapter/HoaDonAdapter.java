package com.example.duan_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.InterfaceRecycle;
import com.example.duan_1.R;
import com.example.duan_1.modul.Donhang;
import com.example.duan_1.modul.GioHang;
import com.example.duan_1.modul.HoaDon;

import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder>{
    private Context context;
    private ArrayList<HoaDon> list;
    InterfaceRecycle interfaceRecycle;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
    }
    public void setOnclickHoadon(InterfaceRecycle interfaceRecycle){
        this.interfaceRecycle = interfaceRecycle;
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
        holder.txtid.setText("Mã Khách hàng: " + list.get(position).getId());
        holder.txtMasp.setText("Mã Sản phẩm: " + list.get(position).getMasp());
        holder.txtSoluong.setText("Số lượng: " + list.get(position).getSoluong());
        holder.txtprice.setText("Giá: " + list.get(position).getPrice());

        holder.txtNgay.setText("Ngày: " + list.get(position).getNgay());

        if (list.get(position).getTrangthai() == 0) {
            holder.txtTrangthai.setText("Chưa giao hàng");
            holder.txtTrangthai.setTextColor(Color.RED);
        } else {
            holder.txtTrangthai.setText("Đang giao hàng");
            holder.txtTrangthai.setTextColor(Color.BLUE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interfaceRecycle != null) {
                    interfaceRecycle.setOnclick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtid,txtprice,txtMasp,txtSoluong,txtTrangthai,txtNgay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.txtMakh);
            txtprice = itemView.findViewById(R.id.txtGia);
            txtMasp = itemView.findViewById(R.id.txtMasp);
            txtSoluong  = itemView.findViewById(R.id.txtSoluong);
            txtTrangthai = itemView.findViewById(R.id.txtTrangthai);
            txtNgay = itemView.findViewById(R.id.txtNgay);
        }
    }
}

package com.example.duan_1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.InterfaceRecycle;
import com.example.duan_1.R;
import com.example.duan_1.dao.HoaDonDao;
import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.HoaDon;
import com.example.duan_1.modul.LoaiProduct;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder> {
    private InterfaceRecycle interfaceRecycle;
    private Context context;
    private ArrayList<HoaDon> list;

    private HoaDonDao hoaDonDao;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
        hoaDonDao = new HoaDonDao(context);

    }

    public void setOnclickHoadon(InterfaceRecycle interfaceRecycle) {
        this.interfaceRecycle = interfaceRecycle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtid.setText("Mã Khách hàng: " + list.get(position).getId());
        holder.txtMasp.setText("Mã Sản phẩm: " + list.get(position).getMasp());
        holder.txtSoluong.setText("Số lượng: " + list.get(position).getSoluong());
        holder.txtprice.setText("Giá: " + list.get(position).getPrice());
        holder.txtNgay.setText("Ngày: " + list.get(position).getNgay());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDiaLogDelete(position);
            }
        });
        int trangthai = list.get(position).getTrangthai();
        if (trangthai == 0) {
            holder.txtTrangthai.setText("Chưa giao hàng");
            holder.txtTrangthai.setTextColor(Color.RED);
        } else if (trangthai == 1) {
            holder.txtTrangthai.setText("Đang giao hàng");
            holder.txtTrangthai.setTextColor(Color.BLUE);

            // Đặt thời gian chờ 1 phút và sau đó thay đổi trạng thái thành "Đã giao hàng"
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Thay đổi trạng thái thành "Đã giao hàng"
                    holder.txtTrangthai.setText("Đã giao hàng");
                    holder.txtTrangthai.setTextColor(Color.GREEN);

                    // Cập nhật trạng thái trong danh sách
                    HoaDon hoaDon = list.get(position);
                    hoaDon.setTrangthai(2);

                    hoaDonDao.updateHoaDon(hoaDon.getId(),hoaDon.getTrangthai());
                    // Thông báo về sự thay đổi trạng thái
                    loadData();
                }
            }, 1 * 60 * 1000); // Đợi 1 phút (1 * 60 * 1000 milliseconds)
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
    public void ShowDiaLogDelete(int position){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xoá");
        builder.setMessage("Bạn có muốn xoá hoá đơn không?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HoaDon hoaDon = list.get(position);
                int delete = hoaDonDao.DeleteHoaDon(hoaDon.getId());
                if (delete == 1){
                    Toast.makeText(context, "Xoá hoá đơn thành công", Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    loadData();
                } else {
                    Toast.makeText(context, "Xoá hoá đơn thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtid, txtprice, txtMasp, txtSoluong, txtTrangthai, txtNgay;
        ImageView ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.txtMakh);
            txtprice = itemView.findViewById(R.id.txtGia);
            txtMasp = itemView.findViewById(R.id.txtMasp);
            txtSoluong = itemView.findViewById(R.id.txtSoluong);
            txtTrangthai = itemView.findViewById(R.id.txtTrangthai);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            ivDelete = itemView.findViewById(R.id.ivDelete);

        }
    }
    private void loadData() {
        list.clear();
        list.addAll(hoaDonDao.getDSHoaDon());
        notifyDataSetChanged();
    }
}
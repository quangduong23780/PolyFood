package com.example.duan_1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.R;
import com.example.duan_1.dao.LoaiSanPhamDao;
import com.example.duan_1.modul.LoaiProduct;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.ViewHolder>{
    Context context;
    ArrayList<LoaiProduct> list;
    LoaiSanPhamDao loaiSanPhamDao;

    public LoaiSanPhamAdapter(Context context, ArrayList<LoaiProduct> list, LoaiSanPhamDao loaiSanPhamDao) {
        this.context = context;
        this.list = list;
        this.loaiSanPhamDao = loaiSanPhamDao;
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
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ShowDiaLogUpdate(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDiaLogDelete(position);
            }
        });
    }
    public void ShowDiaLogUpdate(LoaiProduct loaiProduct){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View  view = inflater.inflate(R.layout.dialog_update_loaisp,null);
        builder.setView(view);

        EditText edt_Tenloai = view.findViewById(R.id.edt_TenLoai);
        TextView txt_MaLoai = view.findViewById(R.id.txt_maloai);

        txt_MaLoai.setText(""+loaiProduct.getId());
        edt_Tenloai.setText(loaiProduct.getName());

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 String name = edt_Tenloai.getText().toString();
                 int  id = loaiProduct.getId();
                 if (name.isEmpty()){
                     Toast.makeText(context, "Vui lòng điền tên loại sản phẩm", Toast.LENGTH_SHORT).show();
                 }

                boolean check = loaiSanPhamDao.UpdateLoaiSP(id,name);
                if (check) {
                   loadData();
                    Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Cập nhật thông tin không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog  = builder.create();
        alertDialog.show();

    }
    public void ShowDiaLogDelete(int position){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xoá");
        builder.setMessage("Bạn có muốn xoá loại sản phẩm không?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoaiProduct loaiProduct = list.get(position);
                int delete = loaiSanPhamDao.DeleteLoaiSP(loaiProduct.getId());
                if (delete == 1){
                    Toast.makeText(context, "Xoá loại sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    loadData();
                } else {
                    Toast.makeText(context, "Xoá loại sản phẩm thất bại", Toast.LENGTH_SHORT).show();
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
        TextView  txtmaloai,txttenloai;
        ImageView ivEdit,ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaloai=itemView.findViewById(R.id.txt_MaLoai);
            txttenloai=itemView.findViewById(R.id.txt_TenLoai);
            ivEdit=itemView.findViewById(R.id.ivEdit);
            ivDelete=itemView.findViewById(R.id.ivDel);
        }
    }
    private void loadData() {
        list.clear();
        list.addAll(loaiSanPhamDao.getDSLoaiSP());
        notifyDataSetChanged();
    }
}

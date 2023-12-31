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
import com.example.duan_1.dao.SanPhamDao;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolde>{
    Context context;
    ArrayList<Product>list;
    SanPhamDao sanPhamDao;

    public SanPhamAdapter(Context context, ArrayList<Product> list, SanPhamDao sanPhamDao) {
        this.context = context;
        this.list = list;
        this.sanPhamDao = sanPhamDao;
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_qlproduct,parent,false);
        return new ViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
        holder.txt_masp.setText("Mã sản phẩm: "+list.get(position).getIdproduct());
        holder.txt_tensp.setText("Tên sản phẩm: " + list.get(position).getNameproduct());
        int typeProduct = Integer.parseInt(list.get(position).getTypeproduct()); // Chuyển đổi kiểu dữ liệu
        String loai = "";
        if (typeProduct == 1) {
            loai = "Đồ ăn";
        } else if (typeProduct == 2) {
            loai = "Đồ uống";
        }
        holder.txt_loaisp.setText("Loại: " + loai);
        holder.txt_giasp.setText("Gía: " + list.get(position).getPriceproduct());
        holder.txt_imgsp.setText("Link ảnh: " + list.get(position).getImgproduct());

        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ShowDiaLogDelete(position);
            }
        });
        holder.ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogUpdateProduct(list.get(holder.getAdapterPosition()));
            }
        });
    }
    public void ShowDiaLogDelete(int position){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xoá");
        builder.setMessage("Bạn có muốn xoá sản phẩm không?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Product product = list.get(position);
                int delete = sanPhamDao.xoaProduct(product.getIdproduct());
                if (delete == 1){
                    Toast.makeText(context, "Xoá sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xoá sản phẩm thất bại", Toast.LENGTH_SHORT).show();
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

    public class ViewHolde extends RecyclerView.ViewHolder {
        TextView txt_masp,txt_tensp,txt_loaisp,txt_giasp,txt_imgsp;
        ImageView ivedit,ivdelete;
        public ViewHolde(@NonNull View itemView) {
            super(itemView);
            txt_masp=itemView.findViewById(R.id.txt_masp);
            txt_tensp=itemView.findViewById(R.id.txt_tensp);
            txt_loaisp=itemView.findViewById(R.id.txt_loaisp);
            txt_giasp=itemView.findViewById(R.id.txt_giasp);
            txt_imgsp=itemView.findViewById(R.id.txt_imgsp);
            ivdelete=itemView.findViewById(R.id.ivDel);
            ivedit=itemView.findViewById(R.id.ivEdit);
        }
    }
    private void showDialogUpdateProduct(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_editproduct, null);
        builder.setView(view);

        TextView txtMasp = view.findViewById(R.id.txtMasp);
        EditText edtTensp = view.findViewById(R.id.edtTensp);
        EditText edtPrice = view.findViewById(R.id.edtPrice);
        EditText edtImage = view.findViewById(R.id.edtImage);

        txtMasp.setText(String.valueOf(product.getIdproduct()));
        edtTensp.setText(product.getNameproduct());
        edtPrice.setText(String.valueOf(product.getPriceproduct()));
        edtImage.setText(product.getImgproduct());

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensp = edtTensp.getText().toString();
                String price = edtPrice.getText().toString();
                String image = edtImage.getText().toString();
                int id = product.getIdproduct();
                int pricesp = 0;

                if (tensp.isEmpty() || price.isEmpty() || image.isEmpty()) {
                    Toast.makeText(context, "Vui lòng điền đủ thông tin sản phẩm", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    pricesp = Integer.parseInt(price);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Giá sản phẩm không hợp lệ", Toast.LENGTH_LONG).show();
                    return;
                }

                boolean check = sanPhamDao.UpdateThongTinProduct(id, tensp, pricesp, image);
                if (check) {
                    Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_LONG).show();
                    loadData();
                } else {
                    Toast.makeText(context, "Cập nhật thông tin không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setPositiveButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadData() {
        list.clear();
        list.addAll(sanPhamDao.getDSProduct());
        notifyDataSetChanged();
    }
}

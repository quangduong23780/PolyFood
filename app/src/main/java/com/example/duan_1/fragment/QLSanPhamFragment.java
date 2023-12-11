package com.example.duan_1.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.R;
import com.example.duan_1.dao.SanPhamDao;
import com.example.duan_1.adapter.SanPhamAdapter;
import com.example.duan_1.modul.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLSanPhamFragment extends Fragment {
    RecyclerView recycler_qlsp;
    SanPhamDao sanPhamDao;
    ArrayList<Product> list;
    SanPhamAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlsanpham, container, false);
        recycler_qlsp = view.findViewById(R.id.recylerSanPham);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        sanPhamDao = new SanPhamDao(getActivity());
        list = sanPhamDao.getDSProduct();
        adapter = new SanPhamAdapter(getContext(), list,sanPhamDao);
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        return view;
    }
    private void loadData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_qlsp.setLayoutManager(linearLayoutManager);
        recycler_qlsp.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsp,null);
        builder.setView(view);

        EditText edtTensp = view.findViewById(R.id.edtTensp);
        EditText edtGiasp = view.findViewById(R.id.edtGiasp);
        EditText edtLoaisp = view.findViewById(R.id.edtLoaisp);
        EditText edtimage = view.findViewById(R.id.edtimage);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensp = edtTensp.getText().toString();
                String loaiString = edtLoaisp.getText().toString();
                String giaspString = edtGiasp.getText().toString();
                String image = edtimage.getText().toString();

                if (tensp.isEmpty() || loaiString.isEmpty() || giaspString.isEmpty() || image.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng điền đủ thông tin sản phẩm", Toast.LENGTH_LONG).show();
                    return;
                }

                int loai = 0;
                int giasp = 0;

                try {
                    loai = Integer.parseInt(loaiString);
                    giasp = Integer.parseInt(giaspString);
                } catch (NumberFormatException e) {
                    // Xử lý ngoại lệ nếu chuỗi không hợp lệ
                    e.printStackTrace();
                }

                boolean check = sanPhamDao.themSanPham(tensp, loai, giasp, image);
                if (check) {
                    Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_LONG).show();
                    list.clear();
                    list.addAll(sanPhamDao.getDSProduct());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Thêm sản phẩm thất bại", Toast.LENGTH_LONG).show();
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
}

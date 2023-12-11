package com.example.duan_1.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.R;
import com.example.duan_1.adapter.LoaiSanPhamAdapter;
import com.example.duan_1.dao.LoaiSanPhamDao;
import com.example.duan_1.modul.LoaiProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLLoaiSanPhamFragment extends Fragment {
    RecyclerView recycler_qlloaiSp;
    LoaiSanPhamDao loaiSanPhamDao;
    ArrayList<LoaiProduct> list;
    LoaiSanPhamAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisp, container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatAdd);

        recycler_qlloaiSp = view.findViewById(R.id.recyclerLoaiSp);

        loaiSanPhamDao = new LoaiSanPhamDao(getContext());
        LoadData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDiaLogAdd();
            }
        });
        return view;
    }
    public void ShowDiaLogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialiog_add_loaisp,null);
        builder.setView(view);
        EditText edt_name = view.findViewById(R.id.edt_TenLoai);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String  name =  edt_name.getText().toString();

                if (name.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng điền tên loại sản phẩm", Toast.LENGTH_SHORT).show();
                }
                boolean check = loaiSanPhamDao.AddLoaiSP(name);
                if (check) {
                    Toast.makeText(getContext(), "Thêm loại sản phẩm thành công", Toast.LENGTH_LONG).show();
                    list.clear();
                    list.addAll(loaiSanPhamDao.getDSLoaiSP());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Thêm loại sản phẩm thất bại", Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void LoadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_qlloaiSp.setLayoutManager(linearLayoutManager);
        list = loaiSanPhamDao.getDSLoaiSP();
        adapter = new LoaiSanPhamAdapter(getContext(),list,loaiSanPhamDao);
        recycler_qlloaiSp.setAdapter(adapter);
    }
}

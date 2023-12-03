package com.example.duan_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.R;
import com.example.duan_1.adapter.KhachHangAdapter;
import com.example.duan_1.adapter.LoaiSanPhamAdapter;
import com.example.duan_1.dao.LoaiSanPhamDao;
import com.example.duan_1.dao.UserDao;
import com.example.duan_1.modul.LoaiProduct;
import com.example.duan_1.modul.User;

import java.util.ArrayList;

public class QLKhachHang extends Fragment {
    RecyclerView recycler_qlKH;
    UserDao userDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlkhachhang,container,false);
        recycler_qlKH = view.findViewById(R.id.recyclerQLKhachHang);

        userDao = new UserDao(getContext());
        LoadData();
        return view;
    }
    public void LoadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_qlKH.setLayoutManager(linearLayoutManager);
        ArrayList<User> list =userDao.getALL();
        KhachHangAdapter adapter = new KhachHangAdapter(getContext(),list);
        recycler_qlKH.setAdapter(adapter);
    }
}

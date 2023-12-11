package com.example.duan_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class QLKhachHang extends Fragment {
    ArrayList<User> list;
    RecyclerView recycler_qlKH;
    UserDao userDao;
    ImageView imgload;
    KhachHangAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlkhachhang,container,false);
        recycler_qlKH = view.findViewById(R.id.recyclerQLKhachHang);
        imgload=view.findViewById(R.id.imgLoad);

        userDao = new UserDao(getContext());
        LoadData();
        imgload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(list, new Comparator<User>() {
                    @Override
                    public int compare(User user1, User user2) {
                        return user2.getMauser()- user1.getMauser();
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    public void LoadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_qlKH.setLayoutManager(linearLayoutManager);
        list =userDao.getALL();
        adapter = new KhachHangAdapter(getContext(),list);
        recycler_qlKH.setAdapter(adapter);
    }
}

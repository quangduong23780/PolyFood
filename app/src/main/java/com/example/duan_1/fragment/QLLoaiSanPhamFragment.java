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
import com.example.duan_1.adapter.LoaiSanPhamAdapter;
import com.example.duan_1.dao.LoaiSanPhamDao;
import com.example.duan_1.modul.LoaiProduct;

import java.util.ArrayList;

public class QLLoaiSanPhamFragment extends Fragment {
    RecyclerView recycler_qlloaiSp;
    LoaiSanPhamDao loaiSanPhamDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisp, container, false);

        recycler_qlloaiSp = view.findViewById(R.id.recyclerLoaiSp);

        loaiSanPhamDao = new LoaiSanPhamDao(getContext());
        LoadData();
        return view;
    }
    public void LoadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_qlloaiSp.setLayoutManager(linearLayoutManager);
        ArrayList<LoaiProduct> list = loaiSanPhamDao.getDSLoaiSP();
        LoaiSanPhamAdapter adapter = new LoaiSanPhamAdapter(getContext(),list);
        recycler_qlloaiSp.setAdapter(adapter);
    }
}

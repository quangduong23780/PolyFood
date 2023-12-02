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
import com.example.duan_1.adapter.HoaDonAdapter;
import com.example.duan_1.dao.HoaDonDao;
import com.example.duan_1.modul.GioHang;
import com.example.duan_1.modul.HoaDon;

import java.util.ArrayList;

public class QLHoaDonFragment extends Fragment {
    RecyclerView recyclerView;
    HoaDonDao hoaDonDao;
    ArrayList<HoaDon> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlhoadon,container,false);
        recyclerView = view.findViewById(R.id.recylerQLHoaDon);
        hoaDonDao = new HoaDonDao(getContext());
        list= hoaDonDao.getDSHoaDon();
        loadData();
        return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        HoaDonAdapter adapter = new HoaDonAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }
}

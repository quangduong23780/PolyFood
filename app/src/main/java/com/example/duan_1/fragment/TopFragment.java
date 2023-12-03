package com.example.duan_1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.R;
import com.example.duan_1.adapter.TopAdapter;
import com.example.duan_1.dao.TopDAO;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class TopFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top,container,false);

        RecyclerView recyclerTop = view.findViewById(R.id.recycle_top);

        TopDAO topDAO = new TopDAO(getContext());
        ArrayList<Product> list = topDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop.setLayoutManager(linearLayoutManager);
        TopAdapter adapter = new TopAdapter(getContext(),list);
        recyclerTop.setAdapter(adapter);

        return view;
    }
}

package com.example.duan_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan_1.InterfaceRecycle;
import com.example.duan_1.R;
import com.example.duan_1.activity.ChiTietProductActivity;
import com.example.duan_1.adapter.SanPhamAdapter;
import com.example.duan_1.adapter.TrangChuAdapter;
import com.example.duan_1.dao.LoaiSanPhamDao;
import com.example.duan_1.dao.SanPhamDao;
import com.example.duan_1.dao.TrangChuDao;
import com.example.duan_1.modul.GioHang;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment {
    RecyclerView recycler_product;
    TrangChuDao trangChuDao;
    ArrayList<Product> list;
    TrangChuAdapter adapter;
    public static ArrayList<GioHang> manggiohang;
    DrawerLayout drawerLayout;
    ViewFlipper viewFlipper;
   public TrangChuFragment(){

   }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_trangchu,container,false);

        Anhxa(view);
        trangChuDao = new TrangChuDao(getActivity());
        list = trangChuDao.getDSProductTrangChu();
        adapter = new TrangChuAdapter(getContext(),list);
        adapter.setOnclickRecycle(new InterfaceRecycle() {
            @Override
            public void setOnclick(int position) {
                Intent intent = new Intent(getActivity(), ChiTietProductActivity.class);
                intent.putExtra("thongtinchitiet", list.get(position));
                startActivity(intent);
            }
        });
        loadData();

        List<Integer> mangquangcao = new ArrayList<>();
        mangquangcao.add(R.drawable.zed);
        mangquangcao.add(R.drawable.zed2);
        mangquangcao.add(R.drawable.zed3);

        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

            Glide.with(getContext())
                    .load(mangquangcao.get(i))
                    .into(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        Animation slideIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        Animation slideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slideIn);
        viewFlipper.setOutAnimation(slideOut);



       return view;
    }

    private void Anhxa(View view) {
        recycler_product = view.findViewById(R.id.recycle_trangchu);
        drawerLayout= view.findViewById(R.id.drawerlayout);
        viewFlipper= view.findViewById(R.id.viewflipper);
        if(manggiohang== null){
            manggiohang = new ArrayList<>();
        }
    }

    private void loadData() {
        recycler_product.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler_product.setAdapter(adapter);
    }
}

package com.example.duan_1.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_1.InterfaceRecycle;
import com.example.duan_1.R;
import com.example.duan_1.adapter.HoaDonAdapter;
import com.example.duan_1.dao.DonhangDao;
import com.example.duan_1.dao.HoaDonDao;
import com.example.duan_1.dao.SanPhamDao;
import com.example.duan_1.modul.Donhang;
import com.example.duan_1.modul.HoaDon;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class QLHoaDonFragment extends Fragment {
    RecyclerView recyclerView;
    HoaDonDao hoaDonDao;
    ArrayList<HoaDon> list;
    HoaDonAdapter adapter;
    ArrayList<Donhang> listKhachhang;
    DonhangDao donhangDao;
    ArrayList<Product> listProduct;
    SanPhamDao sanPhamDao;
    int a;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlhoadon,container,false);
        Anhxa(view);
        loadData();
        adapter.setOnclickHoadon(new InterfaceRecycle() {
            @Override
            public void setOnclick(int position) {
                a = position;
                openDialog(Gravity.CENTER);
            }
        });
        return view;
    }

    private void openDialog(int gravity) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_chitietdonhang);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = gravity;
        if(Gravity.CENTER == gravity ){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }
        HoaDon hoaDon = list.get(a);

        TextView txtTenkhachhang = dialog.findViewById(R.id.txtTenkhachhang);
        TextView txtSodienthoai = dialog.findViewById(R.id.txtSodienthoai);
        TextView txtDiachi = dialog.findViewById(R.id.txtDiachi);
        TextView txtTensanpham = dialog.findViewById(R.id.txtTensanpham);
        TextView txtSoluong = dialog.findViewById(R.id.txtSoluong);
        TextView txtGia = dialog.findViewById(R.id.txtGiachitietdonhang);
        Button btnGiaohang = dialog.findViewById(R.id.btnGiaohang);

        Donhang donhang = donhangDao.getID(String.valueOf(hoaDon.getId()));
        txtTenkhachhang.setText("Tên Khách Hàng: "+ donhang.getTenkhachhang());
        txtSodienthoai.setText("Số điện thoại: "+ donhang.getSodienthoai());
        txtDiachi.setText("Địa chỉ: "+ donhang.getDiachi());
        Product product = sanPhamDao.getID(String.valueOf(hoaDon.getMasp()));
        txtTensanpham.setText("Tên Sản phẩm: "+ product.getNameproduct());

        txtSoluong.setText("Số lượng: "+hoaDon.getSoluong());
        txtGia.setText("Giá: "+ hoaDon.getPrice());
        btnGiaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoaDon.setTrangthai(1);
                hoaDonDao.updateHoaDon(hoaDon.getId(), 1);
                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });

        dialog.show();
    }

    private void Anhxa(View view) {
        recyclerView = view.findViewById(R.id.recylerQLHoaDon);
        hoaDonDao = new HoaDonDao(getContext());
        list= hoaDonDao.getDSHoaDon();
        listKhachhang = new ArrayList<>();
        donhangDao = new DonhangDao(getActivity());
        listKhachhang = donhangDao.getAll();
        listProduct = new ArrayList<>();
        sanPhamDao = new SanPhamDao(getActivity());
        listProduct = sanPhamDao.getDSProduct();
    }

    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HoaDonAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }

}

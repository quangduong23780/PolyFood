package com.example.duan_1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan_1.R;
import com.example.duan_1.dao.ChitietDonhangDao;
import com.example.duan_1.dao.DonhangDao;
import com.example.duan_1.fragment.TrangChuFragment;
import com.example.duan_1.modul.Donhang;
import com.example.duan_1.modul.GioHang;

import java.util.ArrayList;

public class ThongTinKHActivity extends AppCompatActivity {
    public EditText edName,edSdt, edEmail,edDiachi,edID;
    public Button btnThanhtoan, btnQuayve;
    DonhangDao dao;
    Donhang donhang;
    ArrayList<Donhang> arrayList;
    ChitietDonhangDao chitietDonhangDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khactivity);
        Anhxa();
        EventButton();
    }
    private void EventButton() {
        btnQuayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  String id = edID.getText().toString();
                final String name = edName.getText().toString();
                final String sdt = edSdt.getText().toString();
                final String email = edEmail.getText().toString();
                final String diachi = edDiachi.getText().toString();
                if(name.length()>0 || sdt.length()>0|| email.length()>0|| diachi.length()>0){
                    donhang = new Donhang();
                    donhang.setId(Integer.parseInt(id));
                    donhang.setTenkhachhang(name);
                    donhang.setSodienthoai(Integer.parseInt(sdt));
                    donhang.setEmail(email);
                    donhang.setDiachi(diachi);
                    if(dao.insert(donhang)>0){
                        for (int i=0;i<TrangChuFragment.manggiohang.size();i++) {
                            GioHang gioHang = TrangChuFragment.manggiohang.get(i);
                            chitietDonhangDao.insert(donhang.getId(),gioHang);
                            Toast.makeText(ThongTinKHActivity.this, "insert success", Toast.LENGTH_SHORT).show();
                        }
                        TrangChuFragment.manggiohang.clear();
                    }else {
                        Toast.makeText(ThongTinKHActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void Anhxa() {
        edID = findViewById(R.id.idDonhang);
        edName = findViewById(R.id.textinputname);
        edSdt = findViewById(R.id.textinputsdt);
        edEmail = findViewById(R.id.textinputemail);
        edDiachi = findViewById(R.id.textinputaddress);
        btnThanhtoan = findViewById(R.id.btnxacnhan);
        btnQuayve = findViewById(R.id.btnquaylai);
        dao = new DonhangDao(ThongTinKHActivity.this);
        chitietDonhangDao = new ChitietDonhangDao(ThongTinKHActivity.this);
        arrayList = dao.getAll();
        edID.setEnabled(false);
        if(arrayList.size() == 0){
            edID.setText("1");
        }else {
            donhang = dao.getAll().get(arrayList.size()-1);
            edID.setText(String.valueOf(donhang.getId()+1));
        }
    }
}
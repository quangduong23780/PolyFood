package com.example.duan_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duan_1.R;
import com.example.duan_1.adapter.GioHangAdapter;
import com.example.duan_1.fragment.TrangChuFragment;
import com.example.duan_1.modul.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    public static TextView Tvtongtien;
    TextView thongbao;
    Button btnXacnhan, btnQuayve;
    GioHangAdapter gioHangAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
      Anhxa();
      checkcData();
      UpdateTongTien();
    }

    private void checkcData() {
        if(TrangChuFragment.manggiohang.size()<1){
            thongbao.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }else {
            listView.setVisibility(View.VISIBLE);
            thongbao.setVisibility(View.INVISIBLE);
        }
    }
    public static void UpdateTongTien(){
        long tongtien=0;
        for(int i=0;i<TrangChuFragment.manggiohang.size();i++){
            tongtien += TrangChuFragment.manggiohang.get(i).getPrirceproduct();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Tvtongtien.setText(decimalFormat.format(tongtien));
    }
    private void Anhxa() {
        toolbar = findViewById(R.id.toolbargiohang);
        listView = findViewById(R.id.listviewgiohang);
        Tvtongtien = findViewById(R.id.textviewtongtien);
        thongbao = findViewById(R.id.textviewthongbao);
        btnQuayve = findViewById(R.id.buttontieptucmuahang);
        btnXacnhan = findViewById(R.id.buttonthanhtoangiohang);

        gioHangAdapter = new GioHangAdapter(GioHangActivity.this, TrangChuFragment.manggiohang);
        listView.setAdapter(gioHangAdapter);
    }

}
package com.example.duan_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        ClickItemListView();
        EventButton();
    }
    private void EventButton() {
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TrangChuFragment.manggiohang.size()>0){
                    Intent intent = new Intent(getApplicationContext(), ThongTinKHActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(GioHangActivity.this, "Giỏ hàng của bạn chưa có sản phẩm nào", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnQuayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void ClickItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (TrangChuFragment.manggiohang.size() ==0) {
                    thongbao.setVisibility(View.VISIBLE);
                }else {
                    TrangChuFragment.manggiohang.remove(i);
                    gioHangAdapter.notifyDataSetChanged();
                    UpdateTongTien();
                    if(TrangChuFragment.manggiohang.size()==0){
                        thongbao.setVisibility(View.VISIBLE);
                    }else {
                        thongbao.setVisibility(View.INVISIBLE);
                        gioHangAdapter.notifyDataSetChanged();
                        UpdateTongTien();
                    }
                }
            }
        });
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

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GioHangActivity.this,ThongTinKHActivity.class));
            }
        });
    }


}
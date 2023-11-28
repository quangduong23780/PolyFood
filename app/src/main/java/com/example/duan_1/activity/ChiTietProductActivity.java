package com.example.duan_1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duan_1.R;
import com.example.duan_1.fragment.TrangChuFragment;
import com.example.duan_1.modul.GioHang;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;
import java.util.List;

public class ChiTietProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txt_nameproduct,txt_loaiproduct,txt_price;
    Spinner spn_soluong;
    Button btn_giohang;
    ImageView  img_product;
    String name, loai, image;
    int giasp;
    int id;
    Product product = new Product();

    private int getQuantityFromAnotherSource() {
        return 5;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_product);
        txt_nameproduct=findViewById(R.id.txt_nameproduct);
        txt_loaiproduct=findViewById(R.id.txt_loaiproduct);
        txt_price=findViewById(R.id.txt_price);
        spn_soluong=findViewById(R.id.spn_soluong);
        btn_giohang=findViewById(R.id.btn_giohang);
        img_product=findViewById(R.id.img_product);
        toolbar = findViewById(R.id.toolbar_chitiet);
        product = new Product();
        getData();
        Spiner();
        ActionBar();
        // Trong ChiTietSanPhamActivity
        // Khi người dùng nhấn nút "Thêm vào giỏ hàng"
        btn_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent và gửi dữ liệu
                if(TrangChuFragment.manggiohang.size()>0){
                    int soluong = Integer.parseInt(spn_soluong.getSelectedItem().toString());
                    boolean exits = false;
                    for (int i=0;i<TrangChuFragment.manggiohang.size();i++){
                        if(TrangChuFragment.manggiohang.get(i).getId()== id){
                            TrangChuFragment.manggiohang.get(i).setSoluong(TrangChuFragment.manggiohang.get(i).getSoluong()+soluong);
                            if(TrangChuFragment.manggiohang.get(i).getSoluong()>10){
                                TrangChuFragment.manggiohang.get(i).setSoluong(10);
                            }

                            TrangChuFragment.manggiohang.get(i).setPrirceproduct(TrangChuFragment.manggiohang.get(i).getPrirceproduct()* giasp);
                            exits= true;
                        }
                    }
                    if(exits==false){
                        int sl = Integer.parseInt(spn_soluong.getSelectedItem().toString());
                        int Giamoi = giasp *sl;
                        TrangChuFragment.manggiohang.add(new GioHang(id,name,Giamoi,image,sl));
                    }
                } else {
                    int sl = Integer.parseInt(spn_soluong.getSelectedItem().toString());
                    int Giamoi = giasp *sl;
                    TrangChuFragment.manggiohang.add(new GioHang(id,name,Giamoi,image,sl));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData(){
        product = (Product) getIntent().getSerializableExtra("thongtinchitiet");
        id = product.getIdproduct();
        name = product.getNameproduct();
        loai = product.getTypeproduct();
        giasp = product.getPriceproduct();
        image = product.getImgproduct();

        txt_nameproduct.setText(name);
        if(Integer.parseInt(loai)==1){
            txt_loaiproduct.setText("Đồ ăn");
        } else if (Integer.parseInt(loai)==2) {
            txt_loaiproduct.setText("Đồ uống");
        }
        txt_price.setText(String.valueOf(giasp));
    }
    private void Spiner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> sl= new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, soluong);
        spn_soluong.setAdapter(sl);
    }
}

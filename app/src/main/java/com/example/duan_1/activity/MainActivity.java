package com.example.duan_1.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.duan_1.R;
import com.example.duan_1.dao.UserDao;
import com.example.duan_1.fragment.DoanhThuFragment;
import com.example.duan_1.fragment.DoiPassFragment;
import com.example.duan_1.fragment.QLDonHangFragment;
import com.example.duan_1.fragment.QLHoaDonFragment;
import com.example.duan_1.fragment.QLKhachHang;
import com.example.duan_1.fragment.QLLoaiSanPhamFragment;
import com.example.duan_1.fragment.QLSanPhamFragment;
import com.example.duan_1.fragment.TopFragment;
import com.example.duan_1.fragment.TrangChuFragment;
import com.example.duan_1.modul.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recyclerViewTrangChu;
    ViewFlipper viewFlipper;
    ListView listViewTrangChu;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    String username;
    String passold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        //recyclerViewTrangChu = findViewById(R.id.recycle);
        viewFlipper = findViewById(R.id.viewflipper);
        //listViewTrangChu = findViewById(R.id.listView);
        drawerLayout = findViewById(R.id.drawerLayout);
        frameLayout = findViewById(R.id.framelayout);
        View headerLayout = navigationView.getHeaderView(0);
        rePlaceFragment(new TrangChuFragment());
        navigationView.getMenu().findItem(R.id.mTrangChu).setCheckable(true);
        //view flipper
        List<Integer> mangquangcao = new ArrayList<>();
        mangquangcao.add(R.drawable.banner01);
        mangquangcao.add(R.drawable.banner02);
        mangquangcao.add(R.drawable.banner03);

        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

            Glide.with(getApplicationContext())
                    .load(mangquangcao.get(i))
                    .into(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        Animation slideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slideIn);
        viewFlipper.setOutAnimation(slideOut);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.baseline_menu_24);

        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.cart));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.mTrangChu) {
                    fragment = new TrangChuFragment();
                }
                if (item.getItemId() == R.id.mQLLoaiSP) {
                    fragment = new QLLoaiSanPhamFragment();
                }
                if (item.getItemId() == R.id.mQLSanPham) {
                    fragment = new QLSanPhamFragment();
                }
                if (item.getItemId() == R.id.mQLHoaDon) {
                    fragment = new QLHoaDonFragment();
                }
                if (item.getItemId() == R.id.mQLKhachHang) {
                    fragment = new QLKhachHang();
                }
                if (item.getItemId() == R.id.mDoanhThu) {
                    fragment = new DoanhThuFragment();
                }
                if (item.getItemId() == R.id.mTop) {
                    fragment = new TopFragment();
                }
                if (item.getItemId() == R.id.mDoiMatKhau) {
                    showDialogDoiMatKhau();
                }
                if (item.getItemId() == R.id.mThoat) {
                  ShowDiLogOut();
                }  if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle(item.getTitle());
                return false;
            }
        });

        //hiển thị một số chức năng cho admin
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
        String role = sharedPreferences.getString("role","");
        SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        username = sharedPreferences1.getString("username", "");
        passold = sharedPreferences1.getString("password", "");
        Log.d("user", "onCreate: "+ username);
        Log.d("pass", "onCreate: "+ passold.toString());
        if (!role.equals("admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.mDoanhThu).setVisible(false);
            menu.findItem(R.id.mTop).setVisible(false);
            menu.findItem(R.id.mQLKhachHang).setVisible(false);
            menu.findItem(R.id.mQLHoaDon).setVisible(false);
            menu.findItem(R.id.mQLSanPham).setVisible(false);
            menu.findItem(R.id.mQLLoaiSP).setVisible(false);
        }
    }
    private void rePlaceFragment(Fragment fragment){
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    private void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau,null);
        EditText edt_OldPass =  view.findViewById(R.id.edt_OldPass);
        EditText edt_NewPass =  view.findViewById(R.id.edt_NewPass);
        EditText edt_ReNewPass =  view.findViewById(R.id.edt_ReNewPass);

        builder.setView(view);

        builder.setPositiveButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String oldPass = edt_OldPass.getText().toString();
                String newPass = edt_NewPass.getText().toString();
                String reNewPass = edt_ReNewPass.getText().toString();

                if (oldPass.isEmpty() || newPass.isEmpty() || reNewPass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (newPass.equals(reNewPass)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPass(newPass);
                    UserDao dao = new UserDao(MainActivity.this);

                    if (dao.update(user) > 0) {
                        Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Nhập mật khẩu không trùng với nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void  ShowDiLogOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đăng  xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog  = builder.create();
        alertDialog.show();
    }
}
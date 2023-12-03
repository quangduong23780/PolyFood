package com.example.duan_1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_1.R;
import com.example.duan_1.dao.UserDao;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    EditText edtusername,edtpass;
    TextView txtforgot,txtgingup;
    CheckBox checkboxSavePassword;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_login);
        btnlogin=findViewById(R.id.btnLogin);
        edtusername=findViewById(R.id.edtUsername);
        edtpass=findViewById(R.id.edtPassword);
        txtforgot=findViewById(R.id.txtForgot);
        txtgingup=findViewById(R.id.txtSignUp);
        checkboxSavePassword=findViewById(R.id.checkboxSavePassword);
        UserDao userDao = new UserDao(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtusername.getText().toString();
                String pass = edtpass.getText().toString();

                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    if (userDao.checkLogin(username, pass)) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại, vui lòng kiểm tra lại tên người dùng và mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        txtforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotActivity.class));
            }
        });
        txtgingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Kiểm tra nếu đã lưu mật khẩu trước đó, thì hiển thị và đặt trạng thái cho checkbox
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");
        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            edtusername.setText(savedUsername);
            edtpass.setText(savedPassword);
            checkboxSavePassword.setChecked(true);
        }

        // Xử lý sự kiện khi nhấn nút đăng nhập
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtusername.getText().toString();
                String pass = edtpass.getText().toString();
                if (checkboxSavePassword.isChecked()) {
                    // Nếu checkbox được chọn, lưu mật khẩu vào SharedPreferences
                    editor.putString("username", username);
                    editor.putString("password", pass);
                    editor.apply();
                } else {
                    // Nếu checkbox không được chọn, xóa mật khẩu khỏi SharedPreferences
                    editor.remove("username");
                    editor.remove("password");
                    editor.apply();
                }

                // Tiếp tục xử lý đăng nhập
                if (userDao.checkLogin(username, pass)) {
                    Toast.makeText(LoginActivity.this, "Login thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Login thất bại, vui lòng điền lại pass hoặc số username", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
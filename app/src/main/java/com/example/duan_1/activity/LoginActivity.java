package com.example.duan_1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_1.R;
import com.example.duan_1.dao.UserDao;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    EditText edtusername,edtpass;
    TextView txtforgot,txtgingup;

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
        UserDao userDao = new UserDao(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtusername.getText().toString();
                String pass = edtpass.getText().toString();
                if (userDao.checkLogin(username, pass)) {
                    Toast.makeText(LoginActivity.this, "Login thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Login thất bại,vui lòng điền lại pass hoặc số username", Toast.LENGTH_SHORT).show();
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
    }
}
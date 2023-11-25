package com.example.duan_1.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_1.R;
import com.example.duan_1.database.DbHelper;

public class RegisterActivity extends AppCompatActivity {
    DbHelper dbHelper;
    EditText edt_username,edt_name,edt_pass,edt_address,edt_phone;
    Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_username=findViewById(R.id.edt_username);
        edt_pass=findViewById(R.id.edt_pass);
        edt_name=findViewById(R.id.edt_name);
        edt_phone=findViewById(R.id.edt_phone);
        edt_address=findViewById(R.id.edt_address);
        btn_register=findViewById(R.id.btn_register);
        dbHelper = new DbHelper(this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username =  edt_username.getText().toString();
                String pass = edt_pass.getText().toString();
               String name = edt_name.getText().toString();
               String phone = edt_phone.getText().toString();
               String address = edt_address.getText().toString();
               boolean check =  Register(username,pass,name,phone,address);
                    if (check){
                        Toast.makeText(RegisterActivity.this, "Register thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    }else {
                        Toast.makeText(RegisterActivity.this, "Register không thành công", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
    public boolean Register(String username,String pass,String hoten,String phone,String address){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("username",username);
        contentValues.put("pass",pass);
        contentValues.put("hoten",hoten);
        contentValues.put("phone",phone);
        contentValues.put("address",address);
        long check = sqLiteDatabase.insert("USER",null,contentValues);
        if(check == -1)
            return false;
        return true;
    }

}
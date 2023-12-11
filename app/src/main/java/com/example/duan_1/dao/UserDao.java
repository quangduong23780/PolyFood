package com.example.duan_1.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.Product;
import com.example.duan_1.modul.User;

import java.util.ArrayList;


public class UserDao {
    DbHelper dbHelper;
    SharedPreferences sharedPreferences;

    public UserDao(Context context) {
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
    }

    public boolean checkLogin(String username, String pass) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE username=? AND pass =?", new String[]{username, pass});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", cursor.getString(0));
            editor.putString("role", cursor.getString(6));
            editor.commit();

            return true;
        } else
            return false;
    }


        public long update(User user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("pass",user.getPass());
            return db.update("USER",values,"username=?",new String[]{user.getUsername()});
        }
        public ArrayList<User> getALL(){
        //lấy dữ liệu từ sql user vào đây
            ArrayList<User> list = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER",null);
            if (cursor.getCount() !=0){
                cursor.moveToFirst();
                do {
                    list.add(new User(cursor.getInt(0),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
                }while (cursor.moveToNext());
            }
            return list;
        }
}

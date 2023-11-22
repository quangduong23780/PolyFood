package com.example.duan_1.dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.database.DbHelper;


public class UserDao {
    DbHelper dbHelper;
    SharedPreferences sharedPreferences;

    public UserDao(Context context) {
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN",MODE_PRIVATE);
    }
public boolean checkLogin(String username,String pass){
    SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE username=? AND pass =?",new String[]{username,pass});
       if (cursor.getCount() !=0){
           cursor.moveToFirst();

           SharedPreferences.Editor editor  = sharedPreferences.edit();
           editor.putString("username",cursor.getString(0));
           editor.putString("role",cursor.getString(6));
           editor.commit();

           return true;
       }else
           return false;
   }
}

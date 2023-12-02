package com.example.duan_1.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.GioHang;
import com.example.duan_1.modul.HoaDon;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class HoaDonDao {
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
  public HoaDonDao(Context context){dbHelper = new DbHelper(context);}


    public ArrayList<HoaDon> getDSHoaDon(){
        ArrayList<HoaDon> list =new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT * FROM CHITIETDONHANG",null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}

package com.example.duan_1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.LoaiProduct;

import java.util.ArrayList;

public class LoaiSanPhamDao {
    DbHelper dbHelper;
    public LoaiSanPhamDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LoaiProduct> getDSLoaiSP(){
        ArrayList<LoaiProduct> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase  = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISP",null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiProduct(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
      return list;
    }
}

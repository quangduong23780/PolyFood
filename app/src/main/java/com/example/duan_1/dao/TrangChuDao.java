package com.example.duan_1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class TrangChuDao {
    DbHelper dbHelper;
    public TrangChuDao(Context context){dbHelper = new DbHelper(context);}
    public ArrayList<Product> getDSProductTrangChu(){
        ArrayList<Product> list =new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT * FROM PRODUCT",null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new Product(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}

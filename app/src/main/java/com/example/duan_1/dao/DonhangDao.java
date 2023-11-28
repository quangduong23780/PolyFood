package com.example.duan_1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.Donhang;

import java.util.ArrayList;

public class DonhangDao {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public DonhangDao(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<Donhang> getAll(){
        String sql="select * from DONHANG";
        return getData(sql);
    }
    public long insert(Donhang donhang){
        ContentValues values = new ContentValues();
        values.put("id", donhang.getId());
        values.put("tenkhachhang", donhang.getTenkhachhang());
        values.put("sodienthoai", donhang.getSodienthoai());
        values.put("email", donhang.getEmail());
        values.put("diachi", donhang.getDiachi());
        return db.insert("DONHANG", null, values);
    }
    @SuppressLint("Range")
    private ArrayList<Donhang> getData(String sql,String...selectionArg){
        ArrayList<Donhang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArg);
        while (cursor.moveToNext()){
            Donhang donhang = new Donhang();
            donhang.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            donhang.setTenkhachhang(cursor.getString(cursor.getColumnIndex("tenkhachhang")));
            donhang.setSodienthoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("sodienthoai"))));
            donhang.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            donhang.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
            list.add(donhang);
        }
        return list;
    }
}

package com.example.duan_1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.Donhang;
import com.example.duan_1.modul.GioHang;

import java.util.ArrayList;

public class ChitietDonhangDao {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public ChitietDonhangDao(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<GioHang> getAll(){
        String sql = "select * from CHITIETDONHANG";
        return getData(sql);
    }
    public void insert(int id, GioHang gioHang){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("masp", gioHang.getId());
        values.put("giasp", gioHang.getPrirceproduct());
        values.put("soluong", gioHang.getSoluong());
        db.insert("CHITIETDONHANG", null, values);
    }
    @SuppressLint("Range")
    private ArrayList<GioHang> getData(String sql, String...selectionArg){
        ArrayList<GioHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArg);
        while (cursor.moveToNext()){
            GioHang gioHang = new GioHang();
            gioHang.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            gioHang.setPrirceproduct(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giasp"))));
            gioHang.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
            list.add(gioHang);
        }
        return list;
    }
}

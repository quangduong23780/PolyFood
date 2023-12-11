package com.example.duan_1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.Donhang;
import com.example.duan_1.modul.GioHang;
import com.example.duan_1.modul.HoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ChitietDonhangDao {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public ChitietDonhangDao(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<HoaDon> getAll(){
        String sql = "select * from CHITIETDONHANG";
        return getData(sql);
    }
    public void insert(int id, GioHang gioHang,String ngay, int trangthai){
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("masp", gioHang.getId());
        values.put("giasp", gioHang.getPrirceproduct());
        values.put("soluong", gioHang.getSoluong());
        values.put("trangthai", trangthai);
        values.put("ngay", ngay);
        db.insert("CHITIETDONHANG", null, values);
    }
    @SuppressLint("Range")
    private ArrayList<HoaDon> getData(String sql, String...selectionArg){
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArg);
        while (cursor.moveToNext()){
            HoaDon hoaDon  = new HoaDon();
            hoaDon.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            hoaDon.setMasp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("masp"))));
            hoaDon.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giasp"))));
            hoaDon.setSoluong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
            hoaDon.setTrangthai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("trangthai"))));
            hoaDon.setNgay(cursor.getString(cursor.getColumnIndex("ngay")));
            list.add(hoaDon);
        }
        return list;
    }
}

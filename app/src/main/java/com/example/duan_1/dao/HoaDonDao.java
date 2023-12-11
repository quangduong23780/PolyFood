package com.example.duan_1.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


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
         sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT * FROM CHITIETDONHANG",null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3), cursor.getInt(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public void updateHoaDon(int id, int trangThai) {
         sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangthai", trangThai);
        sqLiteDatabase.update("CHITIETDONHANG", contentValues,  "id = ?", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }
    public int DeleteHoaDon(int id) {
       sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CHITIETDONHANG WHERE id =?", new String[]{String.valueOf(id)});
        if (cursor.getCount() == 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("CHITIETDONHANG", "id =?", new String[]{String.valueOf(id)});
        if (check == -1) {
            Log.e("HoaDonDao", "Lỗi xóa hoá đơn từ CSDL");
            return 0;
        }
        return 1;
    }

}

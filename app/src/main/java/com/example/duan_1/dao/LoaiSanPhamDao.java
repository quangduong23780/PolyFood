package com.example.duan_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public boolean AddLoaiSP(String  tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai",tenloai);

        long check = sqLiteDatabase.insert("LOAISP",null,contentValues);
        if(check == -1){
            return false;
        }else {
            return true;
        }
    }
    public int DeleteLoaiSP(int maloai) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISP WHERE maloai =?", new String[]{String.valueOf(maloai)});
        if (cursor.getCount() == 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAISP", "maloai =?", new String[]{String.valueOf(maloai)});
        if (check == -1) {
            Log.e("LoaiSanPhamDao", "Lỗi xóa loại sản phẩm từ CSDL");
            return 0;
        }
        return 1;
    }
    public boolean UpdateLoaiSP( int maloai ,String tenloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai",tenloai);
        long check = sqLiteDatabase.update("LOAISP",contentValues,"maloai =?",new String[]{String.valueOf(maloai)});
        if (check == -1)
            return false;
        return true;
    }
}

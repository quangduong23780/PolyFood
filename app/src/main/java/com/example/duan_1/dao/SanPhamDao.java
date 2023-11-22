package com.example.duan_1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.Product;

import java.util.ArrayList;

public class SanPhamDao {
    DbHelper dbHelper;
    public SanPhamDao(Context context){
         dbHelper = new DbHelper(context);
    }
  public ArrayList<Product> getDSProduct(){
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
    public  boolean themSanPham(String tenproduct,int loai,int price,String image){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenproduct",tenproduct);
        contentValues.put("maloai",loai);
        contentValues.put("price",price);
        contentValues.put("image",image);
        long check = sqLiteDatabase.insert("PRODUCT",null,contentValues);
        if(check == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean UpdateThongTinProduct(int masp, String tenproduct,int price,String image){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenproduct",tenproduct);
        contentValues.put("price",price);
        contentValues.put("image",image);
        long check = sqLiteDatabase.update("PRODUCT",contentValues,"masp =?",new String[]{String.valueOf(masp)});
        if (check == -1)
            return false;
        return true;
    }
    public int xoaProduct(int masp) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PRODUCT WHERE masp =?", new String[]{String.valueOf(masp)});
        if (cursor.getCount() == 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("PRODUCT", "masp =?", new String[]{String.valueOf(masp)});
        if (check == -1) {
            Log.e("SanPhamDao", "Lỗi xóa sản phẩm từ CSDL");
            return 0;
        }
        return 1;
    }
}

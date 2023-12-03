package com.example.duan_1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_1.database.DbHelper;
import com.example.duan_1.modul.Product;

import java.lang.ref.PhantomReference;
import java.net.PortUnreachableException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TopDAO {
    DbHelper dbHelper ;
    public TopDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Product> getTop10(){
        ArrayList<Product> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT dh.masp, pr.tenproduct, COUNT(dh.masp) " +
                "FROM CHITIETDONHANG dh, PRODUCT pr " +
                "WHERE dh.masp = pr.masp " +
                "GROUP BY dh.masp, pr.tenproduct " +
                "ORDER BY COUNT(dh.masp) DESC " +
                "LIMIT 10", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                 list.add(new Product(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public int getDoanhThu(String ngaybatdau, String ngayketthuc) {
        ngaybatdau = convertDateFormat(ngaybatdau);
        ngayketthuc = convertDateFormat(ngayketthuc);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(giasp) FROM CHITIETDONHANG WHERE substr(ngay, 7) || substr(ngay, 4, 2) || substr(ngay, 1, 2) BETWEEN ? AND ?", new String[]{ngaybatdau, ngayketthuc});
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    private String convertDateFormat(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parsedDate = inputFormat.parse(date);
            return outputFormat.format(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}

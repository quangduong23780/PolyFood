package com.example.duan_1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,"QUANLYCUAHANG",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String q_user="CREATE TABLE USER(mauser INTEGER PRIMARY KEY ,username TEXT,pass TEXT,hoten TEXT ,phone TEXT, address TEXT,role TEXT )";
       sqLiteDatabase.execSQL(q_user);

       String q_loai = "CREATE TABLE LOAISP(maloai INTEGER PRIMARY KEY  AUTOINCREMENT, tenloai TEXT)";
       sqLiteDatabase.execSQL(q_loai);

       String q_product =  "CREATE TABLE PRODUCT(masp INTEGER PRIMARY KEY AUTOINCREMENT, tenproduct TEXT,maloai INTEGER REFERENCES LOAISP(maloai),price INTEGER,soluong INTEGER,image TEXT)";
       sqLiteDatabase.execSQL(q_product);

       String d_user="INSERT INTO USER VALUES(1,'admin','123','Tong Quang Duong','0981697058','Ha Noi','admin'),(2,'quangdai','123','Tong Quang Dai','0358625456','HCM','user')";
       sqLiteDatabase.execSQL(d_user);

       String d_loai="INSERT INTO LOAISP VALUES(1,'Đồ ăn'),(2,'Đồ uống')";
       sqLiteDatabase.execSQL(d_loai);

       String d_product = "INSERT INTO PRODUCT VALUES\n" +
               "(1, 'Gà quay', 1, 1, 2000, ''),\n" +
               "(2, 'Vịt quay', 1, 1, 1800, NULL),\n" +
               "(3, 'Cam ép', 2, 500, 1600, NULL),\n" +
               "(4, 'Trà đào', 2, 1, 600, '')";
       sqLiteDatabase.execSQL(d_product);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          if (i != i1){
              sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USER");
              onCreate(sqLiteDatabase);
          }
    }
}

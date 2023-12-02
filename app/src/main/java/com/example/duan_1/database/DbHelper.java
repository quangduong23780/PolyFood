package com.example.duan_1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,"QUANLYCUAHANG",null,5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String chitietdonhang = "CREATE TABLE CHITIETDONHANG(id integer, masp integer , giasp integer, soluong integer)";
        sqLiteDatabase.execSQL(chitietdonhang);
        String donhang = "CREATE TABLE DONHANG(id integer primary key, tenkhachhang text,sodienthoai integer, email text,diachi text)";
        sqLiteDatabase.execSQL(donhang);
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
                "(1, 'Gà quay', 1, 1200, 20, ''),\n" +
                "(2, 'Vịt quay', 1, 1400, 10, NULL),\n" +
                "(3, 'Cam ép', 2, 500, 30, NULL),\n" +
                "(4, 'Trà đào', 2, 600, 40, '')";
        sqLiteDatabase.execSQL(d_product);
        String insert_donhang = "insert into DONHANG values(1,'huy', 12312, 'huycodon@gmail.com', 'ha noi')";
        sqLiteDatabase.execSQL(insert_donhang);
        String d_chitietdonhang ="INSERT INTO CHITIETDONHANG VALUES(1,1,2400,2)";
        sqLiteDatabase.execSQL(d_chitietdonhang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USER");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DONHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISP");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCT");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CHITIETDONHANG");
            onCreate(sqLiteDatabase);
        }
    }
}

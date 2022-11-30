package com.ql.appquanlycuahang1.datbase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreatDBSanPham  extends SQLiteOpenHelper {
    public  static final  String DB_NAME =" CR_SANPHAM";
    public  static final  int DB_VERSION = 1;



    public CreatDBSanPham( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //taoj bang sp
        String querySanPham ="CREATE TABLE SanPham("+
                "maSP INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "nhacungcap TEXT," +
                "thongtin TEXT," +
                "soLuong INTEGER" +
                ")";
        db.execSQL(querySanPham);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

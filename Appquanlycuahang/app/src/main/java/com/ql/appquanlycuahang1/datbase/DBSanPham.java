package com.ql.appquanlycuahang1.datbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.ql.appquanlycuahang1.model.SanPham;

import java.util.ArrayList;

public class DBSanPham {
     SQLiteDatabase sqLiteDatabase;
     CreatDBSanPham  creatDBSanPham;
    private static final String DB_NAME_TABLE = "SanPham";
    private static final String DB_SANPHAMNUMBER_MASP = "maSP";
    private static final String DB_SANPHAMNUMBER_NAME = "ten";
    private static final String DB_SANPHAMNUMBER_NHACC = "nhacungcap";
    private static final String DB_SANPHAMNUMBER_THONGTIN = "thongtin";
    private static final String DB_SANPHAMNUMBER_SOLUONG = "soLuong";

     public DBSanPham(Context context){//ham khỏi tạo
         creatDBSanPham = new CreatDBSanPham(context);
     }

     public void open(){
         sqLiteDatabase =creatDBSanPham.getWritableDatabase();//cho phep mở dọc và sửa

     }

    //Truy vấn không trả kết quả: create, inssert, update, delete..
    public void QueryData(String sql){
        SQLiteDatabase database = creatDBSanPham.getWritableDatabase();
        database.execSQL(sql);
    }

    //Truy vấn trả kết quả select
    public Cursor GetData(String sql){
        SQLiteDatabase database = creatDBSanPham.getWritableDatabase();
        return database.rawQuery(sql, null);
    }

     public void close(){ creatDBSanPham.close(); }

     public long AddNew(SanPham sanPham){
         //tạo đtg Contentvalues để làm phương tiện gửi dl cho databasee
         //giống bundel cho intent
         ContentValues contentValues = new ContentValues();
         contentValues.put(DB_SANPHAMNUMBER_NAME, sanPham.getTen());
         contentValues.put(DB_SANPHAMNUMBER_NHACC, sanPham.getNhacungcap());
         contentValues.put(DB_SANPHAMNUMBER_THONGTIN, sanPham.getThongtin());
         contentValues.put(DB_SANPHAMNUMBER_SOLUONG, sanPham.getSoLuong());

         // gọi hàm insert để thêm dl vào bảng
         long res = sqLiteDatabase.insert(DB_NAME_TABLE,null,contentValues);
         return res;
     }
     public ArrayList<SanPham> GetAll(){
         // tạo ds chứa
         ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();

         // khai báo ds cột láy dl
         String [] ds_cot = new String[]{
                 DB_SANPHAMNUMBER_MASP,DB_SANPHAMNUMBER_NAME,DB_SANPHAMNUMBER_NHACC,DB_SANPHAMNUMBER_THONGTIN,DB_SANPHAMNUMBER_SOLUONG
         };
         //tạo dtg con trỏ đọc dl
         Cursor cursor = sqLiteDatabase.query(DB_NAME_TABLE,ds_cot,null,null,null,null,null);
         cursor.moveToFirst();//dưa con trỏ về đầu kq
         while (!cursor.isAfterLast()){
             // lấy thông tin tưn cột cho vào biến
             int maSP = cursor.getInt(0);
             String ten = cursor.getString(1);
             String nhacungcap = cursor.getString(2);
             String thongtin = cursor.getString(3);
             int soluong = cursor.getInt(4);

             // tạo đôi tg thông tin lớp để gắn thông tin vào
             SanPham sanPham = new SanPham();
             sanPham.setMaSP(maSP);
             sanPham.setTen(ten);
             sanPham.setNhacungcap(nhacungcap);
             sanPham.setThongtin(thongtin);
             sanPham.setSoLuong(soluong);

             // thêm vào ds
             dsSanPham.add(sanPham);
             //sau khi thêm vào ds thì chuyển con trỏ xg dong tiếp theo
             cursor.moveToNext();
         }
         return dsSanPham;//trả về ds
     }
//     public int Update(SanPham sanPham){
//         //tạo đtg Contentvalues để làm phương tiện gửi dl cho databasee
//         //giống bundel cho intent
//         ContentValues contentValues = new ContentValues();
//         contentValues.put(DB_SANPHAMNUMBER_NAME, sanPham.getTen());
//         contentValues.put(DB_SANPHAMNUMBER_NHACC, sanPham.getNhacungcap());
//         contentValues.put(DB_SANPHAMNUMBER_THONGTIN, sanPham.getThongtin());
//         contentValues.put(DB_SANPHAMNUMBER_SOLUONG, sanPham.getSoLuong());
//         // gọi hàm upadte để sửa dl
//         int res  = sqLiteDatabase.update(DB_NAME_TABLE,contentValues,"maSP = " +sanPham.getMaSP(),null);
//         return  res;
//
//     }
}

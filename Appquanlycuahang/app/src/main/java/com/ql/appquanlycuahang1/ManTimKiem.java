package com.ql.appquanlycuahang1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.ql.appquanlycuahang1.adapter.SanPhamTimKiemAdapter;
import com.ql.appquanlycuahang1.datbase.DBSanPham;
import com.ql.appquanlycuahang1.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ManTimKiem extends AppCompatActivity {

    List<SanPham> sanPhamList;
    DBSanPham dbSanPham;
    SanPhamTimKiemAdapter sanPhamTimKiemAdapter;
    ListView lvTimKiem;

    EditText edtTimKiem;
    ImageView imgTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_tim_kiem);

        //Ánh xạ
        lvTimKiem = findViewById(R.id.lvTimKiem);
        edtTimKiem = findViewById(R.id.edt_timKiem);
        imgTimKiem = findViewById(R.id.img_timKiem2);

        dbSanPham = new DBSanPham(getApplicationContext());

        sanPhamList = new ArrayList<>();

        //Hiển thị dữ liệu
        Cursor cursor = dbSanPham.GetData("SELECT * FROM SanPham");
        while(cursor.moveToNext())
        {
            int maSp = cursor.getInt(0);
            String tensp = cursor.getString(1);
            String nhacungcap = cursor.getString(2);
            String thongtin = cursor.getString(3);
            int soluong = cursor.getInt(4);

            sanPhamList.add(new SanPham(tensp, thongtin, nhacungcap, maSp , soluong));
        }
        sanPhamTimKiemAdapter = new SanPhamTimKiemAdapter(this, sanPhamList, ManTimKiem.this);
        lvTimKiem.setAdapter(sanPhamTimKiemAdapter);


        //Cho phép mở, đọc, và sửa
        dbSanPham.open();

        imgTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tencantim = edtTimKiem.getText().toString().trim();
                if(TextUtils.isEmpty(tencantim)){
                    Toast.makeText(ManTimKiem.this, "Bạn chưa nhập dữ liệu", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor cursor = dbSanPham.GetData("SELECT * FROM SanPham WHERE ten LIKE '%"+tencantim+"%'");
                sanPhamList.clear();
                while(cursor.moveToNext())
                {
                    int maSp = cursor.getInt(0);
                    String tensp = cursor.getString(1);
                    String nhacungcap = cursor.getString(2);
                    String thongtin = cursor.getString(3);
                    int soluong = cursor.getInt(4);

                    sanPhamList.add(new SanPham(tensp, thongtin, nhacungcap, maSp , soluong));
                }
                sanPhamTimKiemAdapter.notifyDataSetChanged();
                sanPhamTimKiemAdapter = new SanPhamTimKiemAdapter(ManTimKiem.this, sanPhamList, ManTimKiem.this);
                lvTimKiem.setAdapter(sanPhamTimKiemAdapter);
            }
        });
    }
}
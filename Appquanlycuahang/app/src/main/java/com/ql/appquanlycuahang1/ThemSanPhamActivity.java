package com.ql.appquanlycuahang1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ql.appquanlycuahang1.adapter.SanPhamAdapter;
import com.ql.appquanlycuahang1.datbase.DBSanPham;
import com.ql.appquanlycuahang1.fragment.TrangChuFragment;
import com.ql.appquanlycuahang1.model.SanPham;

import java.util.ArrayList;

public class ThemSanPhamActivity extends AppCompatActivity {
    EditText edTen,edNhaCC,edThongTin,edSoLuong;
    Button btnThem;
    DBSanPham dbSanPham;
    ArrayList<SanPham> sanPhamArrayList;
    SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        anhXa();
        sanPhamArrayList = new ArrayList<>();
        dbSanPham = new DBSanPham(getApplicationContext());
        dbSanPham.open();

        // lấy toàn bộ dl từ csdl ra

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sanPham = new SanPham();
                sanPham.setTen(edTen.getText().toString());
                sanPham.setNhacungcap(edNhaCC.getText().toString());
                sanPham.setThongtin(edThongTin.getText().toString());
                sanPham.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));

                long ketqua = dbSanPham.AddNew(sanPham); //gọi đến insert
                if (ketqua > 0){
                    sanPhamArrayList.clear();
                    sanPhamArrayList.addAll(dbSanPham.GetAll());
                    Toast.makeText(getApplicationContext(), "Thêm Thanh Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void anhXa(){
        edTen = findViewById(R.id.edTen);
        edNhaCC = findViewById(R.id.edNhaCC);
        edThongTin = findViewById(R.id.edThongTin);
        edSoLuong = findViewById(R.id.edSoLuong);
        btnThem = findViewById(R.id.btnThemSP);
    }
}
package com.ql.appquanlycuahang1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ManNoiDung extends AppCompatActivity {
    TextView txtTenSp, txtNoidung,txtThongTin,txtSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_noi_dung);

        txtTenSp = findViewById(R.id.Ten_sp);
        txtNoidung= findViewById(R.id.tv_nhaCungCapChiTiet);
        txtThongTin = findViewById(R.id.tv_ThongTinChiTiet);
        txtSoLuong= findViewById(R.id.tv_SoLuongChiTiet);

        //Nhận dữ liệu từ ShopFrangment
        Intent intent = getIntent();
        String ten = intent.getStringExtra("ten");
        String nhacungcap = intent.getStringExtra("nhacungcap");
        String thongtin = intent.getStringExtra("thongtin");
        int soluong = intent.getIntExtra("soluong",0);

        txtTenSp.setText("Tên : "+ten);
        txtNoidung.setText("Nhà Cung cấp : "+nhacungcap);
        txtThongTin.setText("Thông tin : "+thongtin);
        txtSoLuong.setText("Số Lượng : "+soluong);
    }
}
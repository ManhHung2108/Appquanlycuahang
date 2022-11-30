package com.ql.appquanlycuahang1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ql.appquanlycuahang1.datbase.TaiKhoanHelper;

public class DangKy extends AppCompatActivity {

    EditText edtDkTaiKhoan, edtDkMatKhau, edtDkEmail;
    Button btnDkDangKy, btnDkDangNhap;
    TaiKhoanHelper taiKhoanHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        Anhxa();

        // Đăng ký sự kiện để trở về màn hình đăng nhập.
        btnDkDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
            }
        });

        taiKhoanHelper = new TaiKhoanHelper(DangKy.this, "TaiKhoan.sqlite", null, 1);

        //Đăng ký sự kiện cho nút đăng ký
        btnDkDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtDkTaiKhoan.getText().toString();
                String matkhau = edtDkMatKhau.getText().toString();
                String email = edtDkEmail.getText().toString();

                /*TaiKhoan tk1 = CreateTaiKhoan();*/
                if(TextUtils.isEmpty(taikhoan) && TextUtils.isEmpty(matkhau) && TextUtils.isEmpty(email))
                {
                    Toast.makeText(DangKy.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    taiKhoanHelper.QueryData("INSERT INTO TaiKhoan VALUES (null, '"+ taikhoan +"', '"+ matkhau +"', '"+ email +"')");
                }
            }
        });
    }

    //Phương thức tạo tài khoản
//    private TaiKhoan CreateTaiKhoan(){
//        String taikhoan = edtDkTaiKhoan.getText().toString();
//        String matkhau = edtDkMatKhau.getText().toString();
//        String email = edtDkEmail.getText().toString();
//
//        TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, email);
//
//        return tk;
//    }

    private void Anhxa(){
        edtDkTaiKhoan = findViewById(R.id.dktaikhoan);
        edtDkMatKhau = findViewById(R.id.dkmatkhau);
        edtDkEmail = findViewById(R.id.dkemail);
        btnDkDangKy = findViewById(R.id.dkdangky);
        btnDkDangNhap = findViewById(R.id.dkdangnhap);
    }
}
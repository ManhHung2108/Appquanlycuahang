package com.ql.appquanlycuahang1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ql.appquanlycuahang1.datbase.TaiKhoanHelper;


public class DangNhap extends AppCompatActivity {

    EditText edtMatKhau, edtTaiKhoan;
    Button btnDangNhap, btnDangKy;
    TaiKhoanHelper taiKhoanHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        Anhxa();

        //Tạo database
        taiKhoanHelper = new TaiKhoanHelper(DangNhap.this, "TaiKhoan.sqlite", null, 1);

        //Tạo bảng
          taiKhoanHelper.QueryData("CREATE TABLE IF NOT EXISTS TaiKhoan (Id INTEGER PRIMARY KEY AUTOINCREMENT, TenTaiKhoan text, MatKhau text, Email text)");

        //Thêm dữ liệu
//        taiKhoanHelper.QueryData("INSERT INTO TaiKhoan VALUES (null, 'manhhung21', 'hung2108', 'manhhung21@gmail.com')");


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                //Sử dụng con trỏ để lấy dữ liệu, gọi đến GetData() để lấy tất cả tài khoản
                Cursor cursor = taiKhoanHelper.GetData("SELECT * FROM TaiKhoan");

                boolean kiemTra = false;

                //Vòng lặp để lấy dữ liệu từ cursor với moveToNext() di chuyển tiếp
                while(cursor.moveToNext()){
                    String datataikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);
                    if(edtTaiKhoan.getText().length() !=0 && edtMatKhau.getText().length() !=0)
                    {
                        if(datataikhoan.equals(tentaikhoan)&& datamatkhau.equals(matkhau)){
                            Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            int id = cursor.getInt(0);
                            String email = cursor.getString(3);
                            String tentk = cursor.getString(1);

                            Intent intent = new Intent(DangNhap.this, MainActivity.class);
                            //Gửi dữ liệu qua ActivityMain
                            intent.putExtra("id", id);
                            intent.putExtra("tentaikhoan", tentk);
                            intent.putExtra("email", email);
                            kiemTra = true;
                            startActivity(intent);
                        }

                    }
                    else{
                        Toast.makeText(DangNhap.this, "Mời bạn nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    }
                }
                if(kiemTra == false)
                    Toast.makeText(DangNhap.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();

                //Trả cursor về đầu
                cursor.moveToFirst();
                //Đóng khi không dùng
                cursor.close();
            }
        });


        //Tạo sự kiện click chuyển sang màn hình đăng ký với Intent.
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
    }


    private void Anhxa()
    {
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangNhap = findViewById(R.id.btndangnhap);
        btnDangKy = findViewById(R.id.btndangky);
    }
}
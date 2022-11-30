package com.ql.appquanlycuahang1.model;

public class TaiKhoan {

    //Biến
    private int mId;
    private String mTenTaiKhoan;
    private String mMatKhau;
    private String mEmail;


    //Hàm khởi tạo
    public TaiKhoan(String taikhoan, String matkhau, String email) {
    }

    public TaiKhoan(int mId, String mTenTaiKhoan, String mMatKhau, String mEmail) {
        this.mId = mId;
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mMatKhau = mMatKhau;
        this.mEmail = mEmail;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTenTaiKhoan() {
        return mTenTaiKhoan;
    }

    public void setmTenTaiKhoan(String mTenTaiKhoan) {
        this.mTenTaiKhoan = mTenTaiKhoan;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}


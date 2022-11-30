package com.ql.appquanlycuahang1.model;

public class SanPham {
    public String ten,nhacungcap,thongtin;
    public int maSP,img,soLuong;

    public SanPham() {
    }

    public SanPham(String ten, String nhacungcap, String thongtin, int maSP, int soLuong) {
        this.ten = ten;
        this.nhacungcap = nhacungcap;
        this.thongtin = thongtin;
        this.maSP = maSP;
        this.soLuong = soLuong;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNhacungcap() {
        return nhacungcap;
    }

    public void setNhacungcap(String nhacungcap) {
        this.nhacungcap = nhacungcap;
    }

    public String getThongtin() {
        return thongtin;
    }

    public void setThongtin(String thongtin) {
        this.thongtin = thongtin;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}

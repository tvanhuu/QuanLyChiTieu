package com.tvanhuu.poly.quanlychitieu.model;

import java.util.Date;

/**
 * Created by thuu on 29/01/18.
 **/

public class Loai {
    private String ten, ghiChu, loai;
    private double soTien;
    private Date ngayThang;

    public Loai(){

    }

    public Loai(String ten, String ghiChu, String loai, double soTien, Date ngayThang) {
        this.ten = ten;
        this.ghiChu = ghiChu;
        this.loai = loai;
        this.soTien = soTien;
        this.ngayThang = ngayThang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public Date getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(Date ngayThang) {
        this.ngayThang = ngayThang;
    }
}

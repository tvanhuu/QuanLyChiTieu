package com.tvanhuu.poly.quanlychitieu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by thuu on 29/01/18.
 **/

public class ObjectLoai implements Parcelable {
    private int id;
    private String ten, ghiChu, loai;
    private double soTien;
    private Date ngayThang;

    public ObjectLoai(){

    }

    public ObjectLoai(String ten, String ghiChu, String loai, double soTien, Date ngayThang) {
        this.ten = ten;
        this.ghiChu = ghiChu;
        this.loai = loai;
        this.soTien = soTien;
        this.ngayThang = ngayThang;
    }

    public ObjectLoai(int id, String ten, String ghiChu, String loai, double soTien, Date ngayThang) {
        this.id = id;
        this.ten = ten;
        this.ghiChu = ghiChu;
        this.loai = loai;
        this.soTien = soTien;
        this.ngayThang = ngayThang;
    }

    protected ObjectLoai(Parcel in) {
        id = in.readInt();
        ten = in.readString();
        ghiChu = in.readString();
        loai = in.readString();
        soTien = in.readDouble();
    }

    public static final Creator<ObjectLoai> CREATOR = new Creator<ObjectLoai>() {
        @Override
        public ObjectLoai createFromParcel(Parcel in) {
            return new ObjectLoai(in);
        }

        @Override
        public ObjectLoai[] newArray(int size) {
            return new ObjectLoai[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(ten);
        parcel.writeString(ghiChu);
        parcel.writeString(loai);
        parcel.writeDouble(soTien);
    }
}

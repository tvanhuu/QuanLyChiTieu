package com.tvanhuu.poly.quanlychitieu.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tvanhuu.poly.quanlychitieu.model.ThuChi;

/**
 * Created by thuu on 28/02/18.
 **/

public class SQLManager extends SQLiteOpenHelper {

//    private String ten, ghiChu, loai;
//    private double soTien;
//    private Date ngayThang;
    // infor
    private static String NAME_SQL = "QUANLYCHITIEU";
    private static int VERSION_SQL = 1;

    // table
    private static String LOAI_TABLE = "LOAICHITIEU";

    // LOAI CHI TIEU
    private static String  ID = "ID";
    private static String  NAME = "TENCHITIEU";
    private static String  NOTE = "GHICHUCHITIEU";
    private static String  TYPE = "LOAICHITIEU";
    private static String  MONEY = "SOTIENCHITIEU";
    private static String  DATE = "NGAYTHANG";

    public SQLManager(Context context) {
        super(context, NAME_SQL, null, VERSION_SQL);
    }

    @Override
    public void onCreate(SQLiteDatabase SQL) {
        String sqlQuery = "CREATE TABLE " +LOAI_TABLE+ " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME+" NVARCHAR(50), "+
                NOTE+" NVARCHAR(50), "+
                TYPE+ " NVARCHAR(10), "+
                MONEY+ " NVARCHAR(10), "+
                DATE+ " NVARCHAR(50) )";
        SQL.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            sql.execSQL("DROP TABLE IS EXISTS "+ LOAI_TABLE);
            onCreate(sql);
        }
    }

    public void add(ThuChi loai){
        SQLiteDatabase sql = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, loai.getTen());
        cv.put(NOTE, loai.getGhiChu());
        cv.put(TYPE, loai.getLoai());
        cv.put(MONEY, String.valueOf(loai.getSoTien()));

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String format = simpleDateFormat.format(loai.getNgayThang());
        cv.put(DATE, format);
        sql.insert(LOAI_TABLE ,null, cv);
        sql.close();
    }

    public List<ThuChi> getChi(){
        List<ThuChi> datas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + LOAI_TABLE;
        @SuppressLint("Recycle") Cursor cr = db.rawQuery(select, null);
        while (cr.moveToNext()){
            ThuChi loai = new ThuChi();
            loai.setId(cr.getInt(0));
            loai.setTen(cr.getString(1));
            loai.setGhiChu(cr.getString(2));
            String loaiChi = cr.getString(3);
            loai.setLoai(cr.getString(3));
            loai.setSoTien(Double.parseDouble(cr.getString(4)));

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                final  Date date = simpleDateFormat.parse(cr.getString(5));
                loai.setNgayThang(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(loaiChi.equals("Chi")){
                datas.add(loai);
            }
        }
        cr.close();
        db.close();
        return datas;
    }

    public List<ThuChi> getThu(){
        List<ThuChi> datas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + LOAI_TABLE;
        @SuppressLint("Recycle") Cursor cr = db.rawQuery(select, null);
        while (cr.moveToNext()){
            ThuChi loai = new ThuChi();
            loai.setId(cr.getInt(0));
            loai.setTen(cr.getString(1));
            loai.setGhiChu(cr.getString(2));
            String loaiThu = cr.getString(3);
            loai.setLoai(cr.getString(3));
            loai.setSoTien(Double.parseDouble(cr.getString(4)));

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                final  Date date = simpleDateFormat.parse(cr.getString(5));
                loai.setNgayThang(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(loaiThu.equals("Thu")){
                datas.add(loai);
            }
        }
        cr.close();
        db.close();
        return datas;
    }

    public void update(ThuChi loai){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, loai.getTen());
        cv.put(NOTE, loai.getGhiChu());
        cv.put(TYPE, loai.getLoai());
        cv.put(MONEY, String.valueOf(loai.getSoTien()));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(loai.getNgayThang());
        cv.put(DATE, date);
        db.update(LOAI_TABLE, cv, ID+ "=?", new String[]{String.valueOf(loai.getId())});
        db.close();
    }

    public void delete(ThuChi loai){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(LOAI_TABLE, ID+ "=? ", new String[]{String.valueOf(loai.getId())});
        db.close();
    }

}

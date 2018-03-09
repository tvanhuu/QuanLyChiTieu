package com.tvanhuu.poly.quanlychitieu.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tvanhuu.poly.quanlychitieu.model.ObjectKhoan;
import com.tvanhuu.poly.quanlychitieu.model.ObjectLoai;

/**
 * Created by thuu on 28/02/18.
 **/

public class SQLManager extends SQLiteOpenHelper {

    // infor
    private static String NAME_SQL = "QUANLYCHITIEU";
    private static int VERSION_SQL = 1;

    // table
    private static String LOAI_TABLE = "LOAICHITIEU";
    private static String KHOAN_TABLE = "KHOANCHITIEU";

    // LOAI CHI TIEU
    private static String  ID = "ID";
    private static String  NAME = "TENCHITIEU";
    private static String  NOTE = "GHICHUCHITIEU";
    private static String  TYPE = "LOAICHITIEU";
    private static String  MONEY = "SOTIENCHITIEU";
    private static String  DATE = "NGAYTHANG";

    //KHOAN CHI TIEU
    private static String  ID_KHOAN = "ID";
    private static String  NAME_KHOAN = "TENKHOAN";
    private static String  TYPE_KHOAN = "KIEUCHITIEU";

    public SQLManager(Context context) {
        super(context, NAME_SQL, null, VERSION_SQL);
    }

    @Override
    public void onCreate(SQLiteDatabase SQL) {
        String sqlQueryLoai = "CREATE TABLE " +LOAI_TABLE+ " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME+" NVARCHAR(50), "+
                NOTE+" NVARCHAR(50), "+
                TYPE+ " NVARCHAR(10), "+
                MONEY+ " NVARCHAR(10), "+
                DATE+ " NVARCHAR(50) )";
        String sqlQueryKhoan = "CREATE TABLE " +KHOAN_TABLE+ " ("+
                ID_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME_KHOAN+" NVARCHAR(50), "+
                TYPE_KHOAN+" NVARCHAR(50) )";

        SQL.execSQL(sqlQueryLoai);
        SQL.execSQL(sqlQueryKhoan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            sql.execSQL("DROP TABLE IS EXISTS "+ LOAI_TABLE);
            sql.execSQL("DROP TABLE IS EXISTS "+ KHOAN_TABLE);
            onCreate(sql);
        }
    }

    // Loai
    public void add(ObjectLoai loai){
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

    public List<ObjectLoai> getChi(){
        List<ObjectLoai> datas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + LOAI_TABLE;
        @SuppressLint("Recycle") Cursor cr = db.rawQuery(select, null);
        while (cr.moveToNext()){
            ObjectLoai loai = new ObjectLoai();
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

    public List<ObjectLoai> getThu(){
        List<ObjectLoai> datas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + LOAI_TABLE;
        @SuppressLint("Recycle") Cursor cr = db.rawQuery(select, null);
        while (cr.moveToNext()){
            ObjectLoai loai = new ObjectLoai();
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

    public void update(ObjectLoai loai){
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

    public void delete(ObjectLoai loai){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(LOAI_TABLE, ID+ "=? ", new String[]{String.valueOf(loai.getId())});
        db.close();
    }

    // khoan
    public void addKhoan(ObjectKhoan khoan){
        SQLiteDatabase sql = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_KHOAN, khoan.getName());
        cv.put(TYPE_KHOAN, khoan.getType());
        sql.insert(KHOAN_TABLE ,null, cv);
        sql.close();
    }

    public List<ObjectKhoan> getKhoanChi(){
        List<ObjectKhoan> datas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + KHOAN_TABLE;
        @SuppressLint("Recycle") Cursor cr = db.rawQuery(select, null);

        while (cr.moveToNext()){
            ObjectKhoan khoan = new ObjectKhoan();
            khoan.setId(cr.getInt(0));
            khoan.setName(cr.getString(1));
            khoan.setType(cr.getString(2));
            if((cr.getString(2)).equals("Chi")){
                datas.add(khoan);
            }
        }
        cr.close();
        db.close();
        return datas;
    }

    public List<ObjectKhoan> getKhoanThu(){
        List<ObjectKhoan> datas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + KHOAN_TABLE;
        @SuppressLint("Recycle") Cursor cr = db.rawQuery(select, null);

        while (cr.moveToNext()){
            ObjectKhoan khoan = new ObjectKhoan();
            khoan.setId(cr.getInt(0));
            khoan.setName(cr.getString(1));
            khoan.setType(cr.getString(2));
            if((cr.getString(2)).equals("Thu")){
                datas.add(khoan);
            }
        }
        cr.close();
        db.close();
        return datas;
    }

    public void updateKhoan(ObjectKhoan khoan){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_KHOAN, khoan.getName());
        db.update(KHOAN_TABLE, cv, ID+ "=?", new String[]{String.valueOf(khoan.getId())});
        db.close();
    }

    public void deleteKhoan(ObjectKhoan khoan){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(KHOAN_TABLE, ID+ "=? ", new String[]{String.valueOf(khoan.getId())});
        db.close();
    }

}

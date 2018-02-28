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

import com.tvanhuu.poly.quanlychitieu.model.Loai;

/**
 * Created by thuu on 28/02/18.
 **/

public class SQLQuanLy extends SQLiteOpenHelper {

//    private String ten, ghiChu, loai;
//    private double soTien;
//    private Date ngayThang;
    // infor
    private static String NAME_SQL = "QUANLYCHITIEU";
    private static int VERSION_SQL = 1;

    // table
    private static String LOAI_TABLE = "LOAICHITIEU";

    // LOAI CHI TIEU
    private static String  NAME = "TENCHITIEU";
    private static String  NOTE = "GHICHUCHITIEU";
    private static String  TYPE = "LOAICHITIEU";
    private static String  MONEY = "SOTIENCHITIEU";
    private static String  DATE = "NGAYTHANG";

    public SQLQuanLy(Context context) {
        super(context, NAME_SQL, null, VERSION_SQL);
    }

    @Override
    public void onCreate(SQLiteDatabase SQL) {
        String sqlQuery = "CREATE TABLE " +LOAI_TABLE+ " ("+
                NAME+" NVARCHAR(50), "+
                NOTE+" NVARCHAR(50), "+
                TYPE+ " NVARCHAR(10), "+
                MONEY+ " NVARCHAR(10), "+
                DATE+ " NVARCHAR(50), ";
        SQL.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            sql.execSQL("DROP TABLE IS EXISTS "+ LOAI_TABLE);
            onCreate(sql);
        }
    }

    public void add(Loai loai){
        SQLiteDatabase sql = getWritableDatabase();
        ContentValues ct = new ContentValues();
        ct.put(NAME, loai.getTen());
        ct.put(NOTE, loai.getGhiChu());
        ct.put(TYPE, loai.getLoai());
        ct.put(MONEY, String.valueOf(loai.getSoTien()));

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String format = simpleDateFormat.format(loai.getNgayThang());
        ct.put(DATE, format);
        sql.insert(LOAI_TABLE ,null ,  ct);
        sql.close();
    }

    public List<Loai> getLoai(){
        List<Loai> datas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM " + LOAI_TABLE;
        @SuppressLint("Recycle") Cursor cr = db.rawQuery(select, null);
        while (cr.moveToNext()){
            Loai loai = new Loai();
            loai.setTen(cr.getString(0));
            loai.setGhiChu(cr.getString(1));
            loai.setLoai(cr.getString(2));
            loai.setSoTien(Double.parseDouble(cr.getString(3)));

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                final  Date date = simpleDateFormat.parse(cr.getString(4));
                loai.setNgayThang(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            datas.add(loai);
        }
        cr.close();
        db.close();
        return datas;
    }

    public void update(Loai loai){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, loai.getTen());
        cv.put(NOTE, loai.getGhiChu());
        cv.put(TYPE, loai.getLoai());
        cv.put(MONEY, String.valueOf(loai.getSoTien()));

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(loai.getNgayThang());
        cv.put(DATE, date);
        db.update(LOAI_TABLE, cv, NAME = "?", new String[]{loai.getTen()});
        db.close();
    }

    public void delete(){

    }

}

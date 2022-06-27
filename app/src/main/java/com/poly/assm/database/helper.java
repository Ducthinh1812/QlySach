package com.poly.assm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class helper extends SQLiteOpenHelper {
    public helper(@Nullable Context context) {
        super(context,"assm", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //thanh vien
        String sql="CREATE TABLE thanhvien(matv integer primary key autoincrement," +
                "tentv text, namsinh text)";
        db.execSQL(sql);
        sql = "insert into thanhvien values(null,'Nguyễn Đức Thịnh','2002')";
        db.execSQL(sql);
        sql = "insert into thanhvien values(null,'Thịnh nè','2002')";
        db.execSQL(sql);
        sql = "insert into thanhvien values(null,'Tạ Đại','2002')";
        db.execSQL(sql);
        sql = "insert into thanhvien values(null,'Văn Phong','2002')";
        db.execSQL(sql);
        sql = "insert into thanhvien values(null,'Tạ Hòa','2002')";
        db.execSQL(sql);
        //the loai sach
        String sql1="CREATE TABLE tlsach(matheloai integer primary key autoincrement," +
                "tentheloai text)";
        db.execSQL(sql1);
        sql1 = "insert into tlsach values(null,'Ngôn Tình')";
        db.execSQL(sql1);
        sql1 = "insert into tlsach values(null,'Kinh Dị')";
        db.execSQL(sql1);
        sql1 = "insert into tlsach values(null,'Tiểu Thuyết')";
        db.execSQL(sql1);
        sql1 = "insert into tlsach values(null,'Phiêu Lưu')";
        db.execSQL(sql1);
        sql1 = "insert into tlsach values(null,'Khoa Học')";
        db.execSQL(sql1);
        //sach
        String sql2="CREATE TABLE sach(masach integer primary key autoincrement," +
                "tensach text,giathue integer," +
                "matheloai integer references tlsach(matheloai))";
        db.execSQL(sql2);
        sql2 = "insert into sach values(null,'Đồi Thỏ',40000,4)";
        db.execSQL(sql2);
        sql2 = "insert into sach values(null,'Vũ Trụ',30000,5)";
        db.execSQL(sql2);
        sql2 = "insert into sach values(null,'Hoàng Tử Bé',50000,3)";
        db.execSQL(sql2);
        sql2 = "insert into sach values(null,'Cha Và Con',60000,3)";
        db.execSQL(sql2);
        sql2 = "insert into sach values(null,'Tháng Sáu Trời Xanh Lam',90000,1)";
        db.execSQL(sql2);
        //thu thu
        String sql3="CREATE TABLE thuthu(matt text primary key," +
                "hoten text not null,"+
                "matkhau text not null)";
        db.execSQL(sql3);
        sql3 = "insert into thuthu values('ph01','admin','admin')";
        db.execSQL(sql3);
        sql3 = "insert into thuthu values('ph02','thinhne','2002')";
        db.execSQL(sql3);
        sql3 = "insert into thuthu values('ph03','ducthinh','2002')";
        db.execSQL(sql3);
        //phieumuon
        String sql4="CREATE TABLE phieumuon(mapm integer primary key autoincrement," +
                "tienthue integer,ngay text,trasach integer,"+
                "matv integer references thanhvien(matv),"+
                "masach integer references sach(masach))";
        db.execSQL(sql4);
        sql4 = "insert into phieumuon values(null,90000,'2020-05-07',1,1,5)";
        db.execSQL(sql4);
        sql4 = "insert into phieumuon values(null,60000,'2020-08-11',1,2,4)";
        db.execSQL(sql4);
        sql4 = "insert into phieumuon values(null,90000,'2020-09-02',1,5,5)";
        db.execSQL(sql4);
        sql4 = "insert into phieumuon values(null,50000,'2021-01-10',0,3,3)";
        db.execSQL(sql4);
        sql4 = "insert into phieumuon values(null,30000,'2021-01-15',0,4,2)";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists thuthu");
        db.execSQL("drop table if exists thanhvien");
        db.execSQL("drop table if exists tlsach");
        db.execSQL("drop table if exists sach");
        db.execSQL("drop table if exists phieumuon");
        onCreate(db);
    }
}

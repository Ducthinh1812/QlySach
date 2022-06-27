package com.poly.assm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.assm.database.helper;
import com.poly.assm.model.phieumuon;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class phieumuondao {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private SQLiteDatabase db;
    public phieumuondao(Context context){
        helper helper=new helper(context);
        db=helper.getWritableDatabase();
    }
    public long insert(phieumuon obj){
        ContentValues values=new ContentValues();
        values.put("matv",obj.matv);
        values.put("masach",obj.masach);
        values.put("tienthue",obj.tienthue);
        values.put("trasach",obj.trasach);
        values.put("ngay", obj.ngay);
        return db.insert("phieumuon",null,values);
    }
    public int update(phieumuon obj){
        ContentValues values=new ContentValues();
        values.put("matv",obj.matv);
        values.put("masach",obj.masach);
        values.put("tienthue",obj.tienthue);
        values.put("trasach",obj.trasach);
        values.put("ngay", obj.ngay);
        return db.update("phieumuon",values,"mapm=?",new String[]{String.valueOf(obj.mapm)});

    }
    public int delete(int id){
        return db.delete("phieumuon","mapm=?",new String[]{String.valueOf(id)});
    }
    public List<phieumuon> getall(){
        String sql="select * from phieumuon";
        return getdata(sql);
    }
    public phieumuon getid(String id){
        String sql="select * from phieumuon where mapm=?";
        List<phieumuon>list=getdata(sql,id);
        return list.get(0);
    }
    private List<phieumuon> getdata(String sql,String...id) {
        List<phieumuon> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,id);
        while (c.moveToNext()){
            phieumuon obj=new phieumuon();
            obj.mapm=Integer.parseInt(c.getString(c.getColumnIndex("mapm")));
            obj.matv=Integer.parseInt(c.getString(c.getColumnIndex("matv")));
            obj.masach=Integer.parseInt(c.getString(c.getColumnIndex("masach")));
            obj.tienthue=Integer.parseInt(c.getString(c.getColumnIndex("tienthue")));
            obj.trasach=Integer.parseInt(c.getString(c.getColumnIndex("trasach")));
            obj.ngay=c.getString(c.getColumnIndex("ngay"));
            list.add(obj);
        }
        return list;
    }
}

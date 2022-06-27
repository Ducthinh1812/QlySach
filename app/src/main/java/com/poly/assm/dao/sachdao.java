package com.poly.assm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.assm.database.helper;
import com.poly.assm.model.sach;

import java.util.ArrayList;
import java.util.List;

public class sachdao {
     helper helper;
    private SQLiteDatabase db;
     public sachdao(Context context){
         helper=new helper(context);
         db=helper.getWritableDatabase();
     }
    public List<sach> getall(){
        String sql="select * from sach";
        return getdata(sql);
    }
    public sach getid(String id){
        String sql="select * from sach where masach=?";
        List<sach> list =getdata(sql,id);
        return list.get(0);
    }
    private List<sach> getdata(String sql,String...id) {
        List<sach> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,id);
        while (c.moveToNext()){
            sach obj=new sach();
            obj.masach=Integer.parseInt(c.getString(c.getColumnIndex("masach")));
            obj.tensach=c.getString(c.getColumnIndex("tensach"));
            obj.giathue=Integer.parseInt(c.getString(c.getColumnIndex("giathue")));
            obj.matheloai=Integer.parseInt(c.getString(c.getColumnIndex("matheloai")));
            list.add(obj);
        }
        return list;
    }
    public long insert(sach obj){
        ContentValues values=new ContentValues();
        values.put("tensach",obj.tensach);
        values.put("giathue",obj.giathue);
        values.put("matheloai",obj.matheloai);
        return db.insert("sach",null,values);
    }
    public int update(sach obj){
        ContentValues values=new ContentValues();
        values.put("tensach",obj.tensach);
        values.put("giathue",obj.giathue);
        values.put("matheloai",obj.matheloai);
        return db.update("sach",values,"masach=?",new String[]{String.valueOf(obj.masach)});

    }

    public int delete(int id){
        return db.delete("sach","masach=?",new String[]{String.valueOf(id)});
    }
}

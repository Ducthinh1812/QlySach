package com.poly.assm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.poly.assm.database.helper;
import com.poly.assm.model.sach;
import com.poly.assm.model.thanhvien;
import com.poly.assm.model.theloai;

import java.util.ArrayList;
import java.util.List;

public class thanhviendao {
    private SQLiteDatabase db;
    helper helper;
    public thanhviendao(Context context){
        helper=new helper(context);
        db=helper.getWritableDatabase();
    }
    public List<thanhvien> getall(){
        String sql="select * from thanhvien";
        return getdata(sql);
    }
    public thanhvien getid(String id){
        String sql="select * from thanhvien where matv=?";
        List<thanhvien> list =getdata(sql,id);
        return list.get(0);
    }
    private List<thanhvien> getdata(String sql,String...idd) {
        List<thanhvien> list= new ArrayList<thanhvien>();
        Cursor c=db.rawQuery(sql,idd);
        while (c.moveToNext()){
            thanhvien obj=new thanhvien();
            obj.matv=Integer.parseInt(c.getString(c.getColumnIndex("matv")));
            obj.tentv=c.getString(c.getColumnIndex("tentv"));
            obj.namsinh=c.getString(c.getColumnIndex("namsinh"));
            list.add(obj);
        }
        return list;
    }
    public long insert(thanhvien thanhvien) {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentv", thanhvien.getTentv());
        contentValues.put("namsinh", thanhvien.getNamsinh());
        return db.insert("thanhvien", null, contentValues);
    }
    public int update(thanhvien thanhvien) {
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentv", thanhvien.getTentv());
        contentValues.put("namsinh", thanhvien.getNamsinh());
        return db.update("thanhvien", contentValues, "matv="+thanhvien.getMatv(),null);
    }

    public int delete(int id) {
        return db.delete("thanhvien", "matv=?", new String[]{String.valueOf(id)});
    }
}

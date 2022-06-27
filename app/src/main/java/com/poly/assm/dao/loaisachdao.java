package com.poly.assm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.poly.assm.database.helper;
import com.poly.assm.model.sach;
import com.poly.assm.model.theloai;

import java.util.ArrayList;
import java.util.List;

public class loaisachdao {
    private SQLiteDatabase db;
    helper helper;
    public loaisachdao(Context context){
        helper=new helper(context);
        db=helper.getWritableDatabase();
    }
    public List<theloai> getall(){
        String sql="select * from tlsach";
        return getdata(sql);
    }
    public theloai getid(String id){
        String sql="select * from tlsach where matheloai=?";
        List<theloai> list =getdata(sql,id);
        return list.get(0);
    }
    private List<theloai> getdata(String sql,String...idd) {
        List<theloai> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,idd);
        while (c.moveToNext()){
            theloai obj=new theloai();
            obj.matheloai=Integer.parseInt(c.getString(c.getColumnIndex("matheloai")));
            obj.tentheloai=c.getString(c.getColumnIndex("tentheloai"));
            list.add(obj);
        }
        return list;
    }

    public long inset(theloai theloai) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentheloai", theloai.tentheloai);
        return db.insert("tlsach", null, contentValues);
    }

    public int update(theloai theloai) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentheloai", theloai.tentheloai);
        return db.update("tlsach", contentValues, "matheloai=?", new String[]{String.valueOf(theloai.matheloai)});
    }

    public int delete(int matheloai) {
        return db.delete("tlsach","matheloai=?",new String[]{String.valueOf(matheloai)});
    }
}

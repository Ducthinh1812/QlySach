package com.poly.assm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.poly.assm.database.helper;
import com.poly.assm.model.sach;
import com.poly.assm.model.thanhvien;
import com.poly.assm.model.thuthu;

import java.util.ArrayList;
import java.util.List;

public class thuthudao {
    SQLiteDatabase db;
    helper helper;
    public thuthudao(Context context){
        helper=new helper(context);
        db=helper.getWritableDatabase();
    }
    public long insert(thuthu thuthu){
        ContentValues values=new ContentValues();
        values.put("matt",thuthu.matt);
        values.put("hoten",thuthu.hoten);
        values.put("matkhau",thuthu.matkhau);
        return db.insert("thuthu",null,values);
    }
    public int update(thuthu thuthu){
        ContentValues values=new ContentValues();
        values.put("hoten",thuthu.hoten);
        values.put("matkhau",thuthu.matkhau);
        return db.update("thuthu",values,"matt=?",new String[]{thuthu.getMatt()});
    }
    public int delete(String id){
        return db.delete("thuthu","matt=?",new String[]{id});
    }
    public List<thuthu> getall(){
        String sql="select * from thuthu";
        return getdata(sql);
    }
    public thuthu getid(String id){
        String sql="select * from thuthu where hoten=?";
        List<thuthu>list=getdata(sql,id);
        return list.get(0);
    }
    public List<thuthu> getdata(String sql,String...id) {
        List<thuthu> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,id);
        while (c.moveToNext()){
            thuthu obj=new thuthu();
            obj.matt=c.getString(c.getColumnIndex("matt"));
            obj.hoten=c.getString(c.getColumnIndex("hoten"));
            obj.matkhau=c.getString(c.getColumnIndex("matkhau"));
            list.add(obj);
        }
        return list;
    }

    public int checklogin(String strpass, String strname) {
        String sql="select *from thuthu where hoten=? and matkhau=?";
        List<thuthu> list=getdata(sql,strname,strpass);
        if(list.size()==0)
            return -1;
        return 1;
    }
}

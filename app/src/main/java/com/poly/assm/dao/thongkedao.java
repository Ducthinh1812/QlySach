package com.poly.assm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.assm.database.helper;
import com.poly.assm.model.sach;
import com.poly.assm.model.top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class thongkedao {
    helper helper;
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public thongkedao(Context context){
        this.context=context;
        helper=new helper(context);
        db=helper.getWritableDatabase();
    }
    public List<top> gettop(){
        String sql="select masach,count(masach) as soluong from phieumuon group by masach order by soluong desc limit 10";
        List<top> list=new ArrayList<>();
        sachdao sachdao=new sachdao(context);
        Cursor c=db.rawQuery(sql,null);
        while (c.moveToNext()){
            top top=new top();
            sach sach=sachdao.getid(c.getString(c.getColumnIndex("masach")));
            top.tensach=sach.tensach;
            top.soluong=Integer.parseInt(c.getString(c.getColumnIndex("soluong")));
            list.add(top);
        }
        return list;
    }
    public int getdoanhthu(String tungay,String denngay){
        String sqldt="select SUM(tienthue) as doanhthu from phieumuon where ngay BETWEEN ? AND ?";
        List<Integer> list=new ArrayList<>();
        Cursor c=db.rawQuery(sqldt,new String[]{tungay,denngay});
        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhthu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}

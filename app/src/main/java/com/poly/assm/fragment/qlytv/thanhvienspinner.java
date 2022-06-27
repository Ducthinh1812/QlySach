package com.poly.assm.fragment.qlytv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.poly.assm.R;
import com.poly.assm.model.thanhvien;
import com.poly.assm.model.theloai;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class thanhvienspinner extends ArrayAdapter<thanhvien> {
    Context context;
    ArrayList<thanhvien> list;
    TextView Tvmatv,Tvtentv;

    public thanhvienspinner(@NonNull Context context, ArrayList<thanhvien> list){
        super(context,0,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvienspinner,null);
        }
        final thanhvien item=list.get(position);
        if(item!=null){
            Tvmatv=v.findViewById(R.id.TVmatv);
            Tvmatv.setText(item.matv+". ");
            Tvtentv=v.findViewById(R.id.TVtentv);
            Tvtentv.setText(item.tentv);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.thanhvienspinner,null);
        }
        final thanhvien item=list.get(position);
        if(item!=null){
            Tvmatv=view.findViewById(R.id.TVmatv);
            Tvmatv.setText(item.matv+". ");
            Tvtentv=view.findViewById(R.id.TVtentv);
            Tvtentv.setText(item.tentv);
        }
        return view;
    }
}

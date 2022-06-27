package com.poly.assm.fragment.quanlyls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.poly.assm.R;
import com.poly.assm.model.sach;
import com.poly.assm.model.theloai;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class loaisachspinner extends ArrayAdapter<theloai> {
    Context context;
    ArrayList<theloai> list;
    TextView tvmaloaisach,tvtenloaisach;
    public loaisachspinner(@NonNull Context context, ArrayList<theloai> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.loaisachspinner,null);
        }
        final theloai item=list.get(position);
        if(item!=null){
            tvmaloaisach=view.findViewById(R.id.TVmatlsach);
            tvmaloaisach.setText(item.matheloai+". ");
            tvtenloaisach=view.findViewById(R.id.TVtentlsach);
            tvtenloaisach.setText(item.tentheloai);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.loaisachspinner,null);
        }
        final theloai item=list.get(position);
        if(item!=null){
            tvmaloaisach=view.findViewById(R.id.TVmatlsach);
            tvmaloaisach.setText(item.matheloai+". ");
            tvtenloaisach=view.findViewById(R.id.TVtentlsach);
            tvtenloaisach.setText(item.tentheloai);
        }
        return view;
    }
}

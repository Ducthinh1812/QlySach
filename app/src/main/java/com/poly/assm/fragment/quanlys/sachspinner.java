package com.poly.assm.fragment.quanlys;

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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class sachspinner extends ArrayAdapter<sach> {
    Context context;
    ArrayList<sach> list;
    TextView Tvmas,Tvtens;

    public sachspinner(@NonNull Context context, ArrayList<sach> list){
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
            v=inflater.inflate(R.layout.sachspinner,null);
        }
        final sach item=list.get(position);
        if(item!=null){
            Tvmas=v.findViewById(R.id.TVmas);
            Tvmas.setText(item.masach+". ");
            Tvtens=v.findViewById(R.id.TVtens);
            Tvtens.setText(item.tensach);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.sachspinner,null);
        }
        final sach item=list.get(position);
        if(item!=null){
            Tvmas=view.findViewById(R.id.TVmas);
            Tvmas.setText(item.masach+". ");
            Tvtens=view.findViewById(R.id.TVtens);
            Tvtens.setText(item.tensach);
        }
        return view;
    }
}

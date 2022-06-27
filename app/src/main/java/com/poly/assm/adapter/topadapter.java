package com.poly.assm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.poly.assm.R;
import com.poly.assm.fragment.thongke.top10fragment;
import com.poly.assm.model.top;

import java.util.ArrayList;

public class topadapter extends ArrayAdapter<top> {
    Context context;
    top10fragment fragment;
    private ArrayList<top> list;
    TextView TVsach,TVsol;
    ImageView imxoa;
    public topadapter(@NonNull Context context, top10fragment fragment,ArrayList<top> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.topitem,null);
        }
        final top item=list.get(position);
        if(item!=null){
            TVsach=view.findViewById(R.id.TVSach);
            TVsach.setText("Sách: "+item.tensach);
            TVsol=view.findViewById(R.id.TVsoluong);
            TVsol.setText("Số lượng: "+item.soluong);
        }
        return view;
    }
}

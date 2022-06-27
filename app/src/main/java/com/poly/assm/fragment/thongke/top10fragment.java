package com.poly.assm.fragment.thongke;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.poly.assm.R;
import com.poly.assm.adapter.topadapter;
import com.poly.assm.dao.thongkedao;
import com.poly.assm.model.top;

import java.util.ArrayList;

public class top10fragment extends Fragment {
    ListView lvtop;
    ArrayList<top> list;
    topadapter topadapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_top10fragment, container, false);
        lvtop=root.findViewById(R.id.lvtop);
        thongkedao thongkedao=new thongkedao(getActivity());
        list=(ArrayList<top>)thongkedao.gettop();
        topadapter=new topadapter(getActivity(),this,list);
        lvtop.setAdapter(topadapter);
        return root;
    }
}
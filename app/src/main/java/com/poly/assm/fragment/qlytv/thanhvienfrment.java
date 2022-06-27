package com.poly.assm.fragment.qlytv;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.poly.assm.R;
import com.poly.assm.adapter.thanhvienadapter;
import com.poly.assm.dao.thanhviendao;
import com.poly.assm.model.thanhvien;
import com.poly.assm.model.theloai;

import java.util.ArrayList;

public class thanhvienfrment extends Fragment {
    ListView lv;
    ArrayList<thanhvien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edtmatv,edttentv,edtnams;
    Button btsave,btcancel;
    thanhviendao thanhviendao;
    thanhvienadapter thanhvienadapter;
    thanhvien item;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_thanhvienfrment, container, false);
        lv=v.findViewById(R.id.lvtv);
        fab=v.findViewById(R.id.fabtv);
        thanhviendao=new thanhviendao(getActivity());
        capnhat();
        fab.setOnClickListener((v1) ->{
            opendialog(getActivity(),0);
        });
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            item=list.get(position);
            opendialog(getActivity(),1);
            return false;
        });
        return v;
    }

    protected void opendialog(final Context context,final int type) {
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.thanhviendialog);
        edtmatv=dialog.findViewById(R.id.edtmaTV);
        edttentv=dialog.findViewById(R.id.edttenTV);
        edtnams=dialog.findViewById(R.id.edtns);
        btcancel=dialog.findViewById(R.id.bthuytv);
        btsave=dialog.findViewById(R.id.btluutv);
        edtmatv.setEnabled(false);
        if (type != 0) {
            edtmatv.setText(String.valueOf(item.matv));
            edttentv.setText(item.tentv);
            edtnams.setText(item.namsinh);
    }
        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btsave.setOnClickListener(v -> {
            item=new thanhvien();
            item.tentv=edttentv.getText().toString();
            item.namsinh=edtnams.getText().toString();
            if(validate()>0){
                if(type==0){
                    if(thanhviendao.insert(item)>0){
                        Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Thêm không thành công",Toast.LENGTH_SHORT).show();
                    }
                }
                capnhat();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public int validate() {
        int check=1;
        if(edttentv.getText().length()==0||edtnams.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
    public boolean sua(thanhvien tv){
        int row =thanhviendao.update(tv);
        capnhat();
        return (row>0);
    }
    public void xoa(final int id){
       thanhviendao.delete(id);

    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(thanhviendao.getall());
        if(list != null){
            thanhvienadapter.notifyDataSetChanged();
        }
    }

    private void capnhat() {
        list=(ArrayList<thanhvien>) thanhviendao.getall();
        thanhvienadapter=new thanhvienadapter(getActivity(),this,list);
        lv.setAdapter(thanhvienadapter);
    }
}
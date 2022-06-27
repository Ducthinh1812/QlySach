package com.poly.assm.fragment.quanlyls;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.poly.assm.R;
import com.poly.assm.adapter.loaisachadapter;
import com.poly.assm.dao.loaisachdao;
import com.poly.assm.model.theloai;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class loaisachFragment extends Fragment {
    ArrayList<theloai> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edtmatl,edttentl;
    Button btsaver,btcancell;
    loaisachdao dao;
    loaisachadapter loaisachadapter;
    theloai item;
    RecyclerView rclLoaiSach;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_qlyls, container, false);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dao=new loaisachdao(getActivity());
        list = new ArrayList<>();

        rclLoaiSach= view.findViewById(R.id.lvls);
        fab=view.findViewById(R.id.fabls);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rclLoaiSach.setLayoutManager(linearLayoutManager);


        capnhat();
        fab.setOnClickListener((v1) ->{
            opendialog(getActivity(),0);
        });
    }

    protected void opendialog(final Context context, final int type) {
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.loaisachdialog);
        edtmatl=dialog.findViewById(R.id.edtmals);
        edttentl=dialog.findViewById(R.id.edttenls);
        btcancell=dialog.findViewById(R.id.bthuy);
        btsaver=dialog.findViewById(R.id.btluu);
        edtmatl.setEnabled(false);
        if (type != 0) {
            edtmatl.setText(String.valueOf(item.matheloai));
            edttentl.setText(item.tentheloai);
        }
        btcancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btsaver.setOnClickListener(v -> {
            item=new theloai();
            item.tentheloai=edttentl.getText().toString();
            if(validate()>0){
                if(type==0){
                    if(dao.inset(item)>0){
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
        if(edttentl.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
    public boolean sua(theloai tl){
        int row =dao.update(tl);
        capnhat();
        return (row>0);
    }
    public void xoa(final int id){
       dao.delete(id);
       capnhat();
    }
    private void capnhat() {
        list = (ArrayList<theloai>) dao.getall();
        loaisachadapter = new loaisachadapter(getActivity(),this,list);
        rclLoaiSach.setAdapter(loaisachadapter);
    }
}
package com.poly.assm.fragment.quanlys;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.poly.assm.R;
import com.poly.assm.adapter.sachadapter;
import com.poly.assm.dao.sachdao;
import com.poly.assm.dao.loaisachdao;
import com.poly.assm.fragment.quanlyls.loaisachspinner;
import com.poly.assm.model.sach;
import com.poly.assm.model.theloai;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class quanlysfragment extends Fragment {
    RecyclerView rvsach;
    sachdao sachdao;
    sachadapter adapter;
    ArrayList<sach> sachlist;
    FloatingActionButton fab;
    EditText edtmasach,edttensach,edtgiathue;
    Spinner spinner;
    Button btluu,bthuy;
    sach item;
    loaisachspinner loaisachspinner;
    ArrayList<theloai> theloailist;
    loaisachdao loaisachdao;
    int maloaisach,position;
    Dialog dialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       return inflater.inflate(R.layout.fragment_qlys, container, false);
    }
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sachdao=new sachdao(getActivity());
        sachlist = new ArrayList<>();

        rvsach=view.findViewById(R.id.lvsach);
        fab=view.findViewById(R.id.fabsach);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvsach.setLayoutManager(linearLayoutManager);


        capnhat();
        fab.setOnClickListener((v1) ->{
            opendialog(getActivity(),0);
        });
    }
    protected void opendialog(final Context context,final int type){
        dialog =new Dialog(context);
        dialog.setContentView(R.layout.sachdialog);
        edtmasach=dialog.findViewById(R.id.edtmas);
        edttensach=dialog.findViewById(R.id.edTtens);
        edtgiathue=dialog.findViewById(R.id.edtgiaThu);
        spinner=dialog.findViewById(R.id.spinner);
        bthuy=dialog.findViewById(R.id.bthuyS);
        btluu=dialog.findViewById(R.id.btluuS);
        theloailist=new ArrayList<theloai>();
        loaisachdao =new loaisachdao(context);
        theloailist=(ArrayList<theloai>) loaisachdao.getall();
        loaisachspinner=new loaisachspinner(context,theloailist);
        spinner.setAdapter(loaisachspinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maloaisach=theloailist.get(position).matheloai;
                Toast.makeText(context,"Chọn "+maloaisach,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edtmasach.setEnabled(false);
        if (type != 0) {
            edtmasach.setText(String.valueOf(item.masach));
            edttensach.setText(item.tensach);
            edtgiathue.setText(String.valueOf(item.giathue));
            for (int i=0;i<theloailist.size();i++)
                if(item.matheloai==(theloailist.get(i).matheloai)){
                    position=i;
                }
                spinner.setSelection(position);
        }
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btluu.setOnClickListener(v -> {
            item=new sach();
            item.tensach=edttensach.getText().toString();
            item.giathue=Integer.parseInt(edtgiathue.getText().toString());
            item.matheloai=maloaisach;
            if(validate()>0){
                if(type==0){
                    if(sachdao.insert(item)>0){
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
        if(edttensach.getText().length()==0||edtgiathue.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
    public boolean sua(sach s){
        int row =sachdao.update(s);
        capnhat();
        return (row>0);
    }
    public void xoa(final int id){
        sachdao.delete(id);
        capnhat();
    }
    private void capnhat() {
        sachlist=(ArrayList<sach>) sachdao.getall();
        adapter=new sachadapter(getActivity(),this,sachlist);
        rvsach.setAdapter(adapter);
    }

}
package com.poly.assm.fragment.quanlypm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.poly.assm.R;
import com.poly.assm.adapter.phieumuonadapter;
import com.poly.assm.dao.phieumuondao;
import com.poly.assm.dao.sachdao;
import com.poly.assm.dao.thanhviendao;
import com.poly.assm.fragment.qlytv.thanhvienspinner;
import com.poly.assm.fragment.quanlys.sachspinner;
import com.poly.assm.model.phieumuon;
import com.poly.assm.model.sach;
import com.poly.assm.model.thanhvien;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class phieumuonFragment extends Fragment {
    RecyclerView lvpm;
    ArrayList<phieumuon> listpm;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edtmapm,nGay;
    Spinner spTV,spsach;
    TextView tvTienthue;
    CheckBox checktrasach;
    Button Btnsave,Btncancel,btnnhapngay;
    phieumuondao phieumuondao;
    phieumuonadapter adapter;
    phieumuon item;
    thanhvienspinner thanhvienspinner;
    ArrayList<thanhvien> listthanhvien;
    thanhviendao thanhviendao;
    thanhvien thanhvien;
    int mathanhvien;
    sachspinner sachspinner;
    ArrayList<sach> listsach;
    sachdao sachdao;
    sach sach;
    int maSach,tienThue;
    int positiontv,Positionsach;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_qlypm, container, false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phieumuondao=new phieumuondao(getActivity());
        listpm = new ArrayList<>();

        lvpm=view.findViewById(R.id.lvpm);
        fab=view.findViewById(R.id.fabpm);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        lvpm.setLayoutManager(linearLayoutManager);


        capnhat();
        fab.setOnClickListener((v1) ->{
            opendialog(getActivity(),0);
        });
    }
    protected void opendialog(final Context context, final int type) {
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.phieumuondialog);
        edtmapm=dialog.findViewById(R.id.edtmapm);
        spTV=dialog.findViewById(R.id.spmatv);
        spsach=dialog.findViewById(R.id.spmas);
       nGay=dialog.findViewById(R.id.Tvngay);
       tvTienthue=dialog.findViewById(R.id.tvtienthue);
       checktrasach=dialog.findViewById(R.id.checktras);
       Btnsave=dialog.findViewById(R.id.Btnluu);
       Btncancel=dialog.findViewById(R.id.Btnhuy);
       btnnhapngay=dialog.findViewById(R.id.btnChonNgayPM);
       thanhviendao=new thanhviendao(context);
       listthanhvien=new ArrayList<>();
       listthanhvien=(ArrayList<thanhvien>)thanhviendao.getall();
       thanhvienspinner=new thanhvienspinner(context,listthanhvien);
       spTV.setAdapter(thanhvienspinner);
       spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               mathanhvien=listthanhvien.get(position).matv;
               Toast.makeText(context,"chọn "+mathanhvien,Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
       btnnhapngay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       month = month+1;
                       nGay.setText(year + "-" + month + "-"+dayOfMonth);
                   }
               }, Calendar.YEAR, Calendar.MONTH, Calendar.DATE);
               datePickerDialog.show();
           }
       });
       sachdao=new sachdao(context);
       listsach=new ArrayList<>();
       listsach=(ArrayList<sach>) sachdao.getall();
       sachspinner=new sachspinner(context,listsach);
       spsach.setAdapter(sachspinner);
       spsach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               maSach=listsach.get(position).masach;
               tienThue=listsach.get(position).giathue;
               Toast.makeText(context,"chọn "+maSach,Toast.LENGTH_SHORT).show();
               tvTienthue.setText("Tiền thuê:"+tienThue);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
       edtmapm.setEnabled(false);
       if (type!=0){
           edtmapm.setText(String.valueOf(item.mapm));
           for(int i=0;i<listthanhvien.size();i++)
               if(item.matv==(listthanhvien.get(i).matv)){
                   positiontv=i;
               }
               spTV.setSelection(positiontv);
               for(int i=0;i<listsach.size();i++){
                   if(item.masach==(listsach.get(i).masach)){
                       Positionsach=i;
                   }
           }
               spsach.setSelection(Positionsach);
               if(item.trasach==1){
                   checktrasach.setChecked(true);
               }else {
                   checktrasach.setChecked(false);
               }
       }
        Btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Btnsave.setOnClickListener((v) -> {
            item=new phieumuon();
            item.masach=maSach;
            item.matv=mathanhvien;
            item.ngay=nGay.getText().toString();
            item.tienthue=tienThue;
            if (checktrasach.isChecked()){
                item.trasach=1;
            }else {
                item.trasach=0;
            }
            if(validate()>0){
                if(type==0){
                    if(phieumuondao.insert(item)>0){
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
        if(nGay.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
    public boolean sua(phieumuon pm){
        int row =phieumuondao.update(pm);
        capnhat();
        return (row>0);
    }
    public void xoa(final int id){
       phieumuondao.delete(id);
       capnhat();
    }
    private void capnhat() {
        listpm=(ArrayList<phieumuon>) phieumuondao.getall();
        adapter=new phieumuonadapter(getActivity(),this,listpm);
        lvpm.setAdapter(adapter);
    }
}
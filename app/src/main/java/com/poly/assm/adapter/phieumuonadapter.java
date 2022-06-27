package com.poly.assm.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.assm.R;
import com.poly.assm.dao.sachdao;
import com.poly.assm.dao.thanhviendao;
import com.poly.assm.fragment.qlytv.thanhvienspinner;
import com.poly.assm.fragment.quanlypm.phieumuonFragment;
import com.poly.assm.fragment.quanlys.sachspinner;
import com.poly.assm.model.phieumuon;
import com.poly.assm.model.sach;
import com.poly.assm.model.thanhvien;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class phieumuonadapter extends RecyclerView.Adapter<phieumuonadapter.phieumuonViewHolder> {
    private Context context;
    phieumuonFragment fragment;
    ArrayList<phieumuon> list;
    ArrayList<sach> sachlist;
    ArrayList<thanhvien> tvList;
    sachdao sachdao;
    thanhviendao thanhviendao;
    sachspinner sachspinner;
    thanhvienspinner thanhvienspinner;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public phieumuonadapter(Context context, phieumuonFragment fragment, ArrayList<phieumuon> list) {
        this.list=list;
        this.context=context;
        this.fragment=fragment;
    }

    @NonNull
    @NotNull
    @Override
    public phieumuonViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemphieumuon, parent, false);
        return new phieumuonadapter.phieumuonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull phieumuonViewHolder holder, int position) {
       phieumuon phieumuon= list.get(position);
        holder.TVmapm.setText("Mã phiếu: "+phieumuon.getMapm());
        thanhviendao=new thanhviendao(context);
        thanhvien thanhvien1=thanhviendao.getid(String.valueOf(phieumuon.getMatv()));
        holder.TVtentv.setText("Thành viên: "+thanhvien1.getTentv());
        sachdao =new sachdao(context);
        sach sach1=sachdao.getid(String.valueOf(phieumuon.getMasach()));
        holder.Tvtensach.setText("Tên sách: "+sach1.getTensach());
        holder.TVtienthue.setText("Tiền: "+phieumuon.getTienthue());
        holder.TVngay.setText("Ngày: "+phieumuon.getNgay());
        if(phieumuon.trasach==1){
            holder.TVtrasach.setTextColor(Color.BLUE);
            holder.TVtrasach.setText("Đã trả sách");
            }else{
            holder.TVtrasach.setTextColor(Color.BLUE);
            holder.TVtrasach.setText("Chưa trả sách");
            }
        holder.imgsuap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.phieumuon_update);
                Spinner tentv=dialog.findViewById(R.id.sptmatv);
                EditText ngay=dialog.findViewById(R.id.Tvtngay);
                Spinner tsach=dialog.findViewById(R.id.sptmas);
                TextView tien=dialog.findViewById(R.id.tvttienthue);
                CheckBox tras=dialog.findViewById(R.id.tchecktras);
                Button huypm=dialog.findViewById(R.id.Btnhuyt);
                Button thempm=dialog.findViewById(R.id.Btnluut);
                ngay.setText(phieumuon.getNgay());
                if(phieumuon.trasach==1){
                    tras.setTextColor(Color.RED);
                    tras.setText("Đã trả sách");
                }else{
                    tras.setTextColor(Color.RED);
                    tras.setText("Chưa trả sách");
                }
                 sachlist=new ArrayList<sach>();
                sachdao=new sachdao(context);
                sachlist=(ArrayList<sach>)sachdao.getall();
                sachspinner=new sachspinner(context,sachlist);
                tsach.setAdapter(sachspinner);
                tsach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context,"Chọn "+sachlist.get(position).matheloai,Toast.LENGTH_SHORT).show();
                        phieumuon.setTienthue(sachlist.get(position).giathue);
                        phieumuon.setMasach(sachlist.get(position).masach);
                        tien.setText("Tiền thuê: "+sachlist.get(position).giathue);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                tvList=new ArrayList<>();
                thanhviendao=new thanhviendao(context);
                tvList=(ArrayList<thanhvien>)thanhviendao.getall();
                thanhvienspinner=new thanhvienspinner(context,tvList);
                tentv.setAdapter(thanhvienspinner);
                tentv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context,"chọn "+tvList.get(position).matv,Toast.LENGTH_SHORT).show();
                        phieumuon.setMatv(tvList.get(position).matv);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                thempm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vie) {
                        String date=ngay.getText().toString();
                        phieumuon.setNgay(date);
                        if (fragment.sua(phieumuon)) {
                            Toast.makeText(context, "cập nhập thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "cập nhập không thành công", Toast.LENGTH_LONG).show();
                        }
                        notifyItemChanged(position);
                    }
                });
                huypm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("delete");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fragment.xoa(phieumuon.getMapm());
                        fragment.onResume();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class phieumuonViewHolder extends RecyclerView.ViewHolder {
        TextView TVmapm,Tvtensach,TVtentv,TVtienthue,TVngay,TVtrasach;
        ImageView imgxoa,imgsuap;
        sach sach;
        phieumuon phieumuon;
        public phieumuonViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            TVmapm = itemView.findViewById(R.id.Tvmapm);
            Tvtensach = itemView.findViewById(R.id.Tvtens);
            TVtentv=itemView.findViewById(R.id.tvtenTV);
            TVtienthue=itemView.findViewById(R.id.tvtienThue);
            TVngay= itemView.findViewById(R.id.tvngay);
            TVtrasach= itemView.findViewById(R.id.TVtrasach);
            imgsuap=itemView.findViewById(R.id.imgsuapm);
            imgxoa=itemView.findViewById(R.id.imgxoa);
        }
    }
}

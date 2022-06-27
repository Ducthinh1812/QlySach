package com.poly.assm.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.poly.assm.R;
import com.poly.assm.fragment.qlytv.thanhvienfrment;
import com.poly.assm.model.thanhvien;

import java.util.ArrayList;

public class thanhvienadapter extends ArrayAdapter<thanhvien>{
    Context context;
    thanhvienfrment thanhvienfrment;
    ArrayList<thanhvien> list;
    TextView tvmatv,tvtentv,tvnamsinh;
    ImageView imgdel,imgsua;
    public thanhvienadapter(@NonNull Context context, thanhvienfrment thanhvienfrment,ArrayList<thanhvien> list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
        this.thanhvienfrment=thanhvienfrment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.itemthanhvien,null);
        }
        final thanhvien itemtv=list.get(position);
            tvmatv=view.findViewById(R.id.Tvmatvv);
            tvmatv.setText("Mã thành viên: "+itemtv.matv);
            tvtentv=view.findViewById(R.id.tvtentvv);
            tvtentv.setText("Tên thành viên: "+itemtv.tentv);
            tvnamsinh=view.findViewById(R.id.tvnss);
            tvnamsinh.setText("Năm sinh: "+itemtv.namsinh);
            imgdel=view.findViewById(R.id.imgxoatv);
            imgsua=view.findViewById(R.id.imgsuatv);
            imgsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(context);
                    dialog.setContentView(R.layout.thanhvien_update);
                    EditText tentv=dialog.findViewById(R.id.edtthemtentv);
                    EditText nams=dialog.findViewById(R.id.edtthemnamsinh);
                    Button huy=dialog.findViewById(R.id.btthuytv);
                    Button them=dialog.findViewById(R.id.bttluutv);
                    tentv.setText(itemtv.getTentv());
                    nams.setText(itemtv.getNamsinh());
                    them.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View vie) {
                            String tenthv = tentv.getText().toString();
                            String ns = nams.getText().toString();
                            itemtv.setTentv(tenthv);
                            itemtv.setNamsinh(ns);
                            if (thanhvienfrment.sua(itemtv)) {
                                Toast.makeText(context, "cập nhập thành công", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(context, "cập nhập không thành công", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    huy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
            imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("delete");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        thanhvienfrment.xoa(itemtv.getMatv());
                        thanhvienfrment.onResume();
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
        return view;
    }
}
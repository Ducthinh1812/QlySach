package com.poly.assm.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.assm.R;
import com.poly.assm.dao.loaisachdao;
import com.poly.assm.fragment.quanlyls.loaisachFragment;
import com.poly.assm.model.theloai;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class loaisachadapter extends RecyclerView.Adapter<loaisachadapter.LoaiSachViewHolder> {
    Context context;
    loaisachFragment fragment;
    ArrayList<theloai> list;
    loaisachdao loaisachdao;
    public loaisachadapter(@NonNull Context context, loaisachFragment fragment, ArrayList<theloai> list) {
        this.fragment=fragment;
        this.context=context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisach, parent, false);
        return new LoaiSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LoaiSachViewHolder holder, int position) {
        theloai theloai = list.get(position);
        holder.maLS.setText("Mã loại sách: "+theloai.getMatheloai());
        holder.tenLS.setText("Tên loại sách: "+theloai.getTentheloai());
        holder.imgsuals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog  dialog=new Dialog(context);
                dialog.setContentView(R.layout.loaisach_update);
                EditText tenls=dialog.findViewById(R.id.edtthemtenls);
                Button huy=dialog.findViewById(R.id.btthuy);
                Button them=dialog.findViewById(R.id.bttluu);
                tenls.setText(theloai.getTentheloai());
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vie) {
                        String tenloais = tenls.getText().toString();
                        theloai.setTentheloai(tenloais);
                        if (fragment.sua(theloai)) {
                            Toast.makeText(context, "cập nhập thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "cập nhập không thành công", Toast.LENGTH_LONG).show();
                        }
                        notifyItemChanged(position);
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
        holder.imgDeleteLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("delete");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fragment.xoa(theloai.getMatheloai());
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

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        TextView maLS, tenLS;
        ImageView imgDeleteLS,imgsuals;
        theloai theloai;
        public LoaiSachViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            maLS = itemView.findViewById(R.id.Tvmatlsach);
            tenLS = itemView.findViewById(R.id.tvtentlsach);
            imgDeleteLS = itemView.findViewById(R.id.imgxoals);
            imgsuals=itemView.findViewById(R.id.imgsuals);
        }
    }

}

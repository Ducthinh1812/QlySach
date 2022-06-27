package com.poly.assm.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.assm.R;
import com.poly.assm.dao.sachdao;
import com.poly.assm.dao.loaisachdao;
import com.poly.assm.fragment.quanlyls.loaisachspinner;
import com.poly.assm.fragment.quanlys.quanlysfragment;
import com.poly.assm.model.sach;
import com.poly.assm.model.theloai;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class sachadapter extends RecyclerView.Adapter<sachadapter.SachViewHolder> {
    private Context context;
    quanlysfragment quanlysfragment;
    sachdao sachdao;
    private ArrayList<sach> list;
    ArrayList<theloai> theloailist;
    loaisachdao loaisachdao;
    loaisachspinner loaisachspinner;
    public sachadapter(Context context,quanlysfragment quanlysfragment,ArrayList<sach> list) {
        this.quanlysfragment=quanlysfragment;
        this.context=context;
        this.list=list;
    }

    @NonNull
    @NotNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsach, parent, false);
        return new sachadapter.SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SachViewHolder holder, int position) {
        sach sach= list.get(position);
        holder.tvmasach.setText("Mã loại sách: "+sach.getMasach());
        holder.tvtensach.setText("Tên sách: "+sach.getTensach());
        holder.tvgiathue.setText("Giá thuê: "+sach.getGiathue());
        loaisachdao =new loaisachdao(context);
        theloai theloai= loaisachdao.getid(String.valueOf(sach.getMatheloai()));
        holder.tvloai.setText("Tên Loại sách: "+theloai.getTentheloai());
        holder.imgsuas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.sach_update);
                EditText tenls=dialog.findViewById(R.id.edtthemtens);
                EditText gias=dialog.findViewById(R.id.edtthemgia);
                Spinner ls=dialog.findViewById(R.id.sploais);
                Button huy=dialog.findViewById(R.id.btthuys);
                Button them=dialog.findViewById(R.id.bttluus);
                tenls.setText(sach.getTensach());
                gias.setText(sach.getGiathue()+"");
                theloailist=new ArrayList<theloai>();
                loaisachdao =new loaisachdao(context);
                theloailist=(ArrayList<theloai>) loaisachdao.getall();
                loaisachspinner=new loaisachspinner(context,theloailist);
                ls.setAdapter(loaisachspinner);
                ls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int maloaisach=theloailist.get(position).matheloai;
                        Toast.makeText(context,"Chọn "+maloaisach,Toast.LENGTH_SHORT).show();
                        sach.setMatheloai(maloaisach);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vie) {
                        String tens = tenls.getText().toString();
                        int gia=Integer.parseInt(gias.getText().toString());
                        sach.setTensach(tens);
                        sach.setGiathue(gia);
                        if (quanlysfragment.sua(sach)) {
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
        holder.imgff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("delete");
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       quanlysfragment.xoa(sach.getMasach());
                        quanlysfragment.onResume();
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

    public class SachViewHolder extends RecyclerView.ViewHolder {
        TextView tvmasach,tvtensach,tvgiathue,tvloai;
        ImageView imgff,imgsuas;
        public SachViewHolder(@NonNull @NotNull View view1) {
            super(view1);
            tvmasach = view1.findViewById(R.id.TvmaSach);
            tvtensach = view1.findViewById(R.id.tvtenSach);
            tvgiathue=view1.findViewById(R.id.tvGia);
            tvloai=view1.findViewById(R.id.tvls);
            imgff= view1.findViewById(R.id.imgdells);
            imgsuas=view1.findViewById(R.id.imgsuas);
        }
    }
}

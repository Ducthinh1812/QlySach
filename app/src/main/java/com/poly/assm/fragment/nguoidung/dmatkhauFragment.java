package com.poly.assm.fragment.nguoidung;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.poly.assm.R;
import com.poly.assm.dao.thuthudao;
import com.poly.assm.model.thuthu;

public class dmatkhauFragment extends Fragment {
    EditText edtmkcu,edtmk,edtnhaplai;
    Button btnluu,btnhuy;
    thuthudao dao;
    Context context;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_matkhau, container, false);
        return root;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtmkcu=view.findViewById(R.id.edtmkcu);
        edtmk=view.findViewById(R.id.edtmk);
        edtnhaplai=view.findViewById(R.id.edtnhaplaimk);
        btnhuy=view.findViewById(R.id.btnhuy);
        btnluu=view.findViewById(R.id.btnluu);
        dao=new thuthudao(getActivity());
        btnhuy.setOnClickListener((v) -> {
            edtmkcu.setText("");
            edtmk.setText("");
            edtnhaplai.setText("");
        });
        btnluu.setOnClickListener((v) ->{
            SharedPreferences preferences=getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
            String user=preferences.getString("hoten","");
            if (validate()>0){
                thuthu thuthu=dao.getid(user);
                thuthu.matkhau=edtmk.getText().toString();
                dao.update(thuthu);
                if (dao.update(thuthu)>0){
                    Toast.makeText(getActivity(),"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    edtmkcu.setText("");
                    edtmk.setText("");
                    edtnhaplai.setText("");
                }else {
                    Toast.makeText(getActivity(),"Thay đổi mật khẩu không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int validate() {
        int check=1;
        if (edtmkcu.getText().length()==0||edtmk.getText().length()==0||edtnhaplai.getText().length()==0){
            Toast.makeText(getContext(),"Bạn cần nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            SharedPreferences preferences=getActivity().getSharedPreferences("user_file",Context.MODE_PRIVATE);
            String mkcu=preferences.getString("matkhau","");
            String mk=edtmk.getText().toString();
            String nhaplai=edtnhaplai.getText().toString();
            if(!mkcu.equals(edtmkcu.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai",Toast.LENGTH_SHORT).show();
                check=-1;
            }if(!mk.equals(nhaplai)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}

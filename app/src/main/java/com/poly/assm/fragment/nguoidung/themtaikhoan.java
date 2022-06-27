package com.poly.assm.fragment.nguoidung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poly.assm.R;
import com.poly.assm.dao.thuthudao;
import com.poly.assm.model.thuthu;

public class themtaikhoan extends Fragment {
    EditText edttk,edtpass,edtcheckpass,edthoten;
    Button btnsave,btncancle;
    thuthudao thuthudao;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_themtaikhoan, container, false);


        return root;

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edttk=view.findViewById(R.id.edttaikhoan);
        edtpass=view.findViewById(R.id.edtmkdk);
        edtcheckpass=view.findViewById(R.id.edtcheckmk);
        edthoten=view.findViewById(R.id.edthoten);
        btncancle=view.findViewById(R.id.btnhuyy);
        btnsave=view.findViewById(R.id.btnsave);
        thuthudao=new thuthudao(getActivity());
        btncancle.setOnClickListener((v) -> {
            edthoten.setText("");
            edtcheckpass.setText("");
            edtpass.setText("");
            edttk.setText("");
        });
        btnsave.setOnClickListener((v) -> {
            thuthu thuthu=new thuthu();
            thuthu.matt=edttk.getText().toString();
            thuthu.hoten=edthoten.getText().toString();
            thuthu.matkhau=edtpass.getText().toString();
            if (validate()>0) {
                if (thuthudao.insert(thuthu) > 0) {
                    Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                    edttk.setText("");
                    edthoten.setText("");
                    edtpass.setText("");
                    edtcheckpass.setText("");
                } else {
                    Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int validate() {
        int check=1;
        if (edttk.getText().length()==0||edthoten.getText().length()==0||edtpass.getText().length()==0||edtcheckpass.getText().length()==0){
            Toast.makeText(getContext(),"Bạn cần nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            String pass=edtpass.getText().toString();
            String checkpass=edtcheckpass.getText().toString();
            if(!pass.equals(checkpass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}
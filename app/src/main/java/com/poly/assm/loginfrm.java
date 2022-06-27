package com.poly.assm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.poly.assm.dao.thuthudao;

public class loginfrm extends AppCompatActivity {
    EditText name,pass;
    Button login;
    CheckBox checkBox;
    thuthudao dao;
    String strname;
    String strpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginfrm);
        dao=new thuthudao(this);
        SharedPreferences preferences =getSharedPreferences("user_file",MODE_PRIVATE);
        login=findViewById(R.id.login);
        name=findViewById(R.id.edtname);
        pass=findViewById(R.id.edtpass);
        checkBox=findViewById(R.id.checkBox);
        name.setText(preferences.getString("hoten",""));
        pass.setText(preferences.getString("matkhau",""));
        checkBox.setChecked(preferences.getBoolean("remember",false));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklogin();
            }
        });
    }

    private void checklogin() {
        strname = name.getText().toString();
        strpass = pass.getText().toString();
        if(strname.isEmpty()||strpass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Nhập tên đăng nhâp và mật khẩu",Toast.LENGTH_SHORT).show();
        }else {
            if(dao.checklogin(strpass,strname)>0){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                remember(strname,strpass,checkBox.isChecked());
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strname);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên đăng nhâp và mật khẩu không đúng",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void remember(String strname, String strpass, boolean checked) {
        SharedPreferences preferences=getSharedPreferences("user_file",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(!checked){
            editor.clear();
        }else {
            editor.putString("hoten",strname);
            editor.putString("matkhau",strpass);
            editor.putBoolean("remember",checked);
        }
        editor.commit();
    }
}
package com.poly.assm.fragment.thongke;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.poly.assm.R;
import com.poly.assm.dao.thongkedao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class doanhthufragment extends Fragment {
    Button btntungay,btndenngay,btndoanhthu;
    EditText edttungay,edtdenngay;
    TextView tvdoanhthu;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    int myear,mmonth,mday;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_doanhthufragment, container, false);
        edttungay=v.findViewById(R.id.edttungay);
        edtdenngay=v.findViewById(R.id.edtdenngay);
        tvdoanhthu=v.findViewById(R.id.tvdoanhthu);
        btntungay=v.findViewById(R.id.btntungay);
        btndenngay=v.findViewById(R.id.btndenngay);
        btndoanhthu=v.findViewById(R.id.btndoanhthu);
        btntungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                myear=c.get(Calendar.YEAR);
                mmonth=c.get(Calendar.MONTH);
                mday=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),0,datetungay,myear,mmonth,mday);
                d.show();
            }
        });
        btndenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                myear=c.get(Calendar.YEAR);
                mmonth=c.get(Calendar.MONTH);
                mday=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d=new DatePickerDialog(getActivity(),0,datedenngay,myear,mmonth,mday);
                d.show();            }
        });
        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tungay=edttungay.getText().toString();
                String denngay=edtdenngay.getText().toString();
                thongkedao thongkedao=new thongkedao(getActivity());
                tvdoanhthu.setText("Doanh thu: "+thongkedao.getdoanhthu(tungay,denngay)+" VND");
            }
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener datetungay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myear=year;
            mmonth=month;
            mday=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(myear,mmonth,mday);
            edttungay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener datedenngay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myear=year;
            mmonth=month;
            mday=dayOfMonth;
            GregorianCalendar c=new GregorianCalendar(myear,mmonth,mday);
            edtdenngay.setText(sdf.format(c.getTime()));
        }
    };
}
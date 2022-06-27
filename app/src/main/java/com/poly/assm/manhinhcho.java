package com.poly.assm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class manhinhcho extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_cho);
        img = findViewById(R.id.img_start);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                img.setVisibility(View.GONE);
                Intent intent = new Intent(manhinhcho.this, loginfrm.class);
                startActivity(intent);

                finish();
            }
        }, 3000);
    }
}

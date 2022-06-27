  package com.poly.assm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.poly.assm.dao.thuthudao;
import com.poly.assm.model.thuthu;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    View view;
    thuthudao thuthudao;
    TextView uuser;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       setSupportActionBar(findViewById(R.id.toolbar));
       findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {
              Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();

           }
       });
        drawer = findViewById(R.id.drawer_layout);
       NavigationView navigationView = findViewById(R.id.nav_view);
        view=navigationView.getHeaderView(0);
        uuser=view.findViewById(R.id.tvuser);
        Intent intent=getIntent();
        String user =intent.getStringExtra("user");
        thuthudao=new thuthudao(this);
        thuthu thuthu=thuthudao.getid(user);
        String username=thuthu.hoten;
        uuser.setText("Tài Khoản: "+username);
        if(user.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.themuser).setVisible(true);
        }
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.quanlypm, R.id.quanlyls, R.id.quanlys,R.id.quanlytv,R.id.logout,R.id.doimk,R.id.top10,R.id.doanhthu,R.id.themuser)
                .setOpenableLayout(drawer)
               .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
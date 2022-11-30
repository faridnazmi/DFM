package com.company.dietfitstage7.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.BottomNavigationActivity.Setting;
import com.company.dietfitstage7.BottomNavigationActivity.TimerActivity;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class about_app extends AppCompatActivity {
    BottomNavigationView navigationView;
    SessionManagerDietfit sessionManagerDietfit;
    private long backPressedTime;
    TextView TVIG,TVTwitter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        TVIG = findViewById(R.id.textViewInstagram);
        TVTwitter = findViewById(R.id.textViewTwitter);

        TVIG.setText("@faridnazmi99");
        TVTwitter.setText("@faridnazmi99");

        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();

        //navigationView = findViewById(R.id.bottom_nav_view);

        /*navigationView.setSelectedItemId(R.id.menuaboutapp);

        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuSetting:
                        startActivity(new Intent(getApplicationContext(), Setting.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuTimer:
                        startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuaboutapp:

                }

            }
        }); */



    }

    @Override
    public void onBackPressed(){

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
package com.company.dietfitstage7.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.company.dietfitstage7.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class notification_list extends AppCompatActivity {
BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);

        navigationView = findViewById(R.id.bottom_nav_view);

        //navigationView.setSelectedItemId(R.id.menunotification);

       /* navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuaboutapp:
                        startActivity(new Intent(getApplicationContext(),about_app.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuSetting:
                        startActivity(new Intent(getApplicationContext(),Setting.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menunotification:

                }

            }
        }); */
    }
}
package com.company.dietfitstage7.BottomNavigationActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.company.dietfitstage7.Activity.ChangePasswordActivity;
import com.company.dietfitstage7.Activity.about_app;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.Activity.user_profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Setting extends AppCompatActivity {
    BottomNavigationView navigationView;
    CardView cardUserProfile,cardChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        navigationView = findViewById(R.id.bottom_nav_view);
        cardUserProfile = findViewById(R.id.cardUserProfileSetting);
        cardChangePassword = findViewById(R.id.cardChangePasswordSetting);

        cardUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), user_profile.class));
            }
        });

        cardChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
            }
        });

        navigationView.setSelectedItemId(R.id.menuSetting);

        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuCaloriesCalculator:
                        startActivity(new Intent(getApplicationContext(), CaloriesCalculatorActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuSetting:


                    case R.id.menuTimer:
                        startActivity(new Intent(getApplicationContext(),TimerActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;


                }

            }
        });
    }
}
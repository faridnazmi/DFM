package com.company.dietfitstage7.BottomNavigationActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.Activity.AllExercisesActivity;
import com.company.dietfitstage7.Activity.AllMealsActivity;
import com.company.dietfitstage7.Activity.about_app;
import com.company.dietfitstage7.Activity.user_profile;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Dashboard extends AppCompatActivity {
    //declare variable
    CardView recommendation,meals,exercises,health_tips,diary,bmi_calculator;
    BottomNavigationView navigationView;
    SessionManagerDietfit sessionManagerDietfit;
    private static TextView usernamehome;
    private long backPressedTime;
    ImageView logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //initialize variable
        recommendation = findViewById(R.id.card_recommendation);
        meals = findViewById(R.id.card_meals);
        exercises = findViewById(R.id.card_exercises);
        health_tips = findViewById(R.id.card_health_tips);
        diary = findViewById(R.id.card_diary);
        bmi_calculator = findViewById(R.id.card_bmi);
        navigationView = findViewById(R.id.bottom_nav_view);
        usernamehome = findViewById(R.id.home_username);
        logoutbtn = findViewById(R.id.Logoutbtn);
        //agehome = findViewById(R.id.home_age);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManagerDietfit.logout();
            }
        });

        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();

        /*Intent intent = getIntent();
        String username_user = intent.getStringExtra("username_user");
        String user_age = intent.getStringExtra("user_age"); */

       HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
       final String username_user = user.get(SessionManagerDietfit.Username_user);
       //final String user_age = user.get(SessionManagerDietfit.User_age);

       usernamehome.setText(username_user);
       //agehome.setText(user_age+" Years Old");

        navigationView.setSelectedItemId(R.id.menuHome);

        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:

                    case R.id.menuCaloriesCalculator:
                        startActivity(new Intent(getApplicationContext(), CaloriesCalculatorActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuSetting:
                        startActivity(new Intent(getApplicationContext(),Setting.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuTimer:
                        startActivity(new Intent(getApplicationContext(),TimerActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                }

            }
        });

        //card view listener every page
        recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.company.dietfitstage7.Activity.recommendation.class));
            }
        });

        meals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllMealsActivity.class));
            }
        });

        exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllExercisesActivity.class));
            }
        });

        health_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.company.dietfitstage7.Activity.health_tips.class));
            }
        });

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.company.dietfitstage7.Activity.diary.class));
            }
        });

        bmi_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.company.dietfitstage7.BMICalculatorActivity.bmi_calculator.class));
            }
        });

        //-----------------------------------------------------------------------------------------

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
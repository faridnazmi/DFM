package com.company.dietfitstage7.BottomNavigationActivity;
import com.company.dietfitstage7.Activity.about_app;
import com.company.dietfitstage7.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    TextView timerValue,CompleteMessage;
    static final long START_TIME_IN_MILLIS = 3600000;
    CountDownTimer countDownTimer;
    boolean timerRunning;
    long timeLeftInMillis = START_TIME_IN_MILLIS;
    BottomNavigationView navigationView;
    Button btnStartTimer,btnStopTimer,btnReplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerValue = (TextView)findViewById(R.id.Timer);
        btnStartTimer = findViewById(R.id.buttonStartTimer);
        btnStopTimer = findViewById(R.id.buttonStopTimer);
        btnReplay = findViewById(R.id.buttonReplay);
        CompleteMessage = findViewById(R.id.textWorkoutComplete);

        navigationView = findViewById(R.id.bottom_nav_view);

        navigationView.setSelectedItemId(R.id.menuTimer);

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
                        startActivity(new Intent(getApplicationContext(),Setting.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.menuTimer:




                }

            }
        });

        btnStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                CompleteMessage.setVisibility(View.GONE);
            }
        });

        btnStopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.onFinish();
                countDownTimer.cancel();

            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteMessage.setVisibility(View.GONE);
                countDownTimer.cancel();
                countDownTimer.start();
            }
        });

    }

    private void startTimer()
    {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis=millisUntilFinished;
                updateCountdownTimer();
            }

            @Override
            public void onFinish() {
               CompleteMessage.setVisibility(View.VISIBLE);

            }
        }.start();
        timerRunning = true;
    }

    private void updateCountdownTimer() {
        int minutes = (int) (timeLeftInMillis/1000) / 120;
        int seconds = (int) (timeLeftInMillis/1000) % 60;

        String timeLeft = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        timerValue.setText(timeLeft);

    }
}
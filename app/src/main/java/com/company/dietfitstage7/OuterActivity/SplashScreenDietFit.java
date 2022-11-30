package com.company.dietfitstage7.OuterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashScreenDietFit extends AppCompatActivity {
    Animation topanim,bottonanim;
    CircleImageView logodietfit;
    TextView TVDietfit,slogan;
    private static int SPLASH_SCREEN = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen_diet_fit);


        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottonanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logodietfit = findViewById(R.id.LogoSplashScreen_Dietfit);
        TVDietfit = findViewById(R.id.AppnameDietfit);
        slogan = findViewById(R.id.Slogan);

        logodietfit.setAnimation(topanim);
        TVDietfit.setAnimation(bottonanim);
        slogan.setAnimation(bottonanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        }, SPLASH_SCREEN);

    }
}
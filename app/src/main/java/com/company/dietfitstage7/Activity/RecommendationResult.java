package com.company.dietfitstage7.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.company.dietfitstage7.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RecommendationResult extends AppCompatActivity {
    ImageView btnBackRecommendationResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_result);
        btnBackRecommendationResult = findViewById(R.id.BackRecommendationResult);

        btnBackRecommendationResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),recommendation.class));
            }
        });
    }

}
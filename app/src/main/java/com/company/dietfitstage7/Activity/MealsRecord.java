package com.company.dietfitstage7.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.Adapter.MealsRecordAdapter;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;
import com.company.dietfitstage7.Data.MealData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MealsRecord extends AppCompatActivity {
    private RecyclerView recyclerViewMeals;
    private MealsRecordAdapter mealsRecordAdapter;
    private List<MealData> mealslist;
    ImageView BackMealsRecord;
    private static String URL_ViewMealsRecord ="http://192.168.100.196/Volley_Dietfit/View_recorded_meals.php";
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_record);
        BackMealsRecord = findViewById(R.id.BackMealsRecord);

        recyclerViewMeals = findViewById(R.id.recyclerviewMeals);
        recyclerViewMeals.setHasFixedSize(true);
        recyclerViewMeals.setLayoutManager(new LinearLayoutManager(this));

        mealslist = new ArrayList<>();
        LoadAllMeals();

        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();

        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);

        BackMealsRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), diary.class));
            }
        });

    }

    //Delete AllMealsActivity
    

    private void LoadAllMeals(){

        JsonArrayRequest request = new JsonArrayRequest(URL_ViewMealsRecord, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i< response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String meals_name = object.getString("meals_name").trim();
                        //String meals_new = object.getString("meals_new").trim();
                        String meals_note = object.getString("meals_note").trim();
                        String diary_date = object.getString("diary_date").trim();
                        String diary_id = object.getString("diary_id").trim();
                        String meals_cal_info = object.getString("meals_cal_info").trim();

                        MealData MealData = new MealData();
                        MealData.setMeals_name(meals_name);
                        //MealData.setMeals_new(meals_new);
                        MealData.setMeals_note(meals_note);
                        MealData.setDiary_date(diary_date);
                        MealData.setDiary_id(diary_id);
                        MealData.setMeals_cal_info(meals_cal_info);
                        mealslist.add(MealData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mealsRecordAdapter = new MealsRecordAdapter(MealsRecord.this,mealslist);
                recyclerViewMeals.setAdapter(mealsRecordAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MealsRecord.this, error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MealsRecord.this);
        requestQueue.add(request);
    }
}
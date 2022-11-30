package com.company.dietfitstage7.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.Adapter.DiaryRecordAdapter;
import com.company.dietfitstage7.Data.DiaryData;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaryRecord extends AppCompatActivity {

    private RecyclerView recyclerViewDiary;
    private DiaryRecordAdapter diaryRecordAdapter;
    private List<DiaryData> diarylist;

    private static String URL_ViewDiaryRecord = "http://192.168.100.196/Volley_Dietfit/View_recorded_diary.php";
    ImageView btnBackDiaryRecord;
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    Spinner SpinnerPopulateMeals, SpinnerPopulateExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diary_record);
        btnBackDiaryRecord = findViewById(R.id.BackDiaryRecord);

        recyclerViewDiary = findViewById(R.id.recyclerviewDiary);
        recyclerViewDiary.setHasFixedSize(true);
        recyclerViewDiary.setLayoutManager(new LinearLayoutManager(this));
        diarylist = new ArrayList<>();
        LoadAllDiary();

        btnBackDiaryRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), diary.class));
            }
        });

        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();

        HashMap<String, String> user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);
    }

    private void LoadAllDiary() {

       JsonArrayRequest request = new JsonArrayRequest(URL_ViewDiaryRecord, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(sessionManagerDietfit.isLogin()){
                    for(int i = 0; i< response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);

                            String diary_id = object.getString("diary_id").trim();
                            String meals_name = object.getString("meals_name").trim();
                            String ex_name = object.getString("ex_name").trim();
                            String ex_time_taken = object.getString("ex_time_taken").trim();
                            String ex_cal_info = object.getString("ex_cal_info").trim();
                            String meals_cal_info = object.getString("meals_cal_info").trim();
                            String meals_note = object.getString("meals_note").trim();
                            String ex_note = object.getString("ex_note").trim();
                            String diary_date = object.getString("diary_date").trim();

                            DiaryData diary = new DiaryData();
                            diary.setDiary_id(diary_id);
                            diary.setMeals_name(meals_name);
                            diary.setEx_name(ex_name);
                            diary.setMeals_note(meals_note);
                            diary.setEx_note(ex_note);
                            diary.setDiary_date(diary_date);
                            diary.setMeals_cal_info(meals_cal_info);
                            diary.setEx_time_taken(ex_time_taken);
                            diary.setEx_cal_info(ex_cal_info);
                            diarylist.add(diary);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }

                }else{
                    Toast.makeText(getApplicationContext(),"Diary Cannot Retrieve",Toast.LENGTH_SHORT).show();
                }
                diaryRecordAdapter = new DiaryRecordAdapter(DiaryRecord.this,diarylist);
                recyclerViewDiary.setAdapter(diaryRecordAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DiaryRecord.this, error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(DiaryRecord.this);
        requestQueue.add(request);
//
    }
}
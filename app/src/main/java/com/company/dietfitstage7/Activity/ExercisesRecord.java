package com.company.dietfitstage7.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.Adapter.ExercisesRecordAdapter;
import com.company.dietfitstage7.Data.ExerciseData;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExercisesRecord extends AppCompatActivity {

    private RecyclerView recyclerViewExercises;
    private ExercisesRecordAdapter exercisesRecordAdapter;
    private List<ExerciseData> exerciseslist;
    private static String URL_ViewExercisesRecord="http://192.168.100.196/Volley_Dietfit/View_recorded_exercises.php";
    ImageView BackExercisesRecord;
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_record);
        BackExercisesRecord = findViewById(R.id.BackExerciseRecord);

        recyclerViewExercises = findViewById(R.id.recyclerviewExercises);
        recyclerViewExercises.setHasFixedSize(true);
        recyclerViewExercises.setLayoutManager(new LinearLayoutManager(this));
        exerciseslist = new ArrayList<>();
        LoadAllExercises();

        BackExercisesRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), diary.class));
            }
        });

        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();

        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);
    }

    private void LoadAllExercises(){

        JsonArrayRequest request = new JsonArrayRequest(URL_ViewExercisesRecord, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i< response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String ex_name = object.getString("ex_name").trim();
                        //String ex_new = object.getString("ex_new").trim();
                        String ex_note = object.getString("ex_note").trim();
                        String diary_id = object.getString("diary_id").trim();
                        String diary_date = object.getString("diary_date").trim();
                        String ex_cal_info = object.getString("ex_cal_info").trim();
                        String ex_time_taken = object.getString("ex_time_taken").trim();
                        ExerciseData ExerciseData = new ExerciseData();
                        ExerciseData.setEx_name(ex_name);
                        //ExerciseData.setEx_new(ex_new);
                        ExerciseData.setEx_cal_info(ex_cal_info);
                        ExerciseData.setEx_time_taken(ex_time_taken);
                        ExerciseData.setEx_note(ex_note);
                        ExerciseData.setDiary_id(diary_id);
                        ExerciseData.setDiary_date(diary_date);
                        exerciseslist.add(ExerciseData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                exercisesRecordAdapter = new ExercisesRecordAdapter(ExercisesRecord.this,exerciseslist);
                recyclerViewExercises.setAdapter(exercisesRecordAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ExercisesRecord.this, error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ExercisesRecord.this);
        requestQueue.add(request);
    }
}

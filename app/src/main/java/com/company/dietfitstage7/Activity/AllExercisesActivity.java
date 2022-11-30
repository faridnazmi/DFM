package com.company.dietfitstage7.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.Adapter.AllExerciseAdapter;
import com.company.dietfitstage7.Adapter.AllMealsAdapter;
import com.company.dietfitstage7.Adapter.ExercisesRecordAdapter;
import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.Data.AllExerciseData;
import com.company.dietfitstage7.Data.AllMealsData;
import com.company.dietfitstage7.Data.ExerciseData;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllExercisesActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAllExercise;
    private AllExerciseAdapter AllExerciseAdapter;
    private List<AllExerciseData> AllExerciselist;
    CharSequence search="";
    EditText searchViewExercises;
    private static String URL_AllExercise="http://192.168.100.196/Volley_Dietfit/View_all_exercises.php";
    ImageView backDashboardExercises;
    private long backPressedTime;
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        backDashboardExercises = findViewById(R.id.BackExercises);
        recyclerViewAllExercise = findViewById(R.id.recyclerviewAllExercises);
        recyclerViewAllExercise.setHasFixedSize(true);
        recyclerViewAllExercise.setLayoutManager(new LinearLayoutManager(this));
        AllExerciselist = new ArrayList<>();
        LoadAllExercises();

        backDashboardExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });

        searchViewExercises = findViewById(R.id.searchViewExercises);
        searchViewExercises.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AllExerciseAdapter.getFilter().filter(s);
                search = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void LoadAllExercises() {
        JsonArrayRequest request = new JsonArrayRequest(URL_AllExercise, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i< response.length(); i++) {
                    try {

                        JSONObject object = response.getJSONObject(i);
                        String ex_name = object.getString("ex_name").trim();
                        String ex_type = object.getString("ex_type").trim();
                        String calories_info = object.getString("calories_info").trim();
                        String ex_time_time = object.getString("ex_time_taken").trim();

                        AllExerciseData exercisesData = new AllExerciseData();
                        exercisesData.setEx_name(ex_name);
                        exercisesData.setEx_type(ex_type);
                        exercisesData.setCalories_info(calories_info);
                        exercisesData.setEx_time_taken(ex_time_time);
                        AllExerciselist.add(exercisesData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                AllExerciseAdapter = new AllExerciseAdapter(AllExercisesActivity.this,AllExerciselist);
                recyclerViewAllExercise.setAdapter(AllExerciseAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AllExercisesActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(AllExercisesActivity.this);
        requestQueue.add(request);
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
package com.company.dietfitstage7.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class create_diary extends AppCompatActivity {
    //Declare Variable
    private long backPressedTime;
    ImageView BackCreateDiary,btnClearMealsCaloriesInfo,btnClearMealsInfo,btnClearExerciseCaloriesInfo,btnClearExerciseInfo;
    EditText ETMealsName,ETMealsInfo,ETExerciseName,ETExerciseInfo,ETMealsCalInfo,ETExCalInfo,ETExTimeTaken;
    Button BtnAddDiary;
    private static String URL_ADD_DIARY = "http://192.168.100.196/Volley_Dietfit/add_diary.php";
    String SpinnerPopulationMeals = "http://192.168.100.196/Volley_Dietfit/populate_meals_id.php";
    String SpinnerPopulationExercise = "http://192.168.100.196/Volley_Dietfit/populate_exercises_id.php";
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    Spinner spinnerMealsName,spinnerExerciseName;
    ArrayList<String> mealsNameList = new ArrayList<>();
    ArrayList<String> exNameList = new ArrayList<>();
    ArrayAdapter<String> mealsNameAdapter;
    ArrayAdapter<String> exNameAdapter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diary);

        //Declare id for each Variable
        BackCreateDiary = findViewById(R.id.BackCreateDiary);
        btnClearMealsCaloriesInfo = findViewById(R.id.btnClearMealsCaloriesInfo);
        btnClearMealsInfo = findViewById(R.id.btnClearMealsDetail);
        btnClearExerciseCaloriesInfo = findViewById(R.id.btnClearExerciseCaloriesInfo);
        btnClearExerciseInfo = findViewById(R.id.btnClearExerciseDetail);
        ETMealsName = findViewById(R.id.editTextMealsName);
        ETMealsInfo = findViewById(R.id.editTextMealsDetail);
        ETExerciseName = findViewById(R.id.editTextExerciseName);
        ETExerciseInfo = findViewById(R.id.editTextExerciseDetail);
        BtnAddDiary = findViewById(R.id.buttonAddDiary);
        spinnerExerciseName = findViewById(R.id.SpinnerMealsName);
        spinnerMealsName = findViewById(R.id.SpinnerExerciseName);
        ETMealsCalInfo = findViewById(R.id.editTextMealsCalInfo);
        ETExCalInfo = findViewById(R.id.editTextExerciseCalInfo);
        ETExTimeTaken = findViewById(R.id.editTextExerciseTimeTaken);


        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();


        //Clear Text in the editText

        btnClearMealsCaloriesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETMealsName.getText().clear();
            }
        });

        btnClearMealsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETMealsInfo.getText().clear();
            }
        });

        btnClearExerciseCaloriesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETExerciseName.getText().clear();
            }
        });

        btnClearExerciseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETExerciseInfo.getText().clear();
            }
        });

        // Back Button to Diary Interface
        BackCreateDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),diary.class));
            }
        });

        BtnAddDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            CreateDiary();
            }
        });
    }

    private void CreateDiary(){

        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);
        final String username_user = user.get(sessionManagerDietfit.Username_user);
        final String user_age = user.get(sessionManagerDietfit.User_age);

        final String user_email = this.getEmail;
        final String meals_name = this.ETMealsName.getText().toString().trim();
        final String ex_name = this.ETExerciseName.getText().toString().trim();
        final String meals_note = this.ETMealsInfo.getText().toString().trim();
        final String ex_note = this.ETExerciseInfo.getText().toString().trim();
        final String meals_cal_info = this.ETMealsCalInfo.getText().toString().trim();
        final String ex_cal_info = this.ETExCalInfo.getText().toString().trim();
        final String ex_time_taken = this.ETExTimeTaken.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD_DIARY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(create_diary.this,"Successfully added!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),diary.class));
                            }
                            else{
                                Toast.makeText(create_diary.this,"something went wrong",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(create_diary.this,"Can't be added" , Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(create_diary.this,"Error Malfunction", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_email",user_email);
                params.put("meals_name",meals_name);
                params.put("ex_name",ex_name);
                params.put("meals_note",meals_note);
                params.put("ex_note",ex_note);
                params.put("meals_cal_info",meals_cal_info);
                params.put("ex_cal_info",ex_cal_info);
                params.put("ex_time_taken",ex_time_taken);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void spinnerMeals() {

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, SpinnerPopulationMeals, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("list_meals");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String meals_name = jsonObject.optString("meals_name");
                        String calories_info = jsonObject.optString("calories_info");
                        mealsNameList.add(meals_name +" ("+ calories_info+")");
                        mealsNameAdapter = new ArrayAdapter<>(create_diary.this, android.R.layout.simple_spinner_item,mealsNameList);
                        mealsNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerMealsName.setAdapter(mealsNameAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    public void spinnerExercise() {

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, SpinnerPopulationExercise, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("list_exercise");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String ex_name = jsonObject.optString("ex_name");
                        String calories_info = jsonObject.optString("calories_info");
                        exNameList.add(ex_name+" ("+calories_info+")");
                        exNameAdapter = new ArrayAdapter<>(create_diary.this, android.R.layout.simple_spinner_item,exNameList);
                        exNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerExerciseName.setAdapter(exNameAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    protected void onResume(){
        super.onResume();
        spinnerMeals();
        spinnerExercise();
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
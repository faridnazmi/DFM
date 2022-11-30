package com.company.dietfitstage7.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
import com.company.dietfitstage7.BottomNavigationActivity.CaloriesCalculatorActivity;
import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class diary extends AppCompatActivity {
    ImageView backDashboardDiary,BtnAddCalGoals,btnCaloriesCalculator;
    FloatingActionButton btncreatenewdiary, btnViewAllDiaryRecord;
    private long backPressedTime;
    EditText addcalgoals;
    CardView cardmealsrecord,cardexerciserecord;
    LayoutInflater inflater;
    AlertDialog.Builder alert_dialog;
    TextView CalGoals,CalculateCalNeed;
    String getEmail;
    SessionManagerDietfit sessionManagerDietfit;
    private static String URL_ADD_CAL = "http://192.168.100.196/Volley_Dietfit/update_calories_goal.php";
    private static String URL_READ_CAL = "http://192.168.100.196/Volley_Dietfit/read_calories_goal.php";
    private static final String TAG = diary.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        // Link Var to id ----------------------------------------------------------------------------------------------
        backDashboardDiary = findViewById(R.id.BackDiary);
        btncreatenewdiary = findViewById(R.id.FbInsertDiary);
        cardmealsrecord = findViewById(R.id.cardMealsRecord);
        cardexerciserecord = findViewById(R.id.cardExerciseRecord);
        BtnAddCalGoals = findViewById(R.id.AddCaloriesGoal);
        addcalgoals = findViewById(R.id.editTextAddCalGoals);
        CalGoals = findViewById(R.id.textViewCaloriesGoal);
        btnViewAllDiaryRecord = findViewById(R.id.FbViewALlDiary);
        //btnCaloriesCalculator = findViewById(R.id.CaloriesCalculator);
        CalculateCalNeed = findViewById(R.id.ButtonGoCaloriesCal);


        //------------------------------------------------------------------------------------------------------
        alert_dialog = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();

        //---------------------------------------------------------------------------------------------------------
        BtnAddCalGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = inflater.inflate(R.layout.calories_goal_insert,null);
                alert_dialog
                        .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                addcalgoals = view.findViewById(R.id.editTextAddCalGoals);

                                addCaloriesGoal();


                            }
                        }).setNegativeButton("Cancel", null).setView(view).create().show();
            }
        });

        //----------------------------------------------------------------------------------------------------------

        CalculateCalNeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CaloriesCalculatorDiary.class));

            }
        });

        //------------------------------------------------------------------------------------------------------------
        cardexerciserecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExercisesRecord.class));
            }
        });

        cardmealsrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MealsRecord.class));
            }
        });


        btncreatenewdiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),create_diary.class));
            }
        });

        btnViewAllDiaryRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DiaryRecord.class));
            }
        });

        backDashboardDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));

            }
        });


    }

    public void addCaloriesGoal() {

    HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
    getEmail = user.get(sessionManagerDietfit.User_email);

    final String calories_goal = addcalgoals.getText().toString().trim();
    final String user_email = getEmail;
    String username_user = user.get(sessionManagerDietfit.Username_user);
    String user_age = user.get(sessionManagerDietfit.User_age);

    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD_CAL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");

                        if (success.equals("1")){
                            Toast.makeText(diary.this,"Updated!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),diary.class));
                            overridePendingTransition(0,0);
                            sessionManagerDietfit.createSession(user_email,username_user,user_age);
                        }
                        else{
                            Toast.makeText(diary.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(diary.this,"Cannot Update try again later", Toast.LENGTH_SHORT).show();

                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(diary.this,"Error", Toast.LENGTH_SHORT).show();
                }
            })
    {
        @Override
        protected Map<String,String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            params.put("user_email",user_email);
            params.put("calories_goal",calories_goal);
            return params;
        }
    };

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);

    }

    public void read_calories_diary() {
        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ_CAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")) {

                                for(int i=0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    getEmail = object.getString("user_email").trim();
                                    String calories_goal = object.getString("calories_goal").trim();



                                    CalGoals.setText(calories_goal);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(diary.this, "Your Profile Cannot Be Read "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(diary.this, "Error Malfunction "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_email", getEmail);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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

    @Override
    protected void onResume(){
        super.onResume();
        read_calories_diary();
    }
}
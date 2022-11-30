package com.company.dietfitstage7.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.Activity.about_app;
import com.company.dietfitstage7.Activity.diary;
import com.company.dietfitstage7.OuterActivity.registerUser;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CaloriesCalculatorDiary extends AppCompatActivity {
    EditText userAge,userWeight,userHeight,BMResult,CalNeedResult;
    Spinner userGender,userActiveLevel;
    Button btnCalculate,btnCalNeed,btnUpdateCalGoals;
    ImageButton btnInfo;
    TextView viewStep;
    ImageView btnBackCalCalculatorDiary,btnImageActLvl;
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    private static String URL_ADD_CAL = "http://192.168.100.196/Volley_Dietfit/update_calories_goal.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_calculator_diary);
        userAge = findViewById(R.id.editAgeCalDiary);
        userWeight = findViewById(R.id.editWeightCalDiary);
        userHeight = findViewById(R.id.editHeightCalDiary);
        userGender = findViewById(R.id.editGenderCalDiary);
        BMResult = findViewById(R.id.BMR_ResultCalDiary);
        btnCalculate = findViewById(R.id.calculate_btnCalDiary);
        btnCalNeed = findViewById(R.id.buttonCaloriesNeedCalDiary);
        userActiveLevel = findViewById(R.id.editActiveLevelCalDiary);
        CalNeedResult = findViewById(R.id.ResultCaloriesNeededCalDiary);
        btnInfo = findViewById(R.id.buttonInfoCalDiary);
        viewStep = findViewById(R.id.ViewStepsCalDiary);
        btnUpdateCalGoals = findViewById(R.id.buttonUpdateCaloriesGoalDiary);
        btnBackCalCalculatorDiary = findViewById(R.id.BackCaloriesCalculatorDiary);
        btnImageActLvl = findViewById(R.id.buttonInfoActLvl);


        btnImageActLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CaloriesCalculatorDiary.this);
                builder.setTitle("About Active Level");
                builder.setMessage(
                        "Little/no exercise : If you just having light exercise such as weighting dumbbell once in a week / not making any exercise" +
                                "\n\n Light exercise : If you just do any exercise  once in a week ."+
                                "\n\nModerate exercise : If you constantly do exercise 3-5 days in a week"+
                                "\n\nVery Active : If you constantly do any exercise 6-7 days in a week"+
                                "\n\n Extra Active : If you are having exercise everyday and always involve in physical job");
                // add a button
                builder.setPositiveButton("OK", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        BMResult.setFocusableInTouchMode(false);
        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();



        btnBackCalCalculatorDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),diary.class));
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userGender.getSelectedItem().equals("Male")){
                    male();
                }else if(userGender.getSelectedItem().equals("Female")) {
                    female();
                }else {
                    Toast.makeText(getApplicationContext(),"Please Select Your Gender",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(CaloriesCalculatorDiary.this);
                builder.setTitle("About BMR");
                builder.setMessage(
                        "BMR is short for Basal Metabolic Rate Your Basal Metabolic Rate is " +
                                "the number of calories required to keep your body functioning at rest, also known as your metabolism.");
                // add a button
                builder.setPositiveButton("OK", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        viewStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(CaloriesCalculatorDiary.this);
                builder.setTitle("Steps Calculating Calories Need");
                builder.setMessage(
                        "Step 1: Input Age,Height in cm, Weight in kg and choose your Gender." +
                                "\n\nStep 2: Click Calculate to Calculate BMR, BMR Result will appear below Calculate button."+
                                "\n\nStep 3: Once the BMR Result appear, Choose your Active level."+
                                "\n\nStep 4: Click Calculate below the active level box to calculate your calories need."+
                                "\n\nStep 5: When the result calories need appear, you can update the result to your calories goal.");
                // add a button
                builder.setPositiveButton("OK", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnCalNeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userActiveLevel.getSelectedItem().equals("Little/no exercise")){

                    double bmr_result = Double.parseDouble(BMResult.getText().toString().trim());
                    double CN_Little = bmr_result*1.2;
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String CNResult = decimalFormat.format(CN_Little);
                    CalNeedResult.setText(CNResult);

                }else if(userActiveLevel.getSelectedItem().equals("Light exercise")){

                    double bmr_result = Double.parseDouble(BMResult.getText().toString().trim());
                    double CN_Little = bmr_result*1.375;
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String CNResult = decimalFormat.format(CN_Little);
                    CalNeedResult.setText(CNResult);

                }else if (userActiveLevel.getSelectedItem().equals("Moderate exercise")){

                    double bmr_result = Double.parseDouble(BMResult.getText().toString().trim());
                    double CN_Little = bmr_result*1.55;
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String CNResult = decimalFormat.format(CN_Little);
                    CalNeedResult.setText(CNResult);

                }else if (userActiveLevel.getSelectedItem().equals("Very Active")){

                    double bmr_result = Double.parseDouble(BMResult.getText().toString().trim());
                    double CN_Little = bmr_result*1.725;
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String CNResult = decimalFormat.format(CN_Little);
                    CalNeedResult.setText(CNResult);

                }else if (userActiveLevel.getSelectedItem().equals("Extra Active")){

                    double bmr_result = Double.parseDouble(BMResult.getText().toString().trim());
                    double CN_Little = bmr_result*1.9;
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String CNResult = decimalFormat.format(CN_Little);
                    CalNeedResult.setText(CNResult);

                }else{
                    Toast.makeText(getApplicationContext(),"Choose Your Active Level!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdateCalGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CalNeedResult.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Calculate First",Toast.LENGTH_SHORT).show();
                }else {
                    UpdateCaloriesGoal();
                }
            }
        });

    }
    public void UpdateCaloriesGoal() {

        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);

        final String calories_goal = CalNeedResult.getText().toString().trim();
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
                                Toast.makeText(CaloriesCalculatorDiary.this,"Updated!,Check Your Latest Calories Goal at Diary", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),diary.class));
                                sessionManagerDietfit.createSession(user_email,username_user,user_age);
                            }
                            else{
                                Toast.makeText(CaloriesCalculatorDiary.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CaloriesCalculatorDiary.this,"Cannot Update try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CaloriesCalculatorDiary.this,"Error updating", Toast.LENGTH_SHORT).show();
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

    private void male() {

        int age = Integer.parseInt(userAge.getText().toString().trim());
        double weight = Double.parseDouble(userWeight.getText().toString().trim());
        double height = Double.parseDouble(userHeight.getText().toString().trim());
        double bmr_men = 88.362+(13.397*weight)+(4.77*height) - (5.677*age);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmrMen = decimalFormat.format(bmr_men);
        BMResult.setText(bmrMen);

    }
    private void female(){

        int age = Integer.parseInt(userAge.getText().toString().trim());
        double weight = Double.parseDouble(userWeight.getText().toString().trim());
        double height = Double.parseDouble(userHeight.getText().toString().trim());
        double bmr_women = 447.593 + (9.247*weight) + (3.098*height) - (4.330*age);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String bmrFemale = decimalFormat.format(bmr_women);
        BMResult.setText(bmrFemale);

    }
}
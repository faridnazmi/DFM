package com.company.dietfitstage7.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.BMICalculatorActivity.bmi_calculator_recommendation;
import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class recommendation extends AppCompatActivity {
    ImageView backDashboardRecommendation,viewInfoActlvlRec;
    private long backPressedTime;
    TextView calculateBmiRegister,BreakfastMeal,LunchMeal,DinnerMeal,SupperMeal,MorningWorkout,EveningWorkout,NightWorkout;
    Button ButtonCancelRec;
    EditText ETAge,ETGender,ETBmi,ETActLvl;
    private static final String TAG = recommendation.class.getSimpleName();
    private static String URL_READ_RECOMMENDATION = "http://192.168.100.196/Volley_Dietfit/read_recommendation.php";
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    Button btnSuggest;
    LinearLayout cardExSuggestionL,cardMealsSuggestionL;
    TextView textMealsSuggest,textExerciseSuggestion;
    ProgressBar pbSuggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        backDashboardRecommendation = findViewById(R.id.BackRecommendation);
        calculateBmiRegister = findViewById(R.id.BMICalculatorTextRec);
        ButtonCancelRec = findViewById(R.id.buttonCancelRecommend);
        ETAge = findViewById(R.id.editTextAgeRec);
        ETGender = findViewById(R.id.editTextGenderRec);
        ETBmi = findViewById(R.id.editTextBMIRec);
        ETActLvl = findViewById(R.id.editTextALRec);
        btnSuggest = findViewById(R.id.buttonSuggestion);
        BreakfastMeal = findViewById(R.id.meals_breakfast);
        LunchMeal = findViewById(R.id.RecLunchMeal);
        DinnerMeal = findViewById(R.id.DinnerMeals);
        SupperMeal = findViewById(R.id.SupperMeals);
        MorningWorkout = findViewById(R.id.morningworkoutname);
        EveningWorkout = findViewById(R.id.eveningworkoutname);
        NightWorkout = findViewById(R.id.nightworkoutname);
        pbSuggest = findViewById(R.id.progressBarRecommendation);
        viewInfoActlvlRec = findViewById(R.id.imageViewInfoRecActLvl);


        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();

        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);


        btnSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getApplicationContext(),RecommendationResult.class));
               // pbSuggest.setVisibility(View.VISIBLE);
                Suggest();

            }
        });

        ButtonCancelRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETAge.getText().clear();
                ETGender.getText().clear();
                ETBmi.getText().clear();
                ETActLvl.getText().clear();
            }
        });

        viewInfoActlvlRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(recommendation.this);
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

        calculateBmiRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), bmi_calculator_recommendation.class));
            }
        });

        backDashboardRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });
    }

    private void SuggestionRule(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ_RECOMMENDATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")) {

                                for(int i=0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String user_email = object.getString("user_email").trim();
                                    String user_bmi = object.getString("user_bmi").trim();
                                    String user_age = object.getString("user_age").trim();
                                    String user_gender = object.getString("user_gender").trim();
                                    String user_act_lvl = object.getString("user_act_lvl").trim();


                                    ETBmi.setText(user_bmi);
                                    ETAge.setText(user_age);
                                    ETGender.setText(user_gender);
                                    ETActLvl.setText(user_act_lvl);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(recommendation.this, "Your Profile Cannot Be Read "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(recommendation.this, "Error Malfunction "+error.toString(), Toast.LENGTH_SHORT).show();
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



    private void getUserDetailRecommendation() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ_RECOMMENDATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")) {

                                for(int i=0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String user_email = object.getString("user_email").trim();
                                    String user_bmi = object.getString("user_bmi").trim();
                                    String user_age = object.getString("user_age").trim();
                                    String user_gender = object.getString("user_gender").trim();
                                    String user_act_lvl = object.getString("user_act_lvl").trim();


                                    ETBmi.setText(user_bmi);
                                    ETAge.setText(user_age);
                                    ETGender.setText(user_gender);
                                    ETActLvl.setText(user_act_lvl);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(recommendation.this, "Your Profile Cannot Be Read "+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(recommendation.this, "Error Malfunction "+error.toString(), Toast.LENGTH_SHORT).show();
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
    protected void onResume(){
        super.onResume();
        getUserDetailRecommendation();
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
    public void Suggest(){
       // pbSuggest.setVisibility(View.VISIBLE);
        String age = ETAge.getText().toString().trim();
        String BMI = ETBmi.getText().toString().trim();
        String Gender = ETGender.getText().toString().trim();
        String Act_Lvl = ETActLvl.getText().toString().trim();

        int user_age = Integer.valueOf(age);
        double bmi = Double.valueOf(BMI);

        if (user_age<=25 && bmi <= 28 && Gender.equals("Male") && Act_Lvl.equals("Little/no exercise")){
           // pbSuggest.setVisibility(View.GONE);
            BreakfastMeal.setText("Yogurt");
            LunchMeal.setText("Nasi Lemak");
            DinnerMeal.setText("Nasi Arab");
            SupperMeal.setText("Avocado Drink");
            MorningWorkout.setText("Walking for 30 minutes");
            EveningWorkout.setText("Squat");
            NightWorkout.setText("Lunges");

            Toast.makeText(getApplicationContext(),"Suggested! Check the result in the below",Toast.LENGTH_SHORT).show();

        } else if(user_age<=25  && bmi<=28 && Gender.equals("Male") && Act_Lvl.equals("Light exercise")){
            //pbSuggest.setVisibility(View.GONE);
            BreakfastMeal.setText("Milk");
            LunchMeal.setText("Vegan Asian Rice Salad");
            DinnerMeal.setText("Cheesy Meatball Bombs");
            SupperMeal.setText("Fruit smoothie");
            MorningWorkout.setText("walking for 45 minutes");
            EveningWorkout.setText("cycling");
            NightWorkout.setText("stretching");

            Toast.makeText(getApplicationContext(),"Suggested! Check the result in the below",Toast.LENGTH_SHORT).show();

        } else if(user_age<=12 && bmi<18.5 && Gender.equals("Male") && Act_Lvl.equals("Moderate exercise")){

            BreakfastMeal.setText("Raisin snack packs 1 small packet");
            LunchMeal.setText("Vegan Asian Rice Salad");
            DinnerMeal.setText("Cheesy Meatball Bombs");
            SupperMeal.setText("small packet nuts");
            MorningWorkout.setText("walking for 45 minutes");
            EveningWorkout.setText("cycling 30 minutes");
            NightWorkout.setText("stretching");

            Toast.makeText(getApplicationContext(),"Suggested! Check the result in the below",Toast.LENGTH_SHORT).show();

    }      else if (user_age<=12 && bmi<18.5 && Gender.equals("Male") && Act_Lvl.equals("Very Active")){

            BreakfastMeal.setText("Strawberry Spinach Salad");
            LunchMeal.setText("Chicken Noodle Soup");
            DinnerMeal.setText("Chicken Waldorf Sandwiches");
            SupperMeal.setText("Vegan Banana Muffins");
            MorningWorkout.setText("Jogging 30 minutes");
            EveningWorkout.setText("Walking 1 hour");
            NightWorkout.setText("Stretching leg");

            Toast.makeText(getApplicationContext(),"Suggested! Check the result in the below",Toast.LENGTH_SHORT).show();

        }   else if (user_age<=12 && bmi<18.5 && Gender.equals("Male") && Act_Lvl.equals("Light no/exercise")){

            BreakfastMeal.setText("Roti planta");
            LunchMeal.setText("Nasi kukus ayam");
            DinnerMeal.setText("Sayur lemak cili api");
            SupperMeal.setText("Low fat Milk");
            MorningWorkout.setText("Start Jump 50 times");
            EveningWorkout.setText("Walking ");
            NightWorkout.setText("Dumbbell");

            Toast.makeText(getApplicationContext(),"Suggested! Check the result now",Toast.LENGTH_SHORT).show();

        } else if (user_age>=13 && user_age<=18 && bmi >= 18.5 && bmi <= 24.9 && Gender.equals("Male") && Act_Lvl.equals("Light/no exercise")) {

            BreakfastMeal.setText("Cream cheese with bread");
            LunchMeal.setText("Nasi Lemak");
            DinnerMeal.setText("Sayur lemak cili api");
            SupperMeal.setText("Meatloaf muffins");
            MorningWorkout.setText("walking for 10 minutes");
            EveningWorkout.setText("Standing");
            NightWorkout.setText("stretching arms");

            Toast.makeText(getApplicationContext(),"Suggested! Check the result now",Toast.LENGTH_SHORT).show();

        } else if (user_age<=12 && user_age<=18 && bmi>=18.5 && bmi <=24.9 && Gender.equals("Female") && Act_Lvl.equals("Light exercise")) {

            BreakfastMeal.setText("Cream cheese with bread");
            LunchMeal.setText("Nasi Lemak");
            DinnerMeal.setText("Pepperoni Pizza Sliders");
            SupperMeal.setText("Meatloaf muffins");
            MorningWorkout.setText("walking for 10 minutes");
            EveningWorkout.setText("Standing");
            NightWorkout.setText("stretching arms");

            Toast.makeText(getApplicationContext(),"Suggested! Check the result now",Toast.LENGTH_SHORT).show();
        }
}
}

package com.company.dietfitstage7.OuterActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.company.dietfitstage7.Activity.CaloriesCalculatorDiary;
import com.company.dietfitstage7.BMICalculatorActivity.bmi_calculator_register;
import com.company.dietfitstage7.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerUser extends AppCompatActivity {
    private Button btnregister;
    private ImageView backRegister,ViewInfo,ViewInfoMT;
    private TextView calculate_here;
    private TextInputEditText r_email,r_username,r_pwd,r_age,r_bmi,r_cal_goals;
    private Spinner r_gender,malaysian_type,r_act_lvl;
    private ProgressBar PBRegister;
    private static String URL_REGIST = "http://192.168.100.196/Volley_Dietfit/register.php";
    private long backPressedTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        PBRegister = findViewById(R.id.progressBarRegister);
        r_email = findViewById(R.id.editEmail);
        r_username = findViewById(R.id.EditUsername);
        r_pwd = findViewById(R.id.editPassword);
        r_age = findViewById(R.id.editAge);
        r_gender = findViewById(R.id.GenderSpinner);
        r_bmi = findViewById(R.id.editBMI);
        btnregister = findViewById(R.id.buttonRegister);
        calculate_here = findViewById(R.id.BMICalculatorText);
        backRegister = findViewById(R.id.BackRegister);
        r_cal_goals = findViewById(R.id.editCaloriesGoalRegister);
        malaysian_type = findViewById(R.id.MalaysianTypeInput);
        ViewInfo = findViewById(R.id.imageViewInfoActiveLevel);
        ViewInfoMT = findViewById(R.id.imageViewInfoMalaysianType);
        r_act_lvl = findViewById(R.id.ActiveLevelSpinner);


        //MalaysianTypeDetermination();

        backRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

        ViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(registerUser.this);
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

        ViewInfoMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(registerUser.this);
                builder.setTitle("Human age Classification");
                builder.setMessage(
                        "Child : 0-12 years old" +
                                "\n\nAdolescence : 13-18 years old"+
                                "\n\nAdult : 19-59 years old"+
                                "\n\nSenior Adult : 60 years old");
                // add a button
                builder.setPositiveButton("OK", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String REmail = r_email.getText().toString().trim();
                String RPass = r_pwd.getText().toString().trim();
                String RUsername = r_username.getText().toString().trim();
                String RAge = r_age.getText().toString().trim();
                String RBmi = r_bmi.getText().toString().trim();
                String RGender = r_gender.getSelectedItem().toString().trim();
                String RCalGoals = r_cal_goals.getText().toString().trim();
                String RMT = malaysian_type.getSelectedItem().toString().trim();

                if (!REmail.isEmpty() || !RPass.isEmpty() || !RAge.isEmpty() || !RBmi.isEmpty() || !RGender.isEmpty() || !RUsername.isEmpty() || !RCalGoals.isEmpty() || !RMT.isEmpty()) {
                    Register();
                }
                else {
                    r_email.setError("Please insert email");
                    r_username.setError("Please insert username");
                    r_pwd.setError("Please insert password");
                    r_age.setError("Please insert age");
                    r_bmi.setError("Please insert bmi");
                    r_cal_goals.setError("Please insert calories goal");
                }

            }
        });

        calculate_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), bmi_calculator_register.class));
            }
        });


    }

    private void Register(){
        PBRegister.setVisibility(View.VISIBLE);
        btnregister.setVisibility(View.GONE);

        final String user_email = this.r_email.getText().toString().trim();
        final String username_user = this.r_username.getText().toString().trim();
        final String user_pwd = this.r_pwd.getText().toString().trim();
        final String user_age = this.r_age.getText().toString().trim();
        final String user_gender = this.r_gender.getSelectedItem().toString().trim();
        final String user_bmi = this.r_bmi.getText().toString().trim();
        final String calories_goal = this.r_cal_goals.getText().toString().trim();
        final String malaysian_type = this.malaysian_type.getSelectedItem().toString().trim();
        final String user_act_lvl = this.r_act_lvl.getSelectedItem().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(registerUser.this,"Register Success!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),login.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(registerUser.this,"Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                            PBRegister.setVisibility(View.GONE);
                            btnregister.setVisibility(View.VISIBLE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(registerUser.this,"Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        PBRegister.setVisibility(View.GONE);
                        btnregister.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("user_email",user_email);
                params.put("username_user",username_user);
                params.put("user_pwd",user_pwd);
                params.put("user_age",user_age);
                params.put("user_gender",user_gender);
                params.put("user_bmi",user_bmi);
                params.put("calories_goal",calories_goal);
                params.put("malaysian_type",malaysian_type);
                params.put("user_act_lvl",user_act_lvl);
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
}
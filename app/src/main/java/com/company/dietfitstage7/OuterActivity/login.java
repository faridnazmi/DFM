package com.company.dietfitstage7.OuterActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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
import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;
import com.google.android.material.textfield.TextInputEditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    private TextView register_here,forgot_password;
    private TextInputEditText email,password;
    private Button buttonLogin;
    private ProgressBar PBLogin;
    private static String URL_LOGIN = "http://192.168.100.196/Volley_Dietfit/login.php";
    SessionManagerDietfit sessionManagerDietFit;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManagerDietFit = new SessionManagerDietfit(this);

        PBLogin = findViewById(R.id.progressBarLogin);
        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        register_here = findViewById(R.id.TextRegister_here);
        register_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), registerUser.class));
                finish();
            }
        });
        buttonLogin = findViewById(R.id.bbuttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    Login(mEmail,mPass);
                }
                else {
                    email.setError("Please insert email");
                    password.setError("Please insert password");
                }
            }
        });

    }

    private void Login(String user_email, String user_pwd) {
        PBLogin.setVisibility(View.VISIBLE);
        buttonLogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success =  jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")){
                                for(int i=0; i< jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String user_email = object.getString("user_email").trim();
                                    String username_user = object.getString("username_user").trim();
                                    String user_age = object.getString("user_age").trim();

                                    Toast.makeText(login.this,"Welcome! " +username_user,Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),Dashboard.class));

                                    sessionManagerDietFit.createSession(user_email,user_age,username_user);

                                   /* Intent intent = new Intent(login.this, Dashboard.class);
                                    intent.putExtra("user_email",user_email);
                                    intent.putExtra("username_user",username_user);
                                    intent.putExtra("user_age",user_age);
                                    startActivity(intent); */

                                    PBLogin.setVisibility(View.GONE);
                                }
                            }
                            else {
                                Toast.makeText(login.this,"Invalid email or password", Toast.LENGTH_SHORT).show();
                                PBLogin.setVisibility(View.GONE);
                                buttonLogin.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            PBLogin.setVisibility(View.GONE);
                            buttonLogin.setVisibility(View.VISIBLE);
                            e.printStackTrace();
                            Toast.makeText(login.this,"User does not exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PBLogin.setVisibility(View.GONE);
                buttonLogin.setVisibility(View.VISIBLE);
                Toast.makeText(login.this,"Server Timeout ",Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map <String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("user_email", user_email);
                params.put("user_pwd",user_pwd);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
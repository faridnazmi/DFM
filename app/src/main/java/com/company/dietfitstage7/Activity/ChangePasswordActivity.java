package com.company.dietfitstage7.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.BottomNavigationActivity.Setting;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {
    ImageView BackChangePassword;
    String getEmail;
    SessionManagerDietfit sessionManagerDietfit;
    EditText EditTextNewPassword;
    Button ButtonChangePassword;
    private static String URL_CP = "http://192.168.100.196/Volley_Dietfit/change_password.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        BackChangePassword = findViewById(R.id.BackChangePassword);
        EditTextNewPassword = findViewById(R.id.NewPasswordET);
        ButtonChangePassword = findViewById(R.id.buttonChangePassword);

        BackChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Setting.class));
            }
        });

        ButtonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword();
            }
        });

        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();




        /*Intent intent = getIntent();
        String pwd = intent.getStringExtra("user_pwd");
        EditTextNewPassword.setText(pwd);*/

    }

     private void ChangePassword(){

         HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
         getEmail = user.get(sessionManagerDietfit.User_email);
         String username_user = user.get(sessionManagerDietfit.Username_user);
         String user_age = user.get(sessionManagerDietfit.User_age);

        final String user_email = getEmail;
        final String user_pwd = this.EditTextNewPassword.getText().toString().trim();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(ChangePasswordActivity.this,"Success!",Toast.LENGTH_SHORT).show();
                                sessionManagerDietfit.createSession(user_email,username_user,user_age);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(ChangePasswordActivity.this,"Error Exception"+e.toString(),Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ChangePasswordActivity.this,"Error Volley "+error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws  AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_email",user_email);
                params.put("user_pwd",user_pwd);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
package com.company.dietfitstage7.BMICalculatorActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.company.dietfitstage7.Activity.diary;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.Activity.recommendation;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class bmi_calculator_recommendation extends AppCompatActivity {
    EditText height, weight,ResultBMI;
    String calculation, result,status;
    private Button buttonCalculate,buttonUpdateBMI;
    TextView bmistatus;
    ImageView BtnbackBMI;
    private static String URL_UPDATE_BMI = "http://192.168.100.196/Volley_Dietfit/Update_bmi.php";
    private long backPressedTime;
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator_recommendation);
        weight = findViewById(R.id.inputweightRec);
        height = findViewById(R.id.inputheightRec);
        ResultBMI = findViewById(R.id.editTextResultBMICal);
        bmistatus = findViewById(R.id.textBMIStatus);
        buttonCalculate = findViewById(R.id.buttonCalculateBMIRecommendation);
        buttonUpdateBMI = findViewById(R.id.buttonUpdateBMI);

        sessionManagerDietfit = new SessionManagerDietfit(this);
        sessionManagerDietfit.checkLogin();
        ResultBMI.setFocusableInTouchMode(false);

        buttonUpdateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ResultBMI.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Calculate First!",Toast.LENGTH_SHORT).show();
                }else{
                    updateBMI();
                }

            }
        });
        BtnbackBMI = findViewById(R.id.BackBMIRecommendation);
        BtnbackBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),recommendation.class));

            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(weight.getText().toString().isEmpty()){
                    weight.setError("Weight is Required");
                    return;
                }

                else if(height.getText().toString().isEmpty()){
                    height.setError("Height is Required");
                    return;

                }
                String s1 = weight.getText().toString();
                String s2 = height.getText().toString();

                float weightvalue = Float.parseFloat(s1);
                float heightvalue = Float.parseFloat(s2) / 100;

                float bmicalculation = weightvalue / (heightvalue * heightvalue);

                if (bmicalculation < 16.0) {
                    result = "Very Under Weight!!";
                }else if (bmicalculation < 18.5) {
                    result = "Under Weight!";
                } else if (bmicalculation >= 18.5 && bmicalculation <= 24.9 ) {
                    result = "Normal";
                }else if (bmicalculation >= 25.0 && bmicalculation <= 29.9){
                    result ="Overweight!";
                } else{
                    result="Obese!!";
                }

                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                //calculation = "BMI : "  + bmicalculation + " " + "(" +result + ")";
                String bmi = decimalFormat.format(bmicalculation);
                status = "Your BMI Status are " + result;
                ResultBMI.setText(bmi);
                bmistatus.setText(status);

            }
        });

    }

    private void updateBMI() {
        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
        getEmail = user.get(sessionManagerDietfit.User_email);

        final String user_bmi = ResultBMI.getText().toString().trim();
        final String user_email = getEmail;
        String username_user = user.get(sessionManagerDietfit.Username_user);
        String user_age = user.get(sessionManagerDietfit.User_age);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_BMI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(bmi_calculator_recommendation.this,"Updated!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),recommendation.class));
                                sessionManagerDietfit.createSession(user_email,username_user,user_age);
                            }
                            else{
                                Toast.makeText(bmi_calculator_recommendation.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(bmi_calculator_recommendation.this,"Cannot Update try again later", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(bmi_calculator_recommendation.this,"Error", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_email",user_email);
                params.put("user_bmi",user_bmi);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    /*
@Override
protected void onResume(){
        super.onResume();

} */



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
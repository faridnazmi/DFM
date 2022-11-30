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

import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.R;

import java.text.DecimalFormat;

public class bmi_calculator extends AppCompatActivity {
    EditText height, weight, bmi;
    String calculation, result;
    TextView ResultBMI,StatusBMI;
    Button buttonCalculate;
    ImageView backDashboardBMI;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);
        weight = findViewById(R.id.inputweight);
        height = findViewById(R.id.inputheight);
        ResultBMI = findViewById(R.id.textBMI);
        buttonCalculate = findViewById(R.id.button);
        StatusBMI = findViewById(R.id.textBMIStatusBMICal);

        backDashboardBMI = findViewById(R.id.BackBMI);

        backDashboardBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
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
                //calculation = bmicalculation + " " + "( " +result + " )";
                String bmi = decimalFormat.format(bmicalculation);
                String statusbmi = "Your BMI Status are " + result;
                ResultBMI.setText(bmi);
                StatusBMI.setText(statusbmi);
            }
        });

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
    /*
@Override
protected void onResume(){
        super.onResume();

} */

}
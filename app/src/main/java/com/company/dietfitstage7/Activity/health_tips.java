package com.company.dietfitstage7.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.Adapter.HealthTipsAdapter;
import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.Data.HealthTipsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class health_tips extends AppCompatActivity {
    private RecyclerView recyclerView;
    private com.company.dietfitstage7.Adapter.HealthTipsAdapter HealthTipsAdapter;
    private List<HealthTipsData> healthtipslist;
    ImageView backDashboardHealthTips;
    private long backPressedTime;
    private static String URL_READ_HT = "http://192.168.100.196/Volley_Dietfit/View_health_tips.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);
        backDashboardHealthTips = findViewById(R.id.BackHealth);

        backDashboardHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });

        recyclerView = findViewById(R.id.recyclerviewHealthTips);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        healthtipslist = new ArrayList<>();
        LoadAllHealthTips();

    }

    private void LoadAllHealthTips(){

        JsonArrayRequest request = new JsonArrayRequest(URL_READ_HT, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i< response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String tips_id = object.getString("tips_id").trim();
                        String tips_name = object.getString("tips_name").trim();
                        String tips_type = object.getString("tips_type").trim();
                        String tips_desc = object.getString("tips_desc").trim();
                        String tips_reference = object.getString("tips_reference").trim();

                        HealthTipsData HealthData = new HealthTipsData();
                        HealthData.setTipsId(tips_id);
                        HealthData.setTipsName(tips_name);
                        HealthData.setTipsType(tips_type);
                        HealthData.setTipsDesc(tips_desc);
                        HealthData.setTipsReference(tips_reference);
                        healthtipslist.add(HealthData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                HealthTipsAdapter = new HealthTipsAdapter(health_tips.this,healthtipslist);
                recyclerView.setAdapter(HealthTipsAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(health_tips.this, error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(health_tips.this);
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
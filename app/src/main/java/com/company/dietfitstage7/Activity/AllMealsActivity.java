package com.company.dietfitstage7.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.Adapter.AllMealsAdapter;
import com.company.dietfitstage7.Adapter.ExercisesRecordAdapter;
import com.company.dietfitstage7.BottomNavigationActivity.Dashboard;
import com.company.dietfitstage7.Data.AllMealsData;
import com.company.dietfitstage7.Data.ExerciseData;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllMealsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAllMeals;
     private AllMealsAdapter AllMealsAdapter;
     private List<AllMealsData> AllMealslist;
     CharSequence search="";
     EditText searchViewMeals;

     private static String URL_AllMeals="http://192.168.100.196/Volley_Dietfit/View_all_meals.php";
    ImageView backDashboardMeals;
    private long backPressedTime;
    SessionManagerDietfit sessionManagerDietfit;
    String getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        backDashboardMeals = findViewById(R.id.BackMeals);
        searchViewMeals = findViewById(R.id.searchViewMeals);


        recyclerViewAllMeals = findViewById(R.id.recyclerviewAllMeals);
        recyclerViewAllMeals.setHasFixedSize(true);
        recyclerViewAllMeals.setLayoutManager(new LinearLayoutManager(this));
        AllMealslist = new ArrayList<>();
        LoadAllMeals();

        backDashboardMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });
        searchViewMeals.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    AllMealsAdapter.getFilter().filter(s);
                    search = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void LoadAllMeals() {
        JsonArrayRequest request = new JsonArrayRequest(URL_AllMeals, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i< response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        String meals_name = object.getString("meals_name").trim();
                        String food_type = object.getString("food_type").trim();
                        String calories_info = object.getString("calories_info").trim();

                        AllMealsData mealsData = new AllMealsData();
                        mealsData.setMeals_name(meals_name);
                        mealsData.setFood_type(food_type);
                        mealsData.setCalories_info(calories_info);
                        AllMealslist.add(mealsData);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                AllMealsAdapter = new AllMealsAdapter(AllMealsActivity.this,AllMealslist);
                recyclerViewAllMeals.setAdapter(AllMealsAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AllMealsActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(AllMealsActivity.this);
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
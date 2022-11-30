package com.company.dietfitstage7;

import com.company.dietfitstage7.Data.AllMealsData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("getMeals.php")
    Call<List<AllMealsData>> getMeals (@Query("a") String keyword);


}

package com.company.dietfitstage7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.dietfitstage7.R;
import com.company.dietfitstage7.Data.MealData;

import java.util.List;

public class MealsRecordAdapter  extends  RecyclerView.Adapter<MealsRecordAdapter.MealsHolder>{
    Context context;
    List<MealData> mealslist;

    public MealsRecordAdapter(Context context,List<MealData> mealslist){
        this.context = context;
        this.mealslist = mealslist;
    }
    @NonNull
    @Override
    public MealsHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View mealsLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_meals_view,parent,false);
        return new MealsHolder(mealsLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsRecordAdapter.MealsHolder holder, int position) {
        MealData meals = mealslist.get(position);
        holder.meals_name.setText(meals.getMeals_name());
        //holder.meals_new.setText(meals.getMeals_new());
        holder.meals_note.setText(meals.getMeals_note());
        holder.diary_date.setText(meals.getDiary_date());
        holder.diary_id.setText(meals.getDiary_id());
        holder.meals_cal_info.setText(meals.getMeals_cal_info());
    }

    @Override
    public int getItemCount() {
        return mealslist.size();
    }

    public class MealsHolder extends RecyclerView.ViewHolder {
        TextView meals_name,meals_note,diary_date,diary_id,meals_cal_info;
        //TextView meals_name,meals_new,meals_note,diary_date,diary_id;
        public MealsHolder(@NonNull View itemView) {
            super(itemView);
            meals_name = itemView.findViewById(R.id.recyclerMealsName);
            //meals_new = itemView.findViewById(R.id.recyclerMealNew);
            meals_note = itemView.findViewById(R.id.recyclerMealNoteMeals);
            diary_date = itemView.findViewById(R.id.recyclerMealsDate);
            diary_id = itemView.findViewById(R.id.recyclerMealsDiaryId);
            meals_cal_info = itemView.findViewById(R.id.recyclerMealsCalInfoMeals);

        }
    }
}

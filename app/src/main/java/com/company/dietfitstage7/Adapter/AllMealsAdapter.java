package com.company.dietfitstage7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.dietfitstage7.Activity.AllMealsActivity;
import com.company.dietfitstage7.Data.AllMealsData;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.Data.MealData;

import java.util.ArrayList;
import java.util.List;

public class AllMealsAdapter extends RecyclerView.Adapter<AllMealsAdapter.AllMealsHolder>{
   private Context context;
   private List<AllMealsData> Allmealslist;
   private List<AllMealsData> filteredMealsList;

    public AllMealsAdapter(Context context,List<AllMealsData> Allmealslist){
        this.context = context;
        this.Allmealslist = Allmealslist;
        this.filteredMealsList = Allmealslist;

    }
    @NonNull
    @Override
      public AllMealsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View AllmealsLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_meals,parent,false);
        return new AllMealsHolder(AllmealsLayout);
    }

    @Override
    public void onBindViewHolder(AllMealsAdapter.AllMealsHolder holder, int position) {
        AllMealsData Allmeals = filteredMealsList.get(position);
        holder.meals_name.setText(Allmeals.getMeals_name());
        holder.food_type.setText(Allmeals.getFood_type());
        holder.calories_info.setText(Allmeals.getCalories_info());

    }

    @Override
    public int getItemCount() {
        return filteredMealsList.size();
    }


    public class AllMealsHolder extends RecyclerView.ViewHolder {
        TextView meals_name,food_type,calories_info;
        public AllMealsHolder(@NonNull View itemView) {
            super(itemView);
            meals_name = itemView.findViewById(R.id.recyclerAllMealsName);
            food_type = itemView.findViewById(R.id.recyclerMealFoodType);
            calories_info = itemView.findViewById(R.id.recyclerCaloriesInfo);


        }
    }
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String key = constraint.toString();
                if(key.isEmpty()){
                    filteredMealsList = Allmealslist;
                }
                else{
                    List<AllMealsData> listFiltered = new ArrayList<>();
                    for(AllMealsData row: Allmealslist){
                        if(row.getMeals_name().toLowerCase().contains(key.toLowerCase())){
                            listFiltered.add(row);
                        }else{

                        }
                    }
                    filteredMealsList = listFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredMealsList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredMealsList = (List<AllMealsData>)results.values;
                    notifyDataSetChanged();
            }
        };
    }
}

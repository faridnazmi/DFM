package com.company.dietfitstage7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.company.dietfitstage7.Data.AllExerciseData;
import com.company.dietfitstage7.Data.AllMealsData;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.Data.MealData;

import java.util.ArrayList;
import java.util.List;

public class AllExerciseAdapter extends RecyclerView.Adapter<AllExerciseAdapter.AllExerciseHolder>{
    Context context;
    List<AllExerciseData> AllExerciseslist;
    List<AllExerciseData> FilterAllExercise;

    public AllExerciseAdapter(Context context,List<AllExerciseData> AllExerciseslist){
        this.context = context;
        this.AllExerciseslist = AllExerciseslist;
        this.FilterAllExercise = AllExerciseslist;
    }
    @NonNull
    @Override
    public AllExerciseHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View AllExercisesLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_all_exercise,parent,false);
        return new AllExerciseHolder(AllExercisesLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull AllExerciseAdapter.AllExerciseHolder holder, int position) {
        AllExerciseData AllExercise = FilterAllExercise.get(position);
        holder.ex_name.setText(AllExercise.getEx_name());
        holder.ex_type.setText(AllExercise.getEx_type());
        holder.calories_info.setText(AllExercise.getCalories_info());
        holder.ex_time_taken.setText(AllExercise.getEx_time_taken());
    }

    @Override
    public int getItemCount() {
        return FilterAllExercise.size();
    }

    public class AllExerciseHolder extends RecyclerView.ViewHolder {
        TextView ex_name,ex_type,calories_info,ex_time_taken;
        public AllExerciseHolder(@NonNull View itemView) {
            super(itemView);
            ex_name = itemView.findViewById(R.id.recyclerAllExercisesName);
            ex_type = itemView.findViewById(R.id.recyclerExercisesType);
            calories_info = itemView.findViewById(R.id.recyclerExCaloriesInfo);
            ex_time_taken = itemView.findViewById(R.id.recyclerExTimeTaken);
        }
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String key = constraint.toString();
                if(key.isEmpty()){
                    FilterAllExercise = AllExerciseslist;
                }
                else{
                    List<AllExerciseData> listFiltered = new ArrayList<>();
                    for(AllExerciseData row: AllExerciseslist){
                        if(row.getEx_name().toLowerCase().contains(key.toLowerCase())){
                            listFiltered.add(row);
                        }else{

                        }
                    }
                    FilterAllExercise = listFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = FilterAllExercise;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                FilterAllExercise = (List<AllExerciseData>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}

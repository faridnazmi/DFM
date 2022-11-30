package com.company.dietfitstage7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.dietfitstage7.R;
import com.company.dietfitstage7.Data.ExerciseData;

import java.util.List;

public class ExercisesRecordAdapter  extends  RecyclerView.Adapter<ExercisesRecordAdapter.ExercisesHolder>{
    Context context;
    List<ExerciseData> exerciseslist;

    public ExercisesRecordAdapter(Context context,List<ExerciseData> exerciseslist){
        this.context = context;
        this.exerciseslist = exerciseslist;
    }
    @NonNull
    @Override
    public ExercisesHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View exercisesLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_exercises_view,parent,false);
        return new ExercisesHolder(exercisesLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesRecordAdapter.ExercisesHolder holder, int position) {
        ExerciseData exercises = exerciseslist.get(position);
        holder.ex_name.setText(exercises.getEx_name());
        //holder.ex_new.setText(exercises.getEx_new());
        holder.ex_note.setText(exercises.getEx_note());
        holder.diary_id.setText(exercises.getDiary_id());
        holder.diary_date.setText(exercises.getDiary_date());
        holder.ex_time_taken.setText(exercises.getEx_time_taken());
        holder.ex_cal_info.setText(exercises.getEx_cal_info());
    }

    @Override
    public int getItemCount() {
        return exerciseslist.size();
    }

    public class ExercisesHolder extends RecyclerView.ViewHolder {
        TextView ex_name,ex_note,ex_time_taken,ex_cal_info,diary_id,diary_date;
        //TextView ex_name,ex_new,ex_note,diary_id,diary_date;
        public ExercisesHolder(@NonNull View itemView) {
            super(itemView);
            ex_name = itemView.findViewById(R.id.recyclerExercisesName);
            //ex_new = itemView.findViewById(R.id.recyclerExercisesNew);
            ex_note = itemView.findViewById(R.id.recyclerExercisesNoteExercise);
            diary_id = itemView.findViewById(R.id.recyclerExercisesDiaryId);
            diary_date = itemView.findViewById(R.id.recyclerExercisesDate);
            ex_cal_info = itemView.findViewById(R.id.recyclerExercisesCalInfoExercise);
            ex_time_taken = itemView.findViewById(R.id.recyclerExercisesTimeTakenView);



        }
    }
}

package com.company.dietfitstage7.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.dietfitstage7.R;
import com.company.dietfitstage7.SessionManager.SessionManagerDietfit;
import com.company.dietfitstage7.Data.DiaryData;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiaryRecordAdapter  extends  RecyclerView.Adapter<DiaryRecordAdapter.DiaryHolder>{
    Context context;
    List<DiaryData> diarylist;
    private String URL_DIARY_EDIT = "http://192.168.100.196/Volley_Dietfit/edit_diary.php";
    private String URL_DELETE = "http://192.168.100.196/Volley_Dietfit/Delete_diary.php";
    String getEmail;
    SessionManagerDietfit sessionManagerDietfit;
    public DiaryRecordAdapter(Context context,List<DiaryData> diarylist){
        this.context = context;
        this.diarylist = diarylist;
    }
    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View diaryLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_diary_view,parent,false);
        return new DiaryHolder(diaryLayout);
    }
    @Override
    public void onBindViewHolder(@NonNull DiaryRecordAdapter.DiaryHolder holder, int position) {
        DiaryData diary = diarylist.get(position);
        holder.diary_id.setText(diary.getDiary_id());
        holder.meals_name.setText(diary.getMeals_name());
        holder.ex_name.setText(diary.getEx_name());
        //holder.meals_new.setText(diary.getMeals_new());
        holder.meals_note.setText(diary.getMeals_note());
        //holder.ex_new.setText(diary.getEx_new());
        holder.meals_cal_info.setText(diary.getMeals_cal_info());
        holder.ex_cal_info.setText(diary.getEx_cal_info());
        holder.ex_time_taken.setText(diary.getEx_time_taken());
        holder.ex_note.setText(diary.getEx_note());
        holder.diary_date.setText(diary.getDiary_date());
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SessionManagerDietfit sessionManagerDietfit = new SessionManagerDietfit(context);
                sessionManagerDietfit.checkLogin();

                View editLayout = LayoutInflater.from(context).inflate(R.layout.edit_diary, null);
                EditText meals_note = editLayout.findViewById(R.id.MealsNoteEdit);
                EditText ex_note = editLayout.findViewById(R.id.ExNoteEdit);
                TextView diary_id = editLayout.findViewById(R.id.textViewDiaryId);
                EditText meals_cal_info = editLayout.findViewById(R.id.MealsCalInfoEdit);
                EditText ex_cal_info = editLayout.findViewById(R.id.exCalInfoEdit);
                EditText ex_time_taken = editLayout.findViewById(R.id.exTimeTakenEdit);

                meals_note.setText(diary.getMeals_note());
                ex_note.setText(diary.getEx_note());
                meals_cal_info.setText(diary.getMeals_cal_info());
                ex_cal_info.setText(diary.getEx_cal_info());
                ex_time_taken.setText(diary.getEx_time_taken());
                diary_id.setText(diary.getDiary_id());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(editLayout);
                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final String Meals_note = meals_note.getText().toString();
                        final String Ex_note = ex_note.getText().toString();
                        final String Diary_id = diary_id.getText().toString();
                        final String Meals_cal_info = meals_cal_info.getText().toString();
                        final String Ex_cal_info = ex_cal_info.getText().toString();
                        final String Ex_time_taken = ex_time_taken.getText().toString();

                        HashMap<String, String > user = sessionManagerDietfit.getUserDetail();
                        getEmail = user.get(sessionManagerDietfit.User_email);

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DIARY_EDIT, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String, String> params = new HashMap<>();
                                    params.put("user_email", getEmail);
                                    params.put("meals_note", Meals_note);
                                    params.put("meals_cal_info", Meals_cal_info);
                                    params.put("ex_cal_info", Ex_cal_info);
                                    params.put("ex_time_taken", Ex_time_taken);
                                    params.put("ex_note", Ex_note);
                                    params.put("diary_id", Diary_id);
                                    return params;
                                }
                            };
                            RequestQueue queue = Volley.newRequestQueue(context);
                            queue.add(stringRequest);
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
        }
    });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("DELETE DIARY");
                builder.setMessage("Confirm to Delete "+ diary.getDiary_id());
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringRequest request = new StringRequest(Request.Method.POST, URL_DELETE,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject object = new JSONObject(response);
                                            String check = object.getString("state");
                                            if(check.equals("delete")){
                                                Delete(position);
                                                Toast.makeText(context,"Delete Successful",Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(context,response,Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> deleteParams = new HashMap<>();
                                deleteParams.put("diary_id",diary.getDiary_id());
                                return deleteParams;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        requestQueue.add(request);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return diarylist.size();
    }

    public class DiaryHolder extends RecyclerView.ViewHolder {
        TextView ex_name,ex_note,diary_id,diary_date,meals_name,meals_note,meals_cal_info,ex_cal_info,ex_time_taken;
        ImageButton editButton,deleteButton;
        public DiaryHolder(@NonNull View itemView) {
            super(itemView);
            meals_note = itemView.findViewById(R.id.recyclerDiaryMealsNote);
            diary_id = itemView.findViewById(R.id.recyclerDiaryIdDiary);
            meals_name = itemView.findViewById(R.id.recyclerDiaryMealsName);
            meals_cal_info = itemView.findViewById(R.id.recyclerDiaryMealsCalInfo);
            ex_cal_info = itemView.findViewById(R.id.recyclerDiaryExCalInfo);
            ex_time_taken = itemView.findViewById(R.id.recyclerDiaryExTimeTaken);
            ex_name = itemView.findViewById(R.id.recyclerDiaryExName);
            meals_note = itemView.findViewById(R.id.recyclerDiaryMealsNote);
            ex_note = itemView.findViewById(R.id.recyclerDiaryExNote);
            diary_date = itemView.findViewById(R.id.recyclerDiaryDate);
            editButton = itemView.findViewById(R.id.buttonEditRecyclerViewDiary);
            deleteButton = itemView.findViewById(R.id.buttonDeleteRecyclerViewDiary);
        }
    }
    public void Delete(int item) {
        diarylist.remove(item);
        notifyItemRemoved(item);
    }

}

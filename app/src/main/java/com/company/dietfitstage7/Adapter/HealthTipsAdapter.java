package com.company.dietfitstage7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.dietfitstage7.R;
import com.company.dietfitstage7.Data.HealthTipsData;

import java.util.List;

public class HealthTipsAdapter  extends  RecyclerView.Adapter<HealthTipsAdapter.HealthTipsHolder>{
    Context context;
    List<HealthTipsData> healthTipsDatalist;

    public HealthTipsAdapter(Context context,List<HealthTipsData> healthtipslist){
        this.context = context;
        this.healthTipsDatalist = healthtipslist;
    }
    @NonNull
    @Override
    public HealthTipsHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View HTLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_health_tips,parent,false);
        return new HealthTipsHolder(HTLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthTipsAdapter.HealthTipsHolder holder, int position) {
        HealthTipsData ht = healthTipsDatalist.get(position);
        holder.tips_id.setText(ht.getTipsId());
        holder.tips_name.setText(ht.getTipsName());
        holder.tips_type.setText(ht.getTipsType());
        holder.tips_desc.setText(ht.getTipsDesc());
        holder.tips_reference.setText(ht.getTipsReference());
    }

    @Override
    public int getItemCount() {
        return healthTipsDatalist.size();
    }

    public class HealthTipsHolder extends RecyclerView.ViewHolder {
        TextView tips_name,tips_type,tips_id,tips_desc,tips_reference;
        public HealthTipsHolder(@NonNull View itemView) {
            super(itemView);
            tips_id = itemView.findViewById(R.id.recyclerHealthTipsId);
            tips_name = itemView.findViewById(R.id.recyclerHealthTipsName);
            tips_type = itemView.findViewById(R.id.recyclerHealthTipsType);
            tips_desc = itemView.findViewById(R.id.recyclerHealthTipsDesc);
            tips_reference = itemView.findViewById(R.id.recyclerHealthTipsReferences);

        }
    }
}

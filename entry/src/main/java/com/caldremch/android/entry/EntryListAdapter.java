package com.caldremch.android.entry;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;

import com.caldremch.android.annotation.entry.IEntry;

import java.util.List;

/**
 * @author Caldremch
 * @date 2021/12/20
 * @email caldremch@163.com
 * @describe
 **/
@RestrictTo(RestrictTo.Scope.LIBRARY)
class EntryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int dp16;

    private List<IEntry> data;
    private Context context;

     static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public EntryListAdapter(Context context,  List<IEntry> data) {
        this.context = context;
        dp16 = dp2px(context, 16f);
        this.data = data;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView tv = new TextView(parent.getContext());
        tv.setPadding(dp16, dp16, dp16, dp16);
        tv.setBackgroundColor(Color.parseColor("#257BF4"));
        tv.setTextColor(Color.WHITE);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(tv) {
        };
        tv.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            data.get(pos).onClick(v.getContext());
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(data.get(position).getTitle());
    }

    public void setList(List<IEntry> data){
         this.data = data;
         notifyDataSetChanged();
    }

}

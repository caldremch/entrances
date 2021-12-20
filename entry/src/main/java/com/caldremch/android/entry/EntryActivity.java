package com.caldremch.android.entry;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.caldremch.android.annotation.EntryConst;
import com.caldremch.android.annotation.entry.IEntry;
import com.caldremch.android.annotation.entry.IEntryCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caldremch
 * @date 2020-08-23
 * @email caldremch@163.com
 * @describe entry list Activity
 **/
public final class EntryActivity extends AppCompatActivity {

    private RecyclerView listView;
    private int dp16;

    private int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dp16 = dp2px(16f);
        listView = new RecyclerView(this);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top  = dp16;
            }
        });
        listView.setPadding(dp16, dp16, dp16, dp16);
        setContentView(listView);
        initView();
    }

    private void initView() {
        EntryListAdapter adapter = new EntryListAdapter(getEntryList());
        listView.setAdapter(adapter);
    }

    /**
     * load entry from generate collection class
     *
     * @return list
     */
    private List<IEntry> getEntryList() {
        List<IEntry> list = new ArrayList<>();
        try {
            Class<?> clz = Class.forName(EntryConst.ENTRY_NAME_SIGN);
            Object entryCollection = clz.newInstance();
            if (entryCollection instanceof IEntryCollection) {
                ((IEntryCollection) entryCollection).load(list);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    private class EntryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<IEntry> data;

        public EntryListAdapter(List<IEntry> data) {
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
            RecyclerView.ViewHolder holder =   new RecyclerView.ViewHolder(tv) {
            };
            tv.setOnClickListener(v -> {
               int pos =  holder.getAdapterPosition();
               data.get(pos).onClick(v.getContext());
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((TextView)holder.itemView).setText(data.get(position).getTitle());
        }

    }
}

package com.caldremch.android.entry;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;

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

    private ListView listView;
    private int dp16;

    private int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dp16 = dp2px(16f);
        listView = new ListView(this);
        listView.setPadding(dp16, dp16, dp16, dp16);
        listView.setDivider(new ColorDrawable(Color.TRANSPARENT));
        listView.setDividerHeight(dp16);
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
    private class EntryListAdapter extends BaseAdapter {

        private List<IEntry> data;

        public EntryListAdapter(List<IEntry> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        private TextView createView(Context context) {
            TextView tv = new TextView(context);
            tv.setPadding(dp16, dp16, dp16, dp16);
            tv.setBackgroundColor(Color.BLUE);
            tv.setTextColor(Color.WHITE);
            return tv;
        }

        @Override
        public View getView(int i, View view, final ViewGroup parent) {
            final IEntry entry = data.get(i);
            ViewHolder holder;
            if (view == null) {
                TextView tv = createView(parent.getContext());
                ListView.MarginLayoutParams layoutParams = new ListView.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tv.setLayoutParams(layoutParams);
                view = tv;
                holder = new ViewHolder();
                holder.tv = tv;
                view.setTag(holder);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        entry.onClick(parent.getContext());
                    }
                });
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.tv.setText(entry.getTitle());
            return view;
        }

        private class ViewHolder {
            TextView tv;
        }
    }
}

package com.caldremch.android.entry;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.caldremch.android.annotation.EntryConst;
import com.caldremch.android.annotation.entry.IEntry;
import com.caldremch.android.annotation.entry.IEntryCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caldremch
 * @date 2021/12/20
 * @email caldremch@163.com
 * @describe
 **/
public class EntryRecyclerView extends RecyclerView {

    private int dp16;
    private EntryListAdapter adapter;
    private boolean empty = false;

    public EntryRecyclerView(@NonNull Context context, boolean empty) {
        super(context);
        this.empty = empty;
        init();
    }

    public EntryRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public EntryRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        extracted(context, attrs);
        init();

    }

    private void extracted(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedValue = context.obtainStyledAttributes(attrs, R.styleable.EntryRecyclerView);
        empty = typedValue.getBoolean(R.styleable.EntryRecyclerView_empty, false);
        typedValue.recycle();
    }

    public EntryRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extracted(context, attrs);
        init();

    }

    private void init() {
        dp16 = EntryListAdapter.dp2px(getContext(), 16);
        setLayoutManager(new LinearLayoutManager(getContext()));
        addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = dp16;
            }
        });
        setPadding(dp16, dp16, dp16, dp16);
        adapter = new EntryListAdapter(getContext(), empty ? new ArrayList<>() : getEntryList());
        setAdapter(adapter);
    }

    public void setList(List<IEntry> data) {
        adapter.setList(data);
    }

    public void addEntry(IEntry data) {
        adapter.addEntry(data);
    }

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

}
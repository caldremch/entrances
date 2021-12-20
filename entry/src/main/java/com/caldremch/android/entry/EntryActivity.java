package com.caldremch.android.entry;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView listView = new EntryRecyclerView(this);
        setContentView(listView);
    }
}

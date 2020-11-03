package com.caldremch.android.annotation.entry;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * @author Caldremch
 * @date 2020-08-23
 * @email caldremch@163.com
 * @describe entry should implementation of
 **/
public interface IEntry {

    String getTitle();

    void onClick(@NonNull Context context, @NonNull View itemView);
}

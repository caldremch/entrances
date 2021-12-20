package com.caldremch.entry.demo

import android.content.Context
import com.caldremch.android.annotation.entry.Entry
import com.caldremch.android.annotation.entry.IEntry

/**
 *
 * @author Caldremch
 *
 * @date 2020-08-27 11:18
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/
@Entry
class DEntry : IEntry{

    override fun getTitle(): String {
        return "自定义View展示"
    }

    override fun onClick(context: Context) {
    }
}
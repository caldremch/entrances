package com.caldremch.entry.demo

import android.content.Context
import com.caldremch.android.annotation.entry.Entry
import com.caldremch.android.annotation.entry.IEntry

//import com.caldremch.android.annotation.entry.IEntry

/**
 *
 * @author Caldremch
 *
 * @date 2020-08-25 13:29
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/
@Entry
class One : IEntry {
    override fun getTitle(): String {
        return "第一个入口"
    }

    override fun onClick(context: Context?) {

    }
}
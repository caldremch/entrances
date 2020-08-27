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
class BEntry : IEntry{
    override fun getTitle(): String {
        return "BEntry"
    }

    override fun onClick(context: Context?) {
    }
}
package com.caldremch.entry.demo

import android.content.Context
import android.widget.Toast
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
class AEntry : IEntry{
    override fun getTitle(): String {
        return "AEntry"
    }

    override fun onClick(context: Context?) {
        Toast.makeText(context, "what", Toast.LENGTH_SHORT).show()
    }
}
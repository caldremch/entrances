package com.caldremch.entry.demo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.caldremch.android.annotation.entry.IEntry
import com.caldremch.android.entry.EntryRecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv: EntryRecyclerView = findViewById<EntryRecyclerView>(R.id.entryListView)

        //add extra entry by code
        rv.addEntry(object : IEntry {
            override fun getTitle(): String {
                return "add extra by code"
            }
            override fun onClick(context: Context) {
                Toast.makeText(this@MainActivity, title, Toast.LENGTH_SHORT).show()
            }
        })

        // set new list demo
//        val newList = mutableListOf<IEntry>()
//        newList.add(object : IEntry {
//            override fun getTitle(): String {
//                return "add by myself"
//            }
//
//            override fun onClick(context: Context) {
//                Toast.makeText(this@MainActivity, title, Toast.LENGTH_SHORT).show()
//            }
//        })
//        rv.setList(newList)


    }
}
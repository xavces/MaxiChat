package com.project.maxichat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>
    private var dataset: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        for (i in 1..20) {
            dataset.add("Welcome to $i")
        }
        recyclerLayoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerAdapter = MainAdapter(dataset)
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerView.adapter = recyclerAdapter
    }

}

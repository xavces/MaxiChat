package com.project.maxichat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.JsonWriter
import com.beust.klaxon.Klaxon
import com.beust.klaxon.json

class MessageActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>
    private var dataset: ArrayList<Message> = ArrayList<Message>()

    class Message(var message:String, var created_at:String, var group_id: Int, var sender_id: Int){}

    class User(var id_user: Int){}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        recyclerView = findViewById(R.id.recyclerView)

        var user = User(2)

        for (i in 1..20) {
            dataset.add(Message(message = "Test message $i", created_at = "12:00", group_id = 1, sender_id = 1))
        }

        recyclerLayoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerAdapter = MessageAdapter(dataset, user)
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerView.adapter = recyclerAdapter
        recyclerView.smoothScrollToPosition(recyclerAdapter.itemCount - 1)

    }
}

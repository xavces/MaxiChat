package com.project.maxichat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MessageAdapter(var dataset: ArrayList<MessageActivity.Message>, var user: MessageActivity.User) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var view : View? = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        val myView: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_message, parent, false)
        return ViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {

        var title: TextView = holder.view!!.findViewById(R.id.message)
        var created_at: TextView = holder.view!!.findViewById(R.id.time)
        title.text = dataset[position].message.toString()
        created_at.text = dataset[position].created_at.toString()
    }
}
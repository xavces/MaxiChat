package com.project.maxichat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainAdapter(var dataset: ArrayList<String>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView!!.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val myView: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return ViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.title.text = dataset[position]
    }
}
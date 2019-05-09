package com.project.maxichat.Util

import android.os.Parcelable
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.project.maxichat.R
import com.project.maxichat.routes.Message
import kotlinx.android.synthetic.main.item_layout.view.*

class AdapterMessage(private val datasource: MutableList<Message>): RecyclerView.Adapter<AdapterMessage.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.my_text_view.text = datasource.get(position).message
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        internal var my_text_view : TextView

        init {
            my_text_view = itemView.findViewById(R.id.text_txt) as TextView
        }
    }

    fun insertItem(newList: List<Message>){
        val diffUtilCallback = DiffUtilCallback(datasource, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        datasource.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateItem(newList: List<Message>){
        val diffUtilCallback = DiffUtilCallback(datasource, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        datasource.clear() //clea old data
        datasource.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

}
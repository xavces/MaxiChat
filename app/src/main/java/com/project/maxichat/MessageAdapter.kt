package com.project.maxichat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MessageAdapter(var dataset: ArrayList<MessageActivity.Message>, var user: MessageActivity.User) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        //var title: TextView = itemView!!.findViewById(R.id.message)
        //var created_at: TextView = itemView!!.findViewById(R.id.textView3)

        /*if(sender_id == user.id){

        }else{

        }*/
        var view : View? = itemView
        //var view1 : View? = itemView1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        val myView: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_message, parent, false)
        //val myView1: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_message_from, parent, false)
        //return ViewHolder(myView, myView1)
        return ViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {

        var title: TextView
        var created_at: TextView
        title = holder.view!!.findViewById(R.id.message)
        created_at = holder.view!!.findViewById(R.id.time)

        /*if(dataset[position].sender_id.toString() == user.id_user.toString()){
            title = holder.view1!!.findViewById(R.id.message)
            created_at = holder.view1!!.findViewById(R.id.time)
        }else{
            title = holder.view1!!.findViewById(R.id.message)
            created_at = holder.view1!!.findViewById(R.id.time)
        }*/

        title.text = dataset[position].message.toString()
        created_at.text = dataset[position].created_at.toString()
    }
}
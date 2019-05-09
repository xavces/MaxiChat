package com.project.maxichat

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.project.maxichat.dto.MessageService
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.project.maxichat.routes.Message
import kotlinx.android.synthetic.main.sendtest.*

class sendtest: AppCompatActivity(), AnkoLogger {

    private val url = "http://10.13.100.9:3000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sendtest)

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MessageService::class.java)
        val messageListAll = service.listMessageByGroup(1)

        messageListAll.enqueue(object : Callback<List<Message>> {
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                val allCourse = response.body()
                if (allCourse != null) {
                    info("Tous les users:")
                    for (c in allCourse) {
                        if(c.sender_id == 2){
                            val tv_dynamic = TextView(this@sendtest)
                            tv_dynamic.textSize = 20f
                            tv_dynamic.text = c.message
                            tv_dynamic.setTextColor(Color.GREEN)
                            linearLayout_main.addView(tv_dynamic)
                            info("senderID : " + c.sender_id)
                        } else {
                            val tv_dynamic = TextView(this@sendtest)
                            tv_dynamic.textSize = 20f
                            tv_dynamic.text = c.message
                            tv_dynamic.setTextColor(Color.RED)
                            linearLayout_main.addView(tv_dynamic)
                            info("senderID-else : " + c.sender_id)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Log.e("MainActiviy","error",t)
                error("KO")
            }
        })




        btnSendMessage.setOnClickListener {

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(MessageService::class.java)

            val newMessage = editText.getText().toString()
            val groupId = 1
            val senderId = 2

            val newMessageFromService = Message(message = newMessage, group_id = groupId, sender_id = senderId)
            val createNewMessage = service.createMessage(newMessageFromService)

            createNewMessage.enqueue(object : Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    val allCourse = response.body()
                    if (allCourse != null) {
                        info("Hello ${allCourse.message} / ${allCourse.sender_id} ")

                        val tv_dynamic = TextView(this@sendtest)
                        tv_dynamic.textSize = 20f
                        tv_dynamic.text = editText.getText().toString()
                        info("Send")
                        linearLayout_main.addView(tv_dynamic)
                    }
                }
                override fun onFailure(call: Call<Message>, t: Throwable) {
                    Log.e("MainActiviy","error",t)
                    error("KO")
                }
            })

        }
    }

}
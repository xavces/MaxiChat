package com.project.maxichat

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
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
import com.project.maxichat.Util.AdapterMessage
import java.net.ConnectException
import java.util.*
import kotlin.collections.ArrayList


class sendtest: AppCompatActivity(), AnkoLogger {

    private val url = "http://10.13.100.5:3000/"
    internal var dataSource : MutableList<Message> = ArrayList()

    //val profileName=intent.getStringExtra("Username")
    val profileName="Roberto Carlos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sendtest)


        recycle_view.setHasFixedSize(true)
        recycle_view.layoutManager = (LinearLayoutManager(this))

        val adapter = AdapterMessage(dataSource)
        recycle_view.adapter = adapter

        initData(adapter)

        /*button_insert.setOnClickListener{
            val newData = ArrayList<Message>()

            for (i in 0..2)
                newData.add(Message("insert", 1, 2))
            adapter.insertItem(newData)
            recycle_view.smoothScrollToPosition(adapter.itemCount-1)
        }*/

        /*button_update.setOnClickListener{
            val newData = ArrayList<Message>()

            for (i in 0..2)
                newData.add(Message("update", 1, 2))
            adapter.updateItem(newData)
        }*/


        button_insert.setOnClickListener {
            sendMessage(adapter)
        }

    }

    private fun initData(adapter: AdapterMessage) {
        getAllMessages(adapter)
    }


    fun sendMessage(adapter: AdapterMessage){
        var cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo


        if(networkInfo != null && networkInfo.isConnected){

            Log.d("MainActivity", "Y'a internet")
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(MessageService::class.java)

            val newMessage = editText.getText().toString()
            val user_sender = profileName
            val dateTime = Date()

            val newMessageFromService = Message(message = newMessage, username_sender = user_sender, created_at = dateTime)
            val createNewMessage = service.createMessage(newMessageFromService)

            createNewMessage.enqueue(object : Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    val allCourse = response.body()
                    if (allCourse != null) {
                        val newData = ArrayList<Message>()
                        newData.add(newMessageFromService)
                        info("Create : ${allCourse.message} by ${allCourse.username_sender} AT ${allCourse.created_at} ")
                        adapter.insertItem(newData)
                        //linearLayout_main.addView(tv_dynamic)
                    }
                }
                override fun onFailure(call: Call<Message>, t: Throwable) {
                    Log.e("MainActiviy","error",t)
                    error("KO")
                }
            })
        } else {
            Log.d("sendtest", "Pas de co internet")

        }
    }

    fun getAllMessages(adapter: AdapterMessage){
        var cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo

        if(networkInfo != null && networkInfo.isConnected){
            try {
                Handler().postDelayed({
                    getAllMessages(adapter)
                    info("Actualisation")

                    val retrofit = Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val service = retrofit.create(MessageService::class.java)
                    val messageListAll = service.listMessage()

                    messageListAll.enqueue(object : Callback<List<Message>> {
                        override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                            val allCourse = response.body()
                            if (allCourse != null) {
                                info("Tous les messages:")
                                var compteur = 0
                                val newData = ArrayList<Message>()
                                for (c in allCourse) {
                                    newData.add(
                                        Message(
                                            allCourse.get(compteur).message,
                                            allCourse.get(compteur).username_sender,
                                            allCourse.get(compteur).created_at
                                        )
                                    )
                                    compteur++
                                }

                                adapter.updateItem(newData)
                            }
                        }

                        override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                            Log.e("MainActiviy", "error", t)
                            error("KO")
                        }
                    })

                }, 10000)
            } catch (e: ConnectException) {
                Log.e("sendTest", "Arrive pas a se co lel")
            }
        } else {
            Log.d("sendtest", "Pas de co internet")

        }
    }

}
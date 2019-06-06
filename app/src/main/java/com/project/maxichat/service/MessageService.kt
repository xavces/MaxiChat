package com.project.maxichat.dto

import retrofit2.Call
import retrofit2.http.*
import com.project.maxichat.routes.Message


interface MessageService {

    @GET("/message")
    fun listMessage(): Call<List<Message>>

    @POST("message")
    fun createMessage(@Body newMessage : Message): Call<Message>

}
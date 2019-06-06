package com.project.maxichat.dto

import com.project.maxichat.routes.Message
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface MessageService {

    @GET("/message")
    fun listMessage(): Call<List<Message>>

    @POST("message")
    fun createMessage(@Body newMessage: Message): Call<Message>

}
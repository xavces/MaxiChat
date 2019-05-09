package com.project.maxichat.dto

import retrofit2.Call
import retrofit2.http.*
import com.project.maxichat.routes.Message


interface MessageService {

    @GET("/message")
    fun listUser(): Call<List<Message>>

    // Get message with ID_MESSAGE
    @GET("/message/{id_message}")
    fun getUserById(@Path("id_message") id: Int?): Call<Message>

    // Get message with ID_GROUP
    @GET("/message/group/{id_group}")
    fun listMessageByGroup(@Path("id_group") id: Int?): Call<List<Message>>

    @DELETE("message/{id_message}")
    fun deleteUser(@Path("id_message") id: Int) : Call<Message>

    @POST("message")
    fun createMessage(@Body newMessage : Message): Call<Message>

}
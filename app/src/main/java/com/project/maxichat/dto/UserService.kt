package com.project.maxichat.dto

import com.project.maxichat.routes.User
import retrofit2.Call
import retrofit2.http.*


interface UserService {

    @GET("/users")
    fun listUser(): Call<List<User>>

    @GET("/users/{id_user}")
    fun getUserById(@Path("id_user") id: Int?): Call<User>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int) : Call<User>

    @POST("users")
    fun createUser(@Body newUser : User): Call<User>

    /*@PUT("users/{id}") fun updatePartAsync(@Path("id") id: Int, @Body newPart: User) : Deferred<Response<Void>>
    * #PATCH("users/{id}") fun updatePartAsync(@Path("id") id: Int, @Body newUser: User) : Call<User> */
}
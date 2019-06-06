package com.project.maxichat

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.project.maxichat.dto.UserService
import com.project.maxichat.routes.User
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), AnkoLogger {

    private val url = "http://10.13.100.5:3000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_connecter.setOnClickListener {
            val usernameChoice = editTest_username_login.getText().toString()
            val intent = Intent(this@MainActivity,sendtest::class.java)

            intent.putExtra("Username", usernameChoice)
            startActivity(intent)

            /*startActivity(intentFor<sendtest>("username" to usernameChoice).singleTop())*/
        }



    }






}


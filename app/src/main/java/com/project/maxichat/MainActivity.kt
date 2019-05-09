package com.project.maxichat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

import com.project.maxichat.dto.UserService
import com.project.maxichat.routes.User
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), AnkoLogger {

    private val url = "http://10.13.1.205:3000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOk.setOnClickListener {
            getAllUser()
        }


        buttonSecond.setOnClickListener {
            getUserById(11)
        }


        buttonDelete.setOnClickListener {
            deleteUser(10)
        }


        buttonCreate.setOnClickListener {
            createUser()
        }

        btnChangePage.setOnClickListener {
            startActivity(intentFor<sendtest>().singleTop())
        }

    }

    fun createUser(){
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserService::class.java)

        val newNom = nom.getText().toString()
        val newPrenom = prenom.getText().toString()
        val newEmail = Email.getText().toString()
        val newPass = Pass.getText().toString()
        val newPseudo = Pseudo.getText().toString()
        val newTag = Tag.getText().toString()
        val newAvatar = Avatar.getText().toString()
        val newconfPass = confPass.getText().toString()
        //info("NewNom : " + newNom)

        val newUser = User(
            nom = newNom, prenom = newPrenom, email = newEmail,
            password = newPass, pseudo = newPseudo, tag = newTag, avatar = newAvatar
        )
        val userListById = service.createUser(newUser)

        userListById.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val allCourse = response.body()
                if (allCourse != null) {
                    info("User: CREATED : " + allCourse.nom)
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("MainActiviy_CREATE","error",t)
                error("KO")
            }
        })
    }

    fun getAllUser(){
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserService::class.java)
        val userListAll = service.listUser()

        userListAll.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val allCourse = response.body()
                if (allCourse != null) {
                    info("Tous les users:")
                    for (c in allCourse) {
                        info("  Hello ${c.prenom} ${c.nom} ")
                    }
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("MainActiviy","error",t)
                error("KO")
            }
        })
    }

    fun getUserById(idUserToDelete: Int){
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserService::class.java)
        val userListById = service.getUserById(idUserToDelete)

        userListById.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val allCourse = response.body()
                if (allCourse != null) {
                    info("User(" + idUserToDelete + "): " + allCourse.nom)
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("MainActiviy_GetById","error",t)
                error("KO")
            }
        })
    }

    fun deleteUser(idUserToDelete: Int){
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserService::class.java)
        val userListById = service.deleteUser(idUserToDelete)

        userListById.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val allCourse = response.body()
                if (allCourse != null) {
                    info("User(" + idUserToDelete + "): DELETED")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("MainActiviy_CREATE","error",t)
                error("KO")
            }
        })
    }




}


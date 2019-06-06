package com.project.maxichat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener { startActivity<MessageActivity>() }
    }
}
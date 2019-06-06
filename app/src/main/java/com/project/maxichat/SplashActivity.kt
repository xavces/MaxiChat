package com.project.maxichat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 6000 //7 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}
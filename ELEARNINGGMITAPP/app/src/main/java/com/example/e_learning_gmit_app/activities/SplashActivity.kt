package com.example.e_learning_gmit_app.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.e_learning_gmit_app.R

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT: Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Activity displayed in full screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        Handler()
            .postDelayed(
                {
                    // This method will be executed once the timer is over
                    // Start your app main activity

                    startActivity(Intent(this, LoginActivity::class.java))

                    // close this activity
                    finish()
                },
                SPLASH_TIME_OUT
            )
    }
}

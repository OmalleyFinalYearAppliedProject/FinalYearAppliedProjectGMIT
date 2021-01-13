package com.example.fypkotlincodebase

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.splash_screen.*


class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        //Create Three Seperate animationd for Splash Screen
        val topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation)
        val bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)


        TopTextView.startAnimation(topAnimation)
        middleTextView.startAnimation(middleAnimation)
        bottomTextView.startAnimation(bottomAnimation)


        val splashScreenTimeOut = 4000
        val homeIntent= Intent(this@SplashScreen, MainActivity::class.java)

        Handler().postDelayed({

            startActivity(homeIntent)
            finish()
        } , splashScreenTimeOut.toLong())


    }

}
package com.example.e_learning_gmit_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CalenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


    }


}
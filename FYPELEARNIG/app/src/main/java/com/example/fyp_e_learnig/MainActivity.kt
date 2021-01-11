package com.example.fyp_e_learnig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Remove stats bar icons to create full screen mode for user
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN



        btn_start.setOnClickListener  {


            if(et_name.text.toString().isEmpty())
        }
    }
}
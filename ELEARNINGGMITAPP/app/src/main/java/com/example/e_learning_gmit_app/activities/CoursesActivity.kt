package com.example.e_learning_gmit_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_learning_gmit_app.R

class CoursesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_courses_activity)



        // remove the taskbar from top of screen
        val actionbar = supportActionBar
        actionbar!!.title = "Dashboard"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    // method for back button to return user to previous screen
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


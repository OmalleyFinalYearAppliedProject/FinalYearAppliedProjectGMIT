package com.example.e_learning_gmit_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_learning_gmit_app.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //Launches the Calender Activity inside of the user DashBoard view
        calenderBox_btn.setOnClickListener {

            startActivity(Intent(this@DashboardActivity, CalenderActivity::class.java))
        }
        //Launches the Messages  Activity inside of the user DashBoard
        postsBox_btn.setOnClickListener {

            startActivity(Intent(this@DashboardActivity, PostFeed::class.java))
        }

        //Launches the create Post Activity in the user Dashboard
        user_create_post_btn.setOnClickListener {

            startActivity(Intent(this@DashboardActivity, PostsActivity::class.java))
        }
        //Launches the about app , Describes app to user  Activity inside of the users DashBoard
        about_us_btn.setOnClickListener {

            startActivity(Intent(this@DashboardActivity, AboutUs::class.java))
        }
        //Launches the about app , Shows user Details  to user  Activity inside of the users DashBoard
        my_accBox_btn.setOnClickListener {

            startActivity(Intent(this@DashboardActivity, userProfileActivity::class.java))
        }

        //Launches the about app , Begins Quiz Page  to user  Activity inside of the users DashBoard
        quizBox_btn.setOnClickListener {

            startActivity(Intent(this@DashboardActivity, QuestionLobby::class.java))
        }
    }
}
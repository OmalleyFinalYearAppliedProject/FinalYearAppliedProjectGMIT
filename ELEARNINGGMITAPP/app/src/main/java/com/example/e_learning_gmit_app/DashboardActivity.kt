package com.example.e_learning_gmit_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_login.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        //Launches the Calender Activity inside of the users DashBoard
        calender_btn.setOnClickListener{

            startActivity(Intent(this@DashboardActivity, QuestionLobby::class.java))
            finish()
        }

        //Launches the Messages  Activity inside of the users DashBoard
        messaging_btn.setOnClickListener{

            startActivity(Intent(this@DashboardActivity, QuestionLobby::class.java))
            finish()

        }

        //Launches the about app , Describes app to user  Activity inside of the users DashBoard
        app_info_btn.setOnClickListener{

            startActivity(Intent(this@DashboardActivity, AboutUs::class.java))
            finish()

        }


        //Launches the about app , Shows user Details  to user  Activity inside of the users DashBoard
        my_acc_btn.setOnClickListener{

            startActivity(Intent(this@DashboardActivity, userProfileActivity::class.java))
            finish()

        }

        //Launches the about app , Begins Quiz Page  to user  Activity inside of the users DashBoard
        quiz_btn.setOnClickListener{

            startActivity(Intent(this@DashboardActivity, QuestionLobby::class.java))
            finish()

        }
    }
}
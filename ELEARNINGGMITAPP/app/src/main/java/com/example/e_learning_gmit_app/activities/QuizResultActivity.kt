package com.example.e_learning_gmit_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_learning_gmit_app.R
import com.example.e_learning_gmit_app.data.Constants
import kotlinx.android.synthetic.main.activity_result.*

class QuizResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // instance variables
        val username = intent.getStringExtra(Constants.USER_NAME)
        tv_name.text =username


        val totalQs  = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAns   = intent.getIntExtra(Constants.Correct_Ans,0)
        val noOfQuizzesTaken   = intent.getIntExtra(Constants.Correct_Ans,0)

        // bind user score from constants
        tv_score.text = "Your Score is $correctAns out of $totalQs"
        tv_attempt.text = "Attempt No. $noOfQuizzesTaken "

        // return user to home
        btn_finish.setOnClickListener{

            startActivity(Intent(this, DashboardActivity::class.java))
        }

        // return user to home
        btn_retry.setOnClickListener{

            startActivity(Intent(this, QuizQuestionsActivity::class.java))
        }
    }
}
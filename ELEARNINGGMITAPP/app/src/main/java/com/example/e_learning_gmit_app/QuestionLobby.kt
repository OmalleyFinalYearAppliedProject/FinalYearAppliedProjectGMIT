package com.example.e_learning_gmit_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_question_lobby.*

class QuestionLobby : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_lobby)



        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        btn_start.setOnClickListener{

            if(et_name.text.toString().isEmpty()){



                Toast.makeText(this ,
                    "Please enter your name " , Toast.LENGTH_SHORT).show()
            }else {

                val intentQs = Intent(this, QuizQuestionsActivity::class.java)
                startActivity(intentQs)
                finish()

            }
        }


    }
}
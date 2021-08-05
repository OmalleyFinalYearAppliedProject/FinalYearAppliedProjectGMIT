package com.example.e_learning_gmit_app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.e_learning_gmit_app.R
import com.example.e_learning_gmit_app.data.Constants
import kotlinx.android.synthetic.main.activity_question_lobby.*

class QuestionLobby : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_lobby)


        // remove action bar
        val actionbar = supportActionBar
        actionbar!!.title = "Dashboard"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        // Button event Handler

        btn_start.setOnClickListener {

            if (et_name.text.toString().isEmpty()) {

                // Display text to user
                Toast.makeText(
                    this,
                    "Please enter your name ", Toast.LENGTH_SHORT
                ).show()
            } else {
                // Start activity once entered
                val intentQs = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,et_name.text.toString())
                startActivity(intentQs)
                // end
                finish()

            }
        }

    }
    // Allow user to return to previous page
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
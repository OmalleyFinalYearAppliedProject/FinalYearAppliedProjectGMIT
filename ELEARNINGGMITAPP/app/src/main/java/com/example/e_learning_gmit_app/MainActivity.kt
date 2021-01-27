package com.example.e_learning_gmit_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_question_lobby.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser


        /** If the user is not authenticated , send them to sign Activity**/

        if( user != null){

            val dashboardIntent = Intent (this , DashboardActivity::class.java)
            startActivity(dashboardIntent)
            finish()
        } else {
            val signInintent = Intent (this , LoginActivity::class.java)
            startActivity(signInintent)
            finish()

        }





        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        tv_user_id.text = "User ID :: $userId"
        tv_email_id.text = "Email ID :: $emailId"


        btn_logout.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity , LoginActivity::class.java))
            finish()
        }
    }
    }


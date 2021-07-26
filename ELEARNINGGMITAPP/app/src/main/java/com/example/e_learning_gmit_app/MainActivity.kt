package com.example.e_learning_gmit_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_question_lobby.*

class MainActivity : AppCompatActivity() {

    // instantiate firebase authenticator
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        /** If the user is not authenticated , send them to sign Activity */
        if (user != null) {

            val dashboardIntent = Intent(this, DashboardActivity::class.java)
            startActivity(dashboardIntent)
            finish()
        } else {
            val signInintent = Intent(this, LoginActivity::class.java)
            startActivity(signInintent)
            finish()
        }

        // RETREIVE AND MAP CURRENT VARIABLES
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        // DISPLAY THE USERS ID AND EMAIL
        tv_user_id.text = "User ID :: $userId"
        tv_email_id.text = "Email ID :: $emailId"

        // BUTTON TO LOG OUT FIREBASE USER
        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }
}

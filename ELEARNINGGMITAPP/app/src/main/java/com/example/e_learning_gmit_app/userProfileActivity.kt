package com.example.e_learning_gmit_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_user_profile.*

class userProfileActivity : AppCompatActivity() {


    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        mAuth = FirebaseAuth.getInstance()
        val currentuser = mAuth.currentUser

        id_txt.text = currentuser?.uid
        name_txt.text = currentuser?.displayName
        email_txt.text = currentuser?.email

    }
}
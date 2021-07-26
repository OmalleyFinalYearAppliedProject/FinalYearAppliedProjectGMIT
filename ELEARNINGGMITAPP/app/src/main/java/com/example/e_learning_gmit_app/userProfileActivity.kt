package com.example.e_learning_gmit_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_user_profile.*

class userProfileActivity : AppCompatActivity() {

    // initialise firebase
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Provide action bar on top of component
        val actionbar = supportActionBar

        // add title to top of activity
        actionbar!!.title = "Dashboard"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        // instantiate authenticator client
        mAuth = FirebaseAuth.getInstance()
        val currentuser = mAuth.currentUser

        // Display the current details for sign in user to screen
        id_txt.text = currentuser?.uid
        name_txt.text = currentuser?.displayName
        email_txt.text = currentuser?.email
    }
    // Returns user to previous activity
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

package com.example.e_learning_fyp_code

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main. activity_main.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.user_registration.*


class MainActivity : AppCompatActivity() {



    lateinit var handler:DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler= DatabaseHelper(this)


        showHome()


        registration.setOnClickListener{

            showRegistaration()
        }




        login.setOnClickListener()
        {
            showLogIN()
        }

        save.setOnClickListener {

            handler.insertUserData(name.text.toString(),email.text.toString()
                , password_register.text.toString())

            showHome()
        }


        login_button.setOnClickListener{

           if  (handler.userPresent(login_email.text.toString(), login_password.text.toString()))
           Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

            else
               Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_SHORT).show()

        }
    }


    private fun showRegistaration()
    {

        registration_layout.visibility= View.VISIBLE
        login_layout.visibility = View.GONE
        home_11.visibility=View.GONE
    }


    private fun showLogIN()
    {

        registration_layout.visibility= View.VISIBLE
        login_layout.visibility = View.GONE
        home_11.visibility=View.GONE


    }


    private fun showHome(){


        registration_layout.visibility= View.VISIBLE
        login_layout.visibility = View.GONE
        home_11.visibility=View.GONE

    }
}
package com.example.e_learning_gmit_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN




        tv_register.setOnClickListener{

            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }


        btn_login.setOnClickListener {

            when {


                TextUtils.isEmpty(et_login_email.text.toString().trim {

                    it <= ' '
                }) -> {

                    Toast.makeText(


                        this@LoginActivity,
                        "Please enter email.",
                        Toast.LENGTH_SHORT

                    ).show()
                }


                TextUtils.isEmpty(et_login_password.text.toString().trim {

                    it <= ' '
                }) -> {

                    Toast.makeText(


                        this@LoginActivity,
                        "Please enter password.",
                        Toast.LENGTH_SHORT

                    ).show()
                }

                else -> {


                    val email: String = et_login_email.text.toString().trim {
                        it <= ' '
                    }


                    val password: String = et_login_password.text.toString().trim {
                        it <= ' '
                    }


                    // Create an instance and create a register a user with email and password.
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                                // if the registration is successfully done
                                if (task.isSuccessful) {



                                    Toast.makeText(


                                        this@LoginActivity,
                                        "You are logged successfully",
                                        Toast.LENGTH_SHORT

                                    ).show()


                                    val intent =
                                        Intent(this@LoginActivity, DashboardActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


                                    intent.putExtra(


                                        "user_id",
                                        FirebaseAuth.getInstance().currentUser!!.uid

                                    )
                                    intent.putExtra("email_id",email)
                                    startActivity(intent)
                                    finish()
                                } else {


                                    // If the registering is not successful then show error message.

                                    Toast.makeText(

                                        this@LoginActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT

                                    ).show()


                                }


                            }


                }


            }
        }
    }
}

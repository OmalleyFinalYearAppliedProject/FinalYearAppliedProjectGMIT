package com.example.e_learning_gmit_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.auth.account.WorkAccount.getClient
import com.google.android.gms.auth.api.credentials.Credentials.getClient
import com.google.android.gms.auth.api.phone.SmsRetriever.getClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignIn.getClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.safetynet.SafetyNet.getClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN




        //Launches the Calender Activity inside of the users DashBoard
        guestacc_btn.setOnClickListener{

            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }

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



        // Configure Google Sign In


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()


        googleSignInClient = GoogleSignIn.getClient(this, gso)


        mAuth = FirebaseAuth.getInstance()

        google_sign_in.setOnClickListener {
            googlesignIn()
        }
    }


    private fun googlesignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            val exception = task.exception
            if (task.isSuccessful){

                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("LoginActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("LoginActivity", "Google sign in failed", e)
                }
                }else {
                Log.w("LoginActivity", exception.toString())

                }
            }

        }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("LogInActivity", "signInWithCredential:success")
                        val intent  = Intent(this ,  DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("LoginActivity", "signInWithCredential:failure", task.exception)
                    }

                }
    }




}

package com.example.e_learning_gmit_app

import android.content.Intent
import android.content.pm.PackageManager
import android.media.FaceDetector
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        printKeyHash()
        mAuth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()

        facebook_sign_in.setReadPermissions("email")
        facebook_sign_in.setOnClickListener {
            facebookSignIn()
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        //Launches the Calender Activity inside of the users DashBoard
        guestacc_btn.setOnClickListener {

            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }

        tv_register.setOnClickListener {

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
                                intent.putExtra("email_id", email)
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

        callbackManager!!.onActivityResult(requestCode, resultCode, data)


        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            val exception = task.exception
            if (task.isSuccessful) {

                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("LoginActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("LoginActivity", "Google sign in failed", e)
                }
            } else {
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
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginActivity", "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun printKeyHash() {

        try {

            val info = packageManager.getPackageInfo(
                "com.example.e_learning_gmit_app",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray())
                Log.e("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {


        } catch (e: NoSuchAlgorithmException) {

        }
    }

    private fun facebookSignIn() {


        facebook_sign_in.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {

                handleFacebookAccessToken(result!!.accessToken)
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
            }
        })
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken?) {
        var credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        mAuth.signInWithCredential(credential).addOnFailureListener { e ->
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
            .addOnSuccessListener { result ->

                val email = result.user?.email
                Toast.makeText(this, "You logged in " + email, Toast.LENGTH_LONG).show()
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            }
    }
}

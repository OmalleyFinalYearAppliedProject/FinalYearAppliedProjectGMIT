package com.example.e_learning_gmit_app

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    // obj request code used by firebase
    companion object {
        private const val RC_SIGN_IN = 120
    }
    // authenticating web services
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    //  FacebookSdk set for authentication
    var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // DEV PRINTS KEY HASH TO Console
        printKeyHash()
        // Create obj instance of authentication
        mAuth = FirebaseAuth.getInstance()
        // facebook sdk create insrance
        callbackManager = CallbackManager.Factory.create()

        // event listener for user sign in
        facebook_sign_in.setReadPermissions("email")
        facebook_sign_in.setOnClickListener { facebookSignIn() }

        // app displays in full screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // allows for a guest to sign in
        guestacc_btn.setOnClickListener {

            // start Dashboard activity
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
        }

        // call on register button routes user to
        tv_register.setOnClickListener {

            // start Dashboard activity
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        // event listener for user login button
        btn_login.setOnClickListener {
            when {

                // user on screen text boxes lambda expression
                TextUtils.isEmpty(
                    et_login_email.text.toString().trim {

                        // null string compare
                        it <= ' '
                    }
                ) -> {
                    // display message to screen
                    Toast.makeText(
                        this@LoginActivity,
                        // display message
                        "Please enter email.",
                        Toast.LENGTH_SHORT
                        // make visible in component
                    )
                        .show()
                }
                // when text box empty
                TextUtils.isEmpty(
                    et_login_password.text.toString().trim {

                        // null string
                        it <= ' '
                    }
                ) -> {
                    // display message to screen
                    Toast.makeText(

                        // 2 params activuty lcoation , and text message to dispay
                        this@LoginActivity,
                        "Please enter password.",
                        Toast.LENGTH_SHORT
                        // make visible
                    )
                        .show()
                }
                else -> {

                    // compare and  matches the same characters
                    val email: String = et_login_email.text.toString().trim { it <= ' ' }
                    val password: String = et_login_password.text.toString().trim { it <= ' ' }

                    // Create an instance and create a register a user with email and password.
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            // if the registration is successfully done
                            if (task.isSuccessful) {

                                //  create and display text message to screen
                                Toast.makeText(
                                    this@LoginActivity,
                                    "You are logged successfully",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                // launch new dashboard when login successful
                                val intent =
                                    Intent(this@LoginActivity, DashboardActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                                // Add user info to firebase base
                                intent.putExtra(
                                    "user_id",
                                    FirebaseAuth.getInstance().currentUser!!.uid
                                )
                                intent.putExtra("email_id", email)
                                // start the intended activity
                                startActivity(intent)
                                // Terminate current activity
                                finish()
                            } else {

                                // If the registering is not successful then show error message.
                                Toast.makeText(
                                    this@LoginActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                )
                                    // display message to screen
                                    .show()
                            }
                        }
                }
            }
        }

        // Configure Google Sign In
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        // INSTANTIATE CLIENT TO THE UNIQUE USER
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // create unique instance for authentication
        mAuth = FirebaseAuth.getInstance()

        // event handler for sign in method
        google_sign_in.setOnClickListener { googlesignIn() }
    }

    // function for google sign in
    private fun googlesignIn() {
        // Map sign in intent to google client
        val signInIntent = googleSignInClient.signInIntent
        // Begin activity
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    /** Validate user request on firebase api result */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager!!.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            // co-routine  exception handler
            val exception = task.exception

            if (task.isSuccessful) {

                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("LoginActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately with logged warning
                    Log.w("LoginActivity", "Google sign in failed", e)
                }
            } else {
                // LOG WARNING TO CONSOLE IF TASK ISSUE
                Log.w("LoginActivity", exception.toString())
            }
        }
    }

    /**
     * Authenticate users sign in credentials in email and password input boxes using firebse token
     */
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
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

    // Creates hash key for facebook auth api
    private fun printKeyHash() {

        try {

            // store app package info
            val info =
                packageManager.getPackageInfo(
                    "com.example.e_learning_gmit_app",
                    PackageManager.GET_SIGNATURES
                )

            // repeat  block for full  signature
            for (signature in info.signatures) {
                // Create a  message digest using SHA-1 algroithm
                val md = MessageDigest.getInstance("SHA")
                // parsed message digest key to a byte array
                md.update(signature.toByteArray())
                // DEV print to console
                Log.e("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
            // ERROR HANDLING BLOCK
        } catch (e: PackageManager.NameNotFoundException) {} catch (e: NoSuchAlgorithmException) {}
    }

    // method allows for user to sign in using FACEBOOK ACCOUNT
    private fun facebookSignIn() {

        // button callregister for a facebook sign in
        facebook_sign_in.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {

                    handleFacebookAccessToken(result!!.accessToken)
                }
                override fun onCancel() {}
                // CATCHES ERROR  TO CONSOLE
                override fun onError(error: FacebookException?) {}
            }
        )
    }

    /**
     * handles access token requirement to allow for user log in for facebook systems credientals
     */
    private fun handleFacebookAccessToken(accessToken: AccessToken?) {
        var credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        mAuth
            .signInWithCredential(credential)
            //  if failed Credential output erorr message to user screen
            .addOnFailureListener { e -> Toast.makeText(this, e.message, Toast.LENGTH_LONG).show() }
            // validate email lambda
            .addOnSuccessListener { result ->

                // instance variable email
                val email = result.user?.email

                // Display text to displaying successfully authenticated
                Toast.makeText(this, "You logged in " + email, Toast.LENGTH_LONG).show()

                // Navigate user to dashboard
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            }
    }
}

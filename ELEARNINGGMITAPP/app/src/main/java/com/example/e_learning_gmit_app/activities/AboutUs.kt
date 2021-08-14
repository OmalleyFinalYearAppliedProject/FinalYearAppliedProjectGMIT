package com.example.e_learning_gmit_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import com.example.e_learning_gmit_app.R
import kotlinx.android.synthetic.main.activity_boards.*

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        // app displays in full screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setupHyperlink()

    }
        fun setupHyperlink() {
            val linkTextView = findViewById<TextView>(R.id.githublinktv)
            linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }


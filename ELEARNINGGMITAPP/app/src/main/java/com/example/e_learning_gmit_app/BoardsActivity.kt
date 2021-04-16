package com.example.e_learning_gmit_app

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class BoardsActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boards)

        // Enables Always-on
        setAmbientEnabled()
    }
}
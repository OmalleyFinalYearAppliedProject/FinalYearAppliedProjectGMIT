package com.example.e_learning_gmit_app

import android.graphics.Color
import android.graphics.LinearGradient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_quiz_feed.*
import okhttp3.*
import java.io.IOException

class QuizFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_feed)

        //recyclerView_main.setBackgroundColor(Color.BLUE)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
      //  recyclerView_main.adapter = MainAdapter()


        fetchJson()
    }

    fun fetchJson(){
        println("Attempting to Fetch JSON")


        var url = "https://piece-of-cake-learning.herokuapp.com/api/v1/users"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {


            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed =  gson.fromJson(body, HomeFeed::class.java )


                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homeFeed)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to exec")
            }


        })
    }
}

class HomeFeed(val users: List<User>)

class User(val id: Int ,val username: String  , val password: String , val created_at: String , val updated_at: String )

//class Channel(val name:String , val profileImageUrl: String)

//[{"id":1,"username":"Oliver","password":"password","created_at":"2021-02-16T22:52:47.440Z","updated_at":"2021-02-16T22:52:47.440Z"}]
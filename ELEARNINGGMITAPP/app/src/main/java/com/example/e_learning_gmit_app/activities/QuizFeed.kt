package com.example.e_learning_gmit_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.e_learning_gmit_app.R
import com.example.e_learning_gmit_app.`interface`.FeedInterface
import com.example.e_learning_gmit_app.models.FeedModel
import kotlinx.android.synthetic.main.activity_quiz_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_feed)


        // create instance of retrofit client
        var rf = Retrofit.Builder()
            .baseUrl(FeedInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

        var API = rf.create(FeedInterface::class.java)
        var call = API.posts

        call?.enqueue(object : Callback<List<FeedModel?>?> {

            override fun onFailure(call: Call<List<FeedModel?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<FeedModel?>?>,
                response: Response<List<FeedModel?>?>
            ) {
                var feedList: List<FeedModel>? = response.body() as List<FeedModel>
                var post = arrayOfNulls<String>(feedList!!.size)

                // loop over posts
                for (i in feedList!!.indices)
                    post[i] = feedList!![i]!!.description


                var adapter = ArrayAdapter<String>(
                    applicationContext,
                    android.R.layout.simple_dropdown_item_1line,
                    post
                )
                listview.adapter = adapter

            }
        })
    }

}

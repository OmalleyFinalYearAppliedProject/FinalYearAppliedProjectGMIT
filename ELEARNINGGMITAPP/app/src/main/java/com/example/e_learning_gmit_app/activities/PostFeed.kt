package com.example.e_learning_gmit_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.e_learning_gmit_app.R
import com.example.e_learning_gmit_app.`interface`.FeedInterface
import com.example.e_learning_gmit_app.`interface`.PostInterface
import com.example.e_learning_gmit_app.models.FeedModel
import com.example.e_learning_gmit_app.models.PostModel
import kotlinx.android.synthetic.main.activity_post_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_feed)


        // create instance of retrofit client
        var rf = Retrofit.Builder()
            .baseUrl(PostInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

        var API = rf.create(PostInterface::class.java)
        var call = API.forumposts

        call?.enqueue(object : Callback<List<PostModel?>?> {

            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<PostModel?>?>,
                response: Response<List<PostModel?>?>
            ) {
                var postList: List<PostModel>? = response.body() as List<PostModel>
                var post = arrayOfNulls<String>(postList!!.size)

                // loop over posts
                for (i in postList!!.indices)
                    post[i] = postList!![i]!!.title


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

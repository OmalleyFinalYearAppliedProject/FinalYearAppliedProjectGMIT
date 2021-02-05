package com.example.e_learning_gmit_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import kotlinx.android.synthetic.main.activity_about_us.*
import okhttp3.*
import java.io.IOException

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)



        recyclerView_main.layoutManager = LinearLayoutManager(this)
      //  recyclerView_main.adapter = MainAdapter();



        fetchJson()
    }

    fun fetchJson(){

        println("Attempting to Fetch JSON")


        // location of rails server JSON data
        val url = "http://localhost:3000/api/v1/users/1/facts"


        val request = Request.Builder().url(url).build()


        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {


                val body = response?.body?.string()
                println(body)


                val gson = GsonBuilder().create()




                val homefeed = gson.fromJson(body, HomeFeed::class.java)



                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homefeed)

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }




        })

    }
}

class HomeFeed(val colleges: List<College>)
class College (val id: Int , val name: String)

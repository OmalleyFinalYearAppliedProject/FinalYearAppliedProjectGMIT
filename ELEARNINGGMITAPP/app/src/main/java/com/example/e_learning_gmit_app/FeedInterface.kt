package com.example.e_learning_gmit_app

import retrofit2.Call
import retrofit2.http.GET

interface FeedInterface {


        @get:GET("questions")
        val posts : Call<List<FeedModel?>?>


        companion object {
            const val BASE_URL = "https://cloud-backend-js.herokuapp.com"
    }

}
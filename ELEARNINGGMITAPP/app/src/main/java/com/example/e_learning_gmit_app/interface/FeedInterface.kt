package com.example.e_learning_gmit_app.`interface`

import com.example.e_learning_gmit_app.models.FeedModel
import retrofit2.Call
import retrofit2.http.GET

interface FeedInterface {
    // OKHTTP NETWORK GET REQUEST FOR QUESTIONS API
    @get:GET("questions")
    // CREATE STORE POSTS INTO A LIST
    val posts : Call<List<FeedModel?>?>

    // OBJ HOLDS URL TO SERVER
    companion object {
        const val BASE_URL = "https://cloud-backend-js.herokuapp.com"
    }
}
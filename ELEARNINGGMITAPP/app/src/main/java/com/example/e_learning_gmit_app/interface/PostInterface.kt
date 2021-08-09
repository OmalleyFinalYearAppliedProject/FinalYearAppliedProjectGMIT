package com.example.e_learning_gmit_app.`interface`

import com.example.e_learning_gmit_app.models.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface PostInterface {

    // OKHTTP NETWORK GET REQUEST FOR QUESTIONS API
    @get:GET("posts")
    // CREATE STORE POSTS INTO A LIST
    val posts : Call<List<PostModel?>?>

    // OBJ HOLDS URL TO SERVER
    companion object {
        const val BASE_URL = "http://localhost:3333"
    }
}

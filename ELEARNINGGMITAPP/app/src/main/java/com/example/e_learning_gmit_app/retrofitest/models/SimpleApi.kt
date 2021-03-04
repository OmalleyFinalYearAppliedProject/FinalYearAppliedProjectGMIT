package com.example.e_learning_gmit_app.retrofitest.models

import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("api/v1/users")
    suspend fun getPost(): Response<Post>
}
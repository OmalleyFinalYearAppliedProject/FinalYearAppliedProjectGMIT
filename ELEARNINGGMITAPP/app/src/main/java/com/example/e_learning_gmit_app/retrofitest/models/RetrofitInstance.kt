package com.example.e_learning_gmit_app.retrofitest.models

import com.example.e_learning_gmit_app.retrofitest.models.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PostGetRequest by lazy {

        retrofit.create(PostGetRequest::class.java)
    }
}
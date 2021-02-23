package com.example.e_learning_gmit_app.retrofitest.models

import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
     return   RetrofitInstance.api.getPost()
    }
}
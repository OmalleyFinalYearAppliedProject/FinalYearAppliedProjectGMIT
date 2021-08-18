package com.example.e_learning_gmit_app.client

import android.util.Base64
import com.example.e_learning_gmit_app.`interface`.PostInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitClient {

    private val AUTH = "Basic "+ Base64.encodeToString("testertom:11".toByteArray(), Base64.NO_WRAP)

    private const val BASE_URL = "https://quiz-node-js-backend.herokuapp.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", AUTH)
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: PostInterface by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(PostInterface::class.java)
    }

}

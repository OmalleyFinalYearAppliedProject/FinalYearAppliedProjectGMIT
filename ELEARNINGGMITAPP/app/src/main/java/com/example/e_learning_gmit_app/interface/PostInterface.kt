package com.example.e_learning_gmit_app.`interface`

import com.example.e_learning_gmit_app.models.DefaultResponse
import com.example.e_learning_gmit_app.models.PostModel
import kotlinx.android.synthetic.main.activity_posts.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PostInterface {

    // OKHTTP NETWORK GET REQUEST FOR QUESTIONS API
    @get:GET("posts")
    // CREATE STORE POSTS INTO A LIST
    val posts : Call<List<PostModel?>?>

    // OBJ HOLDS URL TO SERVER
    companion object {
        const val BASE_URL = "https://cloud-backend-js.herokuapp.com"
    }

    // Post request create posts
    @FormUrlEncoded
    @POST("createpost")
    fun createPost(
        @Field( "title") title:String,
        @Field( "active") active:String,
        @Field( "student") student:String,
        @Field( "teacher") teacher:String

    ):Call<DefaultResponse>

}

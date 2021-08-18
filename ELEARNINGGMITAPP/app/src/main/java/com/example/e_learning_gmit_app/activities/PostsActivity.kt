package com.example.e_learning_gmit_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.e_learning_gmit_app.R
import com.example.e_learning_gmit_app.`interface`.FeedInterface
import com.example.e_learning_gmit_app.`interface`.PostInterface
import com.example.e_learning_gmit_app.client.RetrofitClient
import com.example.e_learning_gmit_app.models.DefaultResponse
import com.example.e_learning_gmit_app.models.FeedModel
import com.example.e_learning_gmit_app.models.PostModel
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.android.synthetic.main.activity_post_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)


        buttonCreatePost.setOnClickListener {


            val title = editPostTitle.text.toString().trim()
            val active = editPostActive.text.toString().trim()
            val student = editPostStudent.text.toString().trim()
            val teacher = editPostTeacher.text.toString().trim()

            if(title.isEmpty()){


                editPostTitle.error = "Title Required"
                editPostTitle.requestFocus()
                return@setOnClickListener
            }


            if(active.isEmpty()){


                editPostActive.error = "Active Post  Required"
                editPostActive.requestFocus()
                return@setOnClickListener
            }



            if(student.isEmpty()){


                editPostStudent.error = "Y / N Required"
                editPostStudent.requestFocus()
                return@setOnClickListener
            }


            if(teacher.isEmpty()){


                editPostTeacher.error = "Teacher Y / N status Required"
                editPostTeacher.requestFocus()
                return@setOnClickListener
            }


            RetrofitClient.instance.createPost(title, active, student, teacher)
                .enqueue(object: Callback<DefaultResponse>{
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

}

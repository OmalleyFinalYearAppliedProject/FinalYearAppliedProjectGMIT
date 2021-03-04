package com.example.e_learning_gmit_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_learning_gmit_app.retrofitest.models.Repository
import com.example.e_learning_gmit_app.retrofitest.models.courseViewModel
import com.example.e_learning_gmit_app.retrofitest.models.courseViewModelFactory
import kotlinx.android.synthetic.main.activity_splash.*

class CoursesActivity : AppCompatActivity() {

    private lateinit var viewModel : courseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_courses_activity)



        val actionbar = supportActionBar
        actionbar!!.title = "Dashboard"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        val repository = Repository()
        val courseViewModelFactory = courseViewModelFactory(repository)
        viewModel = ViewModelProvider(this , courseViewModelFactory).get(courseViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->

            if (response.isSuccessful) {

                
                Log.d("Response ", response.body()?.id.toString())
                Log.d("Response ", response.body()?.username.toString())
                Log.d("Response ", response.body()?.password.toString())
                Log.d("Response ", response.body()?.created_at.toString())
                textView.text = response.body()?.username!!
                Log.d("Response ", response.body()?.updated_at.toString())
            }else {

                Log.d("Response," , response.errorBody().toString())
                textView.text = response.code().toString()
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


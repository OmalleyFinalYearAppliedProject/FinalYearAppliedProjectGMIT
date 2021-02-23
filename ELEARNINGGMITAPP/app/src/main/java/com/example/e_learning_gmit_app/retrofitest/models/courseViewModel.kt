package com.example.e_learning_gmit_app.retrofitest.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class courseViewModel(private val repository: Repository): ViewModel() {


    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch {

            val response = repository.getPost()
                myResponse.value = response
        }
    }

}
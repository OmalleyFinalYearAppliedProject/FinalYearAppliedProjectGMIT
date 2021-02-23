package com.example.e_learning_gmit_app.retrofitest.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class courseViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return courseViewModel(repository) as T

    }


}
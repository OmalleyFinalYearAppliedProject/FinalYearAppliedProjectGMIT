package com.example.e_learning_gmit_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>(){



    // Number of Items
    override fun getItemCount(): Int {

        return  3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        // Create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.quiz_row , parent ,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
    }



}

class CustomViewHolder(v: View): RecyclerView.ViewHolder(v) {


}
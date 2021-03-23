package com.example.e_learning_gmit_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quiz_row.view.*

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>(){



    val titles = listOf("who", "what","where")


    // Number of Items
    override fun getItemCount(): Int {

        return  homeFeed.users.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        // Create a view
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.quiz_row , parent ,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val user = homeFeed.users.get(position)
    holder?.view?.quizTextView?.text = user.username


    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {


}
package com.example.e_learning_gmit_app


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.info_row.view.*


class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>()
{


    override fun getItemCount(): Int {

        return homeFeed.colleges.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {


    val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.info_row, parent,false)
        return CustomViewHolder(cellForRow)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        //holder?.itemView?.textView2?.text="gmit123"

        val college = homeFeed.colleges.get(position)
        holder?.itemView?.textView2.text= college.name
    }


}

class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){

}

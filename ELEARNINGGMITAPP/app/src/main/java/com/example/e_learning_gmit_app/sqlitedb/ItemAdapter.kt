package com.example.e_learning_gmit_app.sqlitedb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.e_learning_gmit_app.R
import com.example.e_learning_gmit_app.activities.CalenderActivity
import com.example.e_learning_gmit_app.models.calenderModelClass
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(val context: Context, val items: ArrayList<calenderModelClass>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.tvName.text = item.name
        holder.tvDate.text = item.date

        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )

        } else {

            holder.llMain.setBackgroundColor(ContextCompat.getColor(context,
                R.color.colorWhite
            ))
        }

        holder.ivEdit.setOnClickListener { view ->

            if (context is CalenderActivity) {
                context.updateRecordDialog(item)
            }
        }

        holder.ivDelete.setOnClickListener { view ->

            if (context is CalenderActivity) {
                context.deleteRecordAlertDialog(item)
            }
        }
    }
    /**
     * Get the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val llMain = view.llMain
        val tvName = view.tvName
        val tvDate = view.tvDate
        val ivEdit = view.ivEdit
        val ivDelete = view.ivDelete
    }
}
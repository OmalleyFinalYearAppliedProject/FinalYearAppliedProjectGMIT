package com.example.e_learning_gmit_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(val context: Context, val items: ArrayList<userModelClass>) :
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
        holder.tvEmail.text = item.email

        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorAccent
                )
            )

        } else {

            holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
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
     * Gwt the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each item to
        val llMain = view.llMain
        val tvName = view.tvName
        val tvEmail = view.tvEmail
        val ivEdit = view.ivEdit
        val ivDelete = view.ivDelete
    }
}
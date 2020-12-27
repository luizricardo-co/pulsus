package com.challenge.pulsus.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.challenge.pulsus.R
import kotlinx.android.synthetic.main.adapter_listitem.view.*

class ListAdapter (categoryList: List<String>, internal var ctx: Context, private val listener: OnItemClickListener)
    : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    internal var categoryList: List<String> = ArrayList<String>()
    init {
        this.categoryList = categoryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(ctx).inflate(R.layout.adapter_listitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = categoryList[position]
    }

    override fun getItemCount() = categoryList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        var categoryName = itemView.txtCategoryName
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                val current = categoryList[position]
                listener.onItemClick(current)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(current: String)
    }
}
package com.ysj.mergeadapterlibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

/**
 *Created by tekabo
 *Created on 2020/7/1
 *PackageName com.ysj.mergeadapterlibrary
 */
class BodyAdapter(val items: List<String>) : RecyclerView.Adapter<BodyAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener{
            val position = holder.bindingAdapterPosition //当前绑定Adapter的位置
//            val position = holder.absoluteAdapterPosition//合并后Adapter的绝对位置
            Toast.makeText(parent.context,"You clicked Body item $position",Toast.LENGTH_SHORT).show()
        }

        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = items[position]
    }
}
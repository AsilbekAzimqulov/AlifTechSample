package com.example.aliftechexample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.aliftechexample.R
import com.example.aliftechexample.data.local.entity.Guide
import kotlinx.android.synthetic.main.item_guide.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var items: List<Guide> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_guide, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView)
            .load(item.icon)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(holder.ivLogo)
        holder.tvName.text = item.name
        holder.tvAddress.text = item.endDate
    }

    override fun getItemCount() = items.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogo = itemView.ivIcon
        val tvName = itemView.tvName
        val tvAddress = itemView.tvAddress

        init {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                listener?.onClick(items[adapterPosition].url)
            }
        }
    }

    interface Listener {
        fun onClick(url: String)
    }
}
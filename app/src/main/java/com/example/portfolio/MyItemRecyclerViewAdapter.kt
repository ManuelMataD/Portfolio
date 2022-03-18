package com.example.portfolio

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.portfolio.data.News

import com.example.portfolio.databinding.FragmentNewsBinding
import com.squareup.picasso.Picasso

class MyItemRecyclerViewAdapter(
    private val values: List<News>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Picasso.get().load(item.urlToImage).into(holder.imageView)
        holder.titleView.text = item.title
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.image
        val titleView: TextView = binding.title
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
package com.example.mazadytask.category.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mazadytask.databinding.LiveItemBinding

/**
 * created by Karim Haggagi on 1/8/25
 **/
class LiveAdapter : RecyclerView.Adapter<LiveAdapter.LiveViewHolder>() {
    inner class LiveViewHolder(private val binding: LiveItemBinding) : ViewHolder(binding.root) {
        fun bind() {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveViewHolder {
        return LiveViewHolder(
            LiveItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: LiveViewHolder, position: Int) {

    }
}
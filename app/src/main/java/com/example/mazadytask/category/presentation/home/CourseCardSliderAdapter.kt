package com.example.mazadytask.category.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mazadytask.databinding.CourseItemBinding
import com.github.islamkhsh.CardSliderAdapter

/**
 * created by Karim Haggagi on 1/8/25
 **/
class CourseCardSliderAdapter() :
    CardSliderAdapter<CourseCardSliderAdapter.CourseCardSliderViewHolder>() {
    inner class CourseCardSliderViewHolder(private val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun bindVH(holder: CourseCardSliderViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCardSliderViewHolder {
        return CourseCardSliderViewHolder(
            CourseItemBinding.inflate(
                android.view.LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
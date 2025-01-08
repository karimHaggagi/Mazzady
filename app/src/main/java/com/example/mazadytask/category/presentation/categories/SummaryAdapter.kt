package com.example.mazadytask.category.presentation.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mazadytask.databinding.SummaryItemBinding

/**
 * created by Karim Haggagi on 1/6/25
 **/
class SummaryAdapter() :
    ListAdapter<SummaryData, SummaryAdapter.SummaryViewHolder>(
        DiffUtilCallBacks
    ) {

    inner class SummaryViewHolder(private val binding: SummaryItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: SummaryData) {
            binding.tvLabel.text = item.propertyName
            binding.tvValue.text = item.propertyValue
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        return SummaryViewHolder(
            SummaryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object DiffUtilCallBacks : DiffUtil.ItemCallback<SummaryData>() {

        override fun areItemsTheSame(
            oldItem: SummaryData,
            newItem: SummaryData
        ): Boolean {
            return oldItem.propertyName == newItem.propertyName
        }

        override fun areContentsTheSame(
            oldItem: SummaryData,
            newItem: SummaryData
        ): Boolean {
            return oldItem == newItem
        }
    }
}
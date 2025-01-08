package com.example.mazadytask.category.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mazadytask.R
import com.example.mazadytask.databinding.CategoryItemBinding

/**
 * created by Karim Haggagi on 1/8/25
 **/
class CategoryAdapter(
    private val categoryList: List<String> = listOf(
        "All",
        "Ui/Ux",
        "3D Dimension",
        "Photography",
        "Animation",
        "Digital Marketing"
    )
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var selectedPosition = 0

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvCategoryName.text = item
            if (absoluteAdapterPosition == selectedPosition) {
                binding.tvCategoryName.isSelected = true
                binding.tvCategoryName.setTextColor(binding.root.context.getColor(R.color.white))
            }else{
                binding.tvCategoryName.isSelected = false
                binding.tvCategoryName.setTextColor(binding.root.context.getColor(R.color.dark_gray))
            }
            binding.root.setOnClickListener {
                val oldPosition = selectedPosition
                selectedPosition = absoluteAdapterPosition
                notifyItemChanged(oldPosition)
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList.get(position))
    }
}
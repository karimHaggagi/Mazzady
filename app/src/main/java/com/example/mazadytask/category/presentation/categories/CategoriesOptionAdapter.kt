package com.example.mazadytask.category.presentation.categories

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mazadytask.core.presentation.animateCollapse
import com.example.mazadytask.core.presentation.animateExpand
import com.example.mazadytask.core.presentation.setUpDropDown
import com.example.mazadytask.databinding.DropDownMenuBinding

/**
 * created by Karim Haggagi on 1/6/25
 **/
class CategoriesOptionAdapter(
    private val onItemClicked: (selectedPosition: Int, item: PropertiesDropDownUi) -> Unit,) :
    ListAdapter<PropertiesDropDownUi, CategoriesOptionAdapter.CategoryOptionViewHolder>(
        DiffUtilCallBacks
    ) {

    inner class CategoryOptionViewHolder(private val binding: DropDownMenuBinding) :
        ViewHolder(binding.root) {
        fun bind(item: PropertiesDropDownUi) {
            binding.tlCategory.hint = item.propertyName

           val selectedPosition =  when (item.inputType) {
                InputType.Default -> {
                    binding.tlCustomInput.visibility = View.GONE
                    -1
                }

                InputType.FromUser -> {
                    binding.tlCustomInput.visibility = View.VISIBLE
                    0
                }

                is InputType.SelectedOption -> {
                    binding.tlCustomInput.visibility = View.GONE
                    (item.inputType as InputType.SelectedOption).index
                }
            }
            binding.categoryDropDown.setUpDropDown(
                binding.root.context,
                item.options.map { it.optionName },
                selectedPosition = selectedPosition,
                onItemClick = { position ->
                    if (position == 0) {
                        binding.tlCustomInput.animateExpand(binding.root)
                        item.inputType = InputType.FromUser
                    } else {
                        binding.tlCustomInput.animateCollapse(binding.root)
                        onItemClicked(position, item)
                        item.inputType = InputType.SelectedOption(position)
                    }
                })

            binding.etCustomEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    item.customInput = p0.toString()
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
            binding.rvOptions.adapter = CategoriesOptionAdapter(onItemClicked).apply {
                if (item.optionChildren.isNotEmpty()) {
                    binding.rvOptions.animateExpand(binding.root)
                    submitList(item.optionChildren)
                } else {
                    binding.rvOptions.animateCollapse(binding.root)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryOptionViewHolder {
        return CategoryOptionViewHolder(
            DropDownMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryOptionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object DiffUtilCallBacks : DiffUtil.ItemCallback<PropertiesDropDownUi>() {

        override fun areItemsTheSame(
            oldItem: PropertiesDropDownUi,
            newItem: PropertiesDropDownUi
        ): Boolean {
            return oldItem.inputType == newItem.inputType
        }

        override fun areContentsTheSame(
            oldItem: PropertiesDropDownUi,
            newItem: PropertiesDropDownUi
        ): Boolean {
            return oldItem == newItem
        }
    }
}
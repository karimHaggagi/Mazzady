package com.example.mazadytask.core.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.transition.TransitionManager
import com.example.mazadytask.R
import com.example.mazadytask.category.presentation.categories.CategoryAdapter
import com.google.android.material.textfield.MaterialAutoCompleteTextView

/**
 * created by Karim Haggagi on 1/6/25
 **/


fun MaterialAutoCompleteTextView.setUpDropDown(
    context: Context,
    listItem: List<String>?,
    selectedPosition: Int = -1,
    onItemClick: (position: Int) -> Unit
) {
    if (listItem.isNullOrEmpty()) {
        resetOption()
        return
    }
    val adapter = CategoryAdapter(
        context,
        listItem
    )
    setAdapter(adapter)
    if (selectedPosition == 1 || selectedPosition == -1) {
        setText("")
    }
    if (selectedPosition != -1) {
        setText(adapter.getItem(selectedPosition), false)
    }
    this.setOnItemClickListener { parent, _, position, _ ->
        // Get the selected item
        val selectedItem = parent.getItemAtPosition(position) as String
        val originalPosition =
            listItem.indexOf(selectedItem) // Get the original position in the list

        onItemClick(position)
        // Handle the selected item
        Toast.makeText(context, "Selected: $originalPosition", Toast.LENGTH_SHORT).show()
    }
}

fun MaterialAutoCompleteTextView.resetOption() {
    setText("", false)
}

fun View.animateExpand(viewGroup: ViewGroup) {
    if (isVisible) {
        return
    }
    visibility = View.VISIBLE
    TransitionManager.beginDelayedTransition(viewGroup)
}

fun View.animateCollapse(viewGroup: ViewGroup) {
    if (!isVisible) {
        return
    }
    visibility = View.GONE
    TransitionManager.beginDelayedTransition(viewGroup)
}
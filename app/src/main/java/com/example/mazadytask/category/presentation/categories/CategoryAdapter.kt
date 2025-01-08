package com.example.mazadytask.category.presentation.categories

import android.content.Context
import android.widget.ArrayAdapter

/**
 * created by Karim Haggagi on 1/5/25
 **/
class CategoryAdapter<T>(context: Context, private val listItem: List<T>) : ArrayAdapter<T>(
    context, android.R.layout.simple_dropdown_item_1line, listItem
) {
    fun getItems(): List<T> {
        return listItem
    }
}
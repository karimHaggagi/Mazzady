package com.example.mazadytask.category.domain.model

import com.example.mazadytask.core.presentation.AppLanguage

/**
 * created by Karim Haggagi on 1/5/25
 **/
data class CategoryModel(
    val children: List<ChildrenModel>,
    val circleIcon: String,
    val id: Int,
    val image: String,
    val name: String,
    val slug: String
) {
    val localName: String
        get() = when (AppLanguage.getLanguage()) {
            AppLanguage.ARABIC -> name
            AppLanguage.ENGLISH -> slug
            else -> ""
        }
}

data class ChildrenModel(
    val circleIcon: String,
    val disableShipping: Int,
    val id: Int,
    val image: String,
    val name: String,
    val slug: String
) {
    val localName: String
        get() = when (AppLanguage.getLanguage()) {
            AppLanguage.ARABIC -> name
            AppLanguage.ENGLISH -> slug
        }
}


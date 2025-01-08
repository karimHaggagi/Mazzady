package com.example.mazadytask.category.domain.model

import com.example.mazadytask.core.presentation.AppLanguage

/**
 * created by Karim Haggagi on 1/6/25
 **/
data class CategoryOptionsModel(
    val description: String,
    val id: Int,
    val isList: Boolean,
    val name: String,
    val options: List<OptionModel>,
    val slug: String,
    val value: String
) {
    val localName: String
        get() = when (AppLanguage.getLanguage()) {
            AppLanguage.ARABIC -> name
            AppLanguage.ENGLISH -> slug
        }
}

data class OptionModel(
    val hasChild: Boolean,
    val id: Int,
    val name: String,
    val parent: Int,
    val slug: String
) {
    val localName: String
        get() = when (AppLanguage.getLanguage()) {
            AppLanguage.ARABIC -> name
            AppLanguage.ENGLISH -> slug
        }
}

package com.example.mazadytask.category.data.dto

import com.example.mazadytask.category.domain.model.CategoryOptionsModel
import com.example.mazadytask.category.domain.model.OptionModel
import com.google.gson.annotations.SerializedName

/**
 * created by Karim Haggagi on 1/6/25
 **/
data class CategoryOptionsDto(
    val description: String? = null,
    val id: Int? = null,
    val list: Boolean? = null,
    val name: String? = null,
    val options: List<Option?>? = null,
    @SerializedName("other_value")
    val otherValue: Any? = null,
    val parent: Any? = null,
    val slug: String? = null,
    val type: Any? = null,
    val value: String? = null
)

data class Option(
    val child: Boolean? = null,
    val id: Int? = null,
    val name: String? = null,
    val parent: Int? = null,
    val slug: String? = null
)

fun CategoryOptionsDto.asCategoryPropertiesDomainModel(): CategoryOptionsModel {
    return CategoryOptionsModel(
        description = description ?: "",
        id = id ?: 0,
        isList = list ?: false,
        name = name ?: "",
        slug = slug ?: "",
        value = value ?: "",
        options = options?.map { optionItem ->
            OptionModel(
                hasChild = optionItem?.child ?: false,
                id = optionItem?.id ?: 0,
                name = optionItem?.name ?: "",
                parent = optionItem?.parent ?: 0,
                slug = optionItem?.slug ?: ""
            )
        } ?: emptyList()
    )
}
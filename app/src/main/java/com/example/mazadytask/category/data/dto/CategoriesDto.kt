package com.example.mazadytask.category.data.dto

import com.example.mazadytask.category.domain.model.CategoryModel
import com.example.mazadytask.category.domain.model.ChildrenModel
import com.google.gson.annotations.SerializedName

/**
 * created by Karim Haggagi on 1/5/25
 **/

data class CategoriesDto(
    @SerializedName("ads_banners")
    val adsBanners: List<AdsBanner?>? = null,
    val categories: List<Category?>? = null,
    @SerializedName("google_version")
    val googleVersion: String? = null,
    @SerializedName("huawei_version")
    val huaweiVersion: String? = null,
    @SerializedName("ios_latest_version")
    val iosLatestVersion: String? = null,
    @SerializedName("ios_version")
    val iosVersion: String? = null,
    val statistics: Statistics? = null
)

data class AdsBanner(
    val duration: Int? = null,
    val img: String? = null,
    @SerializedName("media_type")
    val mediaType: String? = null
)

data class Category(
    val children: List<Children?>? = null,
    @SerializedName("child_count")
    val circleIcon: String? = null,
    val description: Any? = null,
    @SerializedName("disable_shipping")
    val disableShipping: Int? = null,
    val id: Int? = null,
    val image: String? = null,
    val name: String? = null,
    val slug: String? = null
)

data class Statistics(
    val auctions: Int? = null,
    val products: Int? = null,
    val users: Int? = null
)

data class Children(
    val children: Any? = null,
    @SerializedName("child_count")
    val circleIcon: String? = null,
    val description: Any? = null,
    @SerializedName("disable_shipping")
    val disableShipping: Int? = null,
    val id: Int? = null,
    val image: String? = null,
    val name: String? = null,
    val slug: String? = null
)

fun CategoriesDto.asCategoryDomainModel(): List<CategoryModel> {

    return this.categories?.map { categoryItem ->
        CategoryModel(
            id = categoryItem?.id ?: 0,
            name = categoryItem?.name ?: "",
            slug = categoryItem?.slug ?: "",
            image = categoryItem?.image ?: "",
            circleIcon = categoryItem?.circleIcon ?: "",
            children = categoryItem?.children?.map { childrenItem ->
                ChildrenModel(
                    id = childrenItem?.id ?: 0,
                    name = childrenItem?.name ?: "",
                    slug = childrenItem?.slug ?: "",
                    image = childrenItem?.image ?: "",
                    circleIcon = childrenItem?.circleIcon ?: "",
                    disableShipping = childrenItem?.disableShipping ?: 0,
                )
            } ?: emptyList()
        )
    } ?: emptyList()
}
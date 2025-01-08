package com.example.mazadytask.category.data.dto

/**
 * created by Karim Haggagi on 1/6/25
 **/
data class ResponseModel<T>(
    val code: Int? = null,
    val data: T? = null,
    val msg: String? = null
)

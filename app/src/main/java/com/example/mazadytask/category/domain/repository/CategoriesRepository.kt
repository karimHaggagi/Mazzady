package com.example.mazadytask.category.domain.repository

import com.example.mazadytask.category.domain.model.CategoryModel
import com.example.mazadytask.category.domain.model.CategoryOptionsModel
import com.example.mazadytask.core.domain.DataError
import com.example.mazadytask.core.domain.Result

/**
 * created by Karim Haggagi on 1/5/25
 **/
interface CategoriesRepository {
    suspend fun getAllRepositories(): Result<List<CategoryModel>, DataError.Remote>
    suspend fun getCategoryProperties(categoryId: Int): Result<List<CategoryOptionsModel>, DataError.Remote>
    suspend fun getCategoryOptions(optionId: Int): Result<List<CategoryOptionsModel>, DataError.Remote>

}
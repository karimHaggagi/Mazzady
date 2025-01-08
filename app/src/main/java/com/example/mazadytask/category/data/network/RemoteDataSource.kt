package com.example.mazadytask.category.data.network

import com.example.mazadytask.category.data.dto.CategoriesDto
import com.example.mazadytask.category.data.dto.CategoryOptionsDto
import com.example.mazadytask.core.domain.DataError
import com.example.mazadytask.core.domain.Result

/**
 * created by Karim Haggagi on 1/5/25
 **/
interface RemoteDataSource {
    suspend fun getAllCategories(): Result<CategoriesDto, DataError.Remote>
    suspend fun getCategoryProperties(categoryId: Int):Result<List<CategoryOptionsDto>, DataError.Remote>
    suspend fun getCategoryOptions(optionId: Int):Result<List<CategoryOptionsDto>, DataError.Remote>

}
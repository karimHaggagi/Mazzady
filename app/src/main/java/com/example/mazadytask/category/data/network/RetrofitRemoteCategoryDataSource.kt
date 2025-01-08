package com.example.mazadytask.category.data.network

import com.example.mazadytask.category.data.dto.CategoriesDto
import com.example.mazadytask.category.data.dto.CategoryOptionsDto
import com.example.mazadytask.core.data.safeCall
import com.example.mazadytask.core.domain.DataError
import com.example.mazadytask.core.domain.Result
import javax.inject.Inject

/**
 * created by Karim Haggagi on 1/5/25
 **/
class RetrofitRemoteCategoryDataSource @Inject constructor(private val apiService: CategoryApiService) :
    RemoteDataSource {
    override suspend fun getAllCategories(): Result<CategoriesDto, DataError.Remote> {
        return safeCall<CategoriesDto> {
            apiService.getAllCategories()
        }
    }

    override suspend fun getCategoryProperties(categoryId: Int): Result<List<CategoryOptionsDto>, DataError.Remote> {
        return safeCall<List<CategoryOptionsDto>> {
            apiService.getCategoryProperties(categoryId)
        }
    }

    override suspend fun getCategoryOptions(optionId: Int): Result<List<CategoryOptionsDto>, DataError.Remote> {
        return safeCall<List<CategoryOptionsDto>> {
            apiService.getCategoryOptions(optionId)
        }
    }
}
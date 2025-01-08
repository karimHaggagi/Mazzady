package com.example.mazadytask.category.data.repoImp

import com.example.mazadytask.category.data.dto.asCategoryDomainModel
import com.example.mazadytask.category.data.dto.asCategoryPropertiesDomainModel
import com.example.mazadytask.category.data.network.RemoteDataSource
import com.example.mazadytask.category.domain.model.CategoryModel
import com.example.mazadytask.category.domain.model.CategoryOptionsModel
import com.example.mazadytask.category.domain.repository.CategoriesRepository
import com.example.mazadytask.core.domain.DataError
import com.example.mazadytask.core.domain.Result
import com.example.mazadytask.core.domain.map
import javax.inject.Inject

/**
 * created by Karim Haggagi on 1/5/25
 **/
class CategoryRepoImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CategoriesRepository {
    override suspend fun getAllRepositories(): Result<List<CategoryModel>, DataError.Remote> {
        return remoteDataSource.getAllCategories().map { it.asCategoryDomainModel() }
    }

    override suspend fun getCategoryProperties(categoryId: Int): Result<List<CategoryOptionsModel>, DataError.Remote> {
        return remoteDataSource.getCategoryProperties(categoryId)
            .map { dto -> dto.map { it.asCategoryPropertiesDomainModel() } }
    }

    override suspend fun getCategoryOptions(optionId: Int): Result<List<CategoryOptionsModel>, DataError.Remote> {
        return remoteDataSource.getCategoryOptions(optionId)
            .map { dto -> dto.map { it.asCategoryPropertiesDomainModel() } }
    }
}
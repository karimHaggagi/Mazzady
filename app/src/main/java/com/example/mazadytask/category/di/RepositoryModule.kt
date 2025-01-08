package com.example.mazadytask.category.di


import com.example.mazadytask.category.data.repoImp.CategoryRepoImpl
import com.example.mazadytask.category.domain.repository.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideCategoryRepository(categoryRepoImpl: CategoryRepoImpl): CategoriesRepository

}
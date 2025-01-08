package com.example.mazadytask.category.di

import com.example.mazadytask.category.data.network.RemoteDataSource
import com.example.mazadytask.category.data.network.RetrofitRemoteCategoryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * created by Karim Haggagi on 1/5/25
 **/
@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun provideCategoryDataSource(retrofitRemoteCategoryDataSource: RetrofitRemoteCategoryDataSource): RemoteDataSource


}
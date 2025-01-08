package com.example.mazadytask.core.di

import android.app.Application
import com.example.mazadytask.core.presentation.AndroidStringProvider
import com.example.mazadytask.core.presentation.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * created by Karim Haggagi on 1/6/25
 **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideString(androidStringProvider: AndroidStringProvider): StringProvider {
        return androidStringProvider
    }

}
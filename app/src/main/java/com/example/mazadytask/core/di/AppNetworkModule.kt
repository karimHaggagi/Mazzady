package com.example.mazadytask.core.di

import android.app.Application
import com.example.mazadytask.category.data.network.CategoryApiService
import com.example.mazadytask.core.data.AppConstants.BASE_URL
import com.example.mazadytask.core.data.HeaderInterceptor
import com.example.mazadytask.core.data.NetworkConnectionInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppNetworkModule {

    @Provides
    @Singleton
    fun provideNetworkInterceptor(application: Application): NetworkConnectionInterceptor =
        NetworkConnectionInterceptor(application)

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: NetworkConnectionInterceptor,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(interceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(
                60,
                TimeUnit.SECONDS
            )
            .writeTimeout(
                60,
                TimeUnit.SECONDS
            )
            .connectTimeout(
                60,
                TimeUnit.SECONDS
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CategoryApiService =
        retrofit.create(CategoryApiService::class.java)
}
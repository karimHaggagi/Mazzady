package com.example.mazadytask.category.data.network

import com.example.mazadytask.category.data.dto.CategoriesDto
import com.example.mazadytask.category.data.dto.CategoryOptionsDto
import com.example.mazadytask.category.data.dto.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * created by Karim Haggagi on 1/5/25
 **/
interface CategoryApiService {

    @GET("get_all_cats")
    suspend fun getAllCategories(): Response<ResponseModel<CategoriesDto>>

    @GET("properties")
    suspend fun getCategoryProperties(@Query("cat") categoryId: Int): Response<ResponseModel<List<CategoryOptionsDto>>>

    @GET("get-options-child/{optionId}")
    suspend fun getCategoryOptions(@Path("optionId") optionId: Int): Response<ResponseModel<List<CategoryOptionsDto>>>

}
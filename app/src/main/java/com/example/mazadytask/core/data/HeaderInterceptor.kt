package com.example.mazadytask.core.data

import com.example.mazadytask.core.data.AppConstants.HEADER_KEY
import com.example.mazadytask.core.data.AppConstants.HEADER_VALUE
import okhttp3.Interceptor
import okhttp3.Response

/**
 * created by Karim Haggagi on 1/5/25
 **/
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(HEADER_KEY, HEADER_VALUE)
            .build()
        return chain.proceed(request)
    }
}
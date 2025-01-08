package com.example.mazadytask.core.data

import com.example.mazadytask.category.data.dto.ResponseModel
import com.example.mazadytask.core.domain.DataError
import kotlinx.coroutines.ensureActive
import java.net.SocketTimeoutException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext
import com.example.mazadytask.core.domain.Result
import retrofit2.Response

/**
 * created by Karim Haggagi on 1/5/25
 **/
suspend inline fun <reified T> safeCall(execute: () -> Response<ResponseModel<T>>): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }
    return responseToResult(response)
}

inline fun <reified T> responseToResult(response: Response<ResponseModel<T>>): Result<T, DataError.Remote> {
    return when (response.code()) {
        in 200..299 -> {
            try {
                if (response.body() != null) {
                    val responseModel = response.body()!!
                    Result.Success(responseModel.data!!)
                } else {
                    throw Exception()
                }
            } catch (e: Exception) {
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }

        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Remote.SERVER)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }

}
package com.example.githubuserprofile.data.source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend inline fun <T : Any> safeApiCall(crossinline request: suspend () -> Response<T>): Flow<ApiResult<T>> =
    flow {
        try {
            val response = withContext(Dispatchers.IO) {
                request.invoke()
            }
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ApiResult.success(it))
                } ?: emit(ApiResult.failure("Server Response is Empty."))
            } else {
                emit(ApiResult.failure("Something went wrong! Please try again."))
            }
        } catch (e: Exception) {
            emit(ApiResult.failure(e.message.toString()))
        }
    }
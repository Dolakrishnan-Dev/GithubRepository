package com.example.githubuserprofile.data.source

data class ApiResult<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null)
        }

        fun <T> failure(msg: String, data: T? = null): ApiResult<T> {
            return ApiResult(Status.FAILURE, data, msg)
        }

    }
}

enum class Status {
    SUCCESS,
    FAILURE,
}
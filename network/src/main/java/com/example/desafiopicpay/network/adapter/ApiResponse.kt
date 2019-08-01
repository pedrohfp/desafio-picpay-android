package com.example.desafiopicpay.network.adapter

import retrofit2.Response

internal const val UNKNOWN_CODE = -1
internal const val NO_CONTENT_CODE = 204

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == NO_CONTENT_CODE) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                ApiErrorResponse(response.code(), response.errorBody()?.string() ?: response.message())
            }
        }

        fun <T> create(errorCode: Int, error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(errorCode, error.message ?: "Unknown Error!")
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()
data class ApiErrorResponse<T>(val errorCode: Int, val errorMessage: String) : ApiResponse<T>()
data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

package com.example.android_movies_app.api.responses

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object{
        fun <T> create(error: Throwable): ApiResponse<T>{
            return ApiErrorResponse(error.message ?: "Unknown Error, Check Network Connection")
        }




    }

}
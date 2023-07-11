package com.example.android_movies_app.api.responses

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object{
        fun <T> create(error: Throwable): ApiResponse<T>{
            return ApiErrorResponse(error.message ?: "Unknown Error, Check Network Connection")
        }


        fun <T> create(response: Response<T>): ApiResponse<T>{
            return if (response.isSuccessful){
                val body = response.body()
                if (body == null || response.code() == 204){
                    ApiEmptyResponse()
                }else{
                    ApiSuccessResponse(
                        body = body
                    )
                }
            }else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                }else {
                    msg
                }
                ApiErrorResponse(errorMsg ?: "Error, Check Network Connect!")
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()
data class ApiSuccessResponse<T>(val body: T): ApiResponse<T>()
data class ApiErrorResponse<T>(val errorMessage: String): ApiResponse<T>()
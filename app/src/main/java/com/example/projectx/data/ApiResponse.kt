package com.example.projectx.data

import retrofit2.Response

/**
 * This class is used for returning response from an API to repository classes
 */
sealed class ApiResponse<T> {

    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg ?: "unknown error", response.code())
            }
        }


        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error",throwable = error)
        }
    }


}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage:String,val errorCode:Int=0,val throwable: Throwable?=null):ApiResponse<T>()

data class ApiSuccessResponse<T>(val body:T):ApiResponse<T>()


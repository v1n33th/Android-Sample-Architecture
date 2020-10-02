package com.example.projectx.data


sealed class Resource<T> {
    data class SUCCESS<T>(val data: T) : Resource<T>()
    data class ERROR<T>(val errorMessage: String, val errorType: ErrorType = ErrorType.UNHANDLED) :
        Resource<T>()

    data class LOADING<T>(val loadingMessage: String? = null) : Resource<T>()
}
package com.example.projectx.utlities.retrofit

import com.example.projectx.data.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Used to wrap the response to our required format in the repository layer
 */
class RetrofitWrapperAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type?,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        return returnType?.let {
            try {
                when (getRawType(it)) {
                    Call::class.java -> {
                        val enclosingType = getParameterUpperBound(0, it as ParameterizedType)
                        when (getRawType(enclosingType)) {
                            ApiResponse::class.java -> {
                                val bodyType = getParameterUpperBound(0, enclosingType as ParameterizedType)
                                ApiResponseAdapter<Any>(bodyType)
                            }
                            else -> null
                        }
                    }
                    else -> null
                }
            } catch (ex: Exception) {
                null
            }
        }
    }
}
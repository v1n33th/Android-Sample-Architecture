package com.example.projectx.utlities.retrofit

import com.example.projectx.data.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResponseCall<T>(private val proxyCall: Call<T>):CallDelegate<T,ApiResponse<T>>(proxyCall) {
    override fun enqueueImpl(callback: Callback<ApiResponse<T>>) {
        proxyCall.enqueue(object : Callback<T>{
            override fun onFailure(call: Call<T>, t: Throwable) {
                val result=ApiResponse.create<T>(t)
                callback.onResponse(this@ApiResponseCall, Response.success(result))

            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result=ApiResponse.create(response)
                callback.onResponse(this@ApiResponseCall, Response.success(result))

            }

        })
    }

    override fun cloneImpl(): Call<ApiResponse<T>> = ApiResponseCall (proxyCall.clone())
}
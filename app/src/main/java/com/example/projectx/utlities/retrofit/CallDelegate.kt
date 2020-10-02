package com.example.projectx.utlities.retrofit

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CallDelegate<Tin,Tout>(private val proxyCall: Call<Tin>):Call<Tout> {

    abstract fun enqueueImpl(callback: Callback<Tout>)
    abstract fun cloneImpl():Call<Tout>

    override fun execute(): Response<Tout> {
        TODO("No need to be implemented")
    }

    final override fun enqueue(callback: Callback<Tout>) {
        enqueueImpl(callback)
    }

    final override fun clone(): Call<Tout> = cloneImpl()

    override fun cancel() = proxyCall.cancel()

    override fun request(): Request = proxyCall.request()

    override fun isExecuted(): Boolean = proxyCall .isExecuted

    override fun isCanceled(): Boolean = proxyCall.isCanceled

    override fun timeout(): Timeout = proxyCall.timeout()
}
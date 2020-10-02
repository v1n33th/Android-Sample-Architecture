package com.example.projectx.utlities.retrofit

import com.example.projectx.data.WebResponseWrapper
import com.squareup.moshi.Types
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

/**
 * Used to unwrap the enveloped response and pass it to next layer
 */
object WebResponseUnWrapAdapter : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val wrapperType = Types.newParameterizedType(WebResponseWrapper::class.java, type)
        val delegate: Converter<ResponseBody, WebResponseWrapper<Any>>? =
            retrofit.nextResponseBodyConverter(this, wrapperType, annotations)
        return UnWrapper(delegate)
    }
}

private class UnWrapper<T>(
    private val delegate: Converter<ResponseBody, WebResponseWrapper<T>>?
) : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T? {
        delegate?.convert(value)?.let {
            when (it.status) {
                1 -> return it.data
                else -> throw IOException(it.message ?: "Exception ")
            }
        }
        return null
    }
}
package com.example.projectx.data

import com.squareup.moshi.JsonClass

/**
 * This data class is used if web api response is enveloped
 */
@JsonClass(generateAdapter = true)
data class WebResponseWrapper<T>(
    val data: T?,
    val status: Int,
    val message: String?
)
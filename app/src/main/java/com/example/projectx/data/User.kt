package com.example.projectx.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(val userId:Int,val token:String)
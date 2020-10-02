package com.example.projectx.repository.webservices

import com.example.projectx.data.ApiResponse
import com.example.projectx.data.User
import retrofit2.http.POST

interface LoginInterface {
    @POST("/login")
    suspend fun getUser():ApiResponse<User>
}
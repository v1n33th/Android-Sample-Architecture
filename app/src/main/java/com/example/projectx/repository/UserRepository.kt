package com.example.projectx.repository

import com.example.projectx.data.*
import com.example.projectx.repository.webservices.LoginInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val loginInterface: LoginInterface){

    fun getUser()= flow{
        emit(Resource.LOADING<User>())
        delay(5000)
         when(val response=loginInterface.getUser()){
                is ApiSuccessResponse-> emit(Resource.SUCCESS(response.body))
                is ApiEmptyResponse -> emit(Resource.ERROR("",ErrorType.EMPTY_DATA))
                is ApiErrorResponse-> emit(Resource.ERROR(response.errorMessage,ErrorType.API_EXCEPTION))
        }
    }

}
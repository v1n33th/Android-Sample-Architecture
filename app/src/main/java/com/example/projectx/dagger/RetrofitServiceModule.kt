package com.example.projectx.dagger

import com.example.projectx.repository.webservices.LoginInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Use this module to add all your retrofit interfaces
 */
@Module
object RetrofitServiceModule {
    @Provides @JvmStatic
    fun provideLoginService(retrofit: Retrofit):LoginInterface{
      return retrofit.create(LoginInterface::class.java)
    }
}
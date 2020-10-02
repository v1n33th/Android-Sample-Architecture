package com.example.projectx.dagger

import com.example.projectx.utlities.retrofit.AddTokenInterceptor
import com.example.projectx.utlities.retrofit.BASE_URL
import com.example.projectx.utlities.retrofit.RetrofitWrapperAdapterFactory
import com.example.projectx.utlities.retrofit.WebResponseUnWrapAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RetroFitModule {

    @Provides @JvmStatic
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides @JvmStatic
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,addTokenInterceptor: AddTokenInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(addTokenInterceptor)
            .build()
    }

    /**
     * WebResponseUnWrapAdapter->use this adapter only if your api response is enveloped
     * RetrofitWrapperAdapterFactory->This adapter is used to transform the response to our required format.
     */
    @Provides @JvmStatic
    fun getRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(WebResponseUnWrapAdapter)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RetrofitWrapperAdapterFactory())
            .client(okHttpClient)
            .build()
    }

}
package com.example.projectx.utlities.retrofit

import com.example.projectx.utlities.EncryptedSharedPref
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Can be used to token to all your web requests
 */
@Singleton
class AddTokenInterceptor @Inject constructor(private val encryptedSharedPref: EncryptedSharedPref):Interceptor {
    var token:String? = encryptedSharedPref.getToken()

    override fun intercept(chain: Interceptor.Chain): Response {

        val request =chain.request()
        val requestBuilder =request.newBuilder()
        if(token==null){
            token=encryptedSharedPref.getToken()
        }
        if(request.header(NO_AUTHENTICATE)!=null){
            token?.let {
                requestBuilder.addHeader(AUTH_TOKEN,it)
            }?:throw IOException(NO_TOKEN)
        }
        return chain.proceed(request)

    }
}
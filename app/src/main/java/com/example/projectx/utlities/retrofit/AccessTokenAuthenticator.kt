package com.example.projectx.utlities.retrofit

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * Authenitcator will be called if 401 is returned by server indicating not authenticated state
 * Can be added to retrofit module
 */

class AccessTokenAuthenticator @Inject constructor(
) : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
        try {
            val token = "getToken()"//get your token here
            /**
             * In synchronized block as multiple api calls would return 401 at same time,but token needs to be fetched only once
             */
            synchronized(this)
            {
                val newToken = "getToken()" ?: throw IOException("Not Authenticated")//get token again as it may be already been fetched
                if (newToken != token) {//check if new token is different means a new token has been retrieved so continue with the request
                    return response.request
                        .newBuilder()
                        .header(AUTH_TOKEN, newToken)
                        .build()
                }
                var retryCount = 0

                while (retryCount < 3) {//retry login
                    var newToken=""
                    runBlocking {  //if retrofit uses suspend function use run blocking to execute it synchronzily.This wont cause any background issues as this whole method is called in background thread
                       newToken="suc"
                    }
                    if(newToken.isNotEmpty()) {//continue with request on success
                        return response.request
                            .newBuilder()
                            .header(AUTH_TOKEN, newToken)
                            .build()
                    }

                    ++retryCount
                }
                throw IOException("not authenticated")
            }

        } catch (ex: Exception) {
            Timber.e(ex)
            throw IOException("not authenticated")

        }

    }
}
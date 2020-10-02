package com.example.projectx.utlities

import android.content.SharedPreferences
import com.example.projectx.dagger.EncryptedSharedPref
import dagger.Reusable
import javax.inject.Inject

/**
 * This is part of crypto library and can be used to store sensitive information
 */
@Reusable
class EncryptedSharedPref @Inject constructor(@EncryptedSharedPref val encryptedSharedPref: SharedPreferences) {

    fun getToken():String?{
        return encryptedSharedPref.getString("token",null)
    }
    fun setToken(token:String){
        encryptedSharedPref.edit()
            .putString("token", token)
            .apply()
    }
}
package com.example.projectx.dagger

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.projectx.R
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GeneralSharedPref


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserSharedPref

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EncryptedSharedPref

/**
 * This modules shows how qualifier can be used to give two instances of a same class
 */

@Module
object SharedPreferenceModule {

    @Provides
    @JvmStatic
    @GeneralSharedPref
    fun provideGeneralSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.general_shared_pref),
            Context.MODE_PRIVATE
        )

    @Provides
    @JvmStatic
    @UserSharedPref
    fun provideUserSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.general_shared_pref),
            Context.MODE_PRIVATE
        )

    @Provides
    @JvmStatic
    @EncryptedSharedPref
    fun provideSecureSharedPreference(context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            context.getString(R.string.crypto_shared_pref),
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }

}
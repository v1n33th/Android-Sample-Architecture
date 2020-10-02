package com.example.projectx

import android.app.Application
import com.example.projectx.dagger.ApplicationComponent
import com.example.projectx.dagger.ComponentProvider
import com.example.projectx.dagger.DaggerApplicationComponent
import timber.log.Timber
import kotlin.system.exitProcess

/**
 * Main application classes
 * Timber Tree is planted to manage logs easy
 */
class MyApplication:Application(),ComponentProvider {

    override val component:ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Thread.setDefaultUncaughtExceptionHandler { _, paramThrowable ->
            //Catch your exception
            // Without System.exit() this will not work.
            Timber.e(paramThrowable)
            exitProcess(2)
        }
    }


}


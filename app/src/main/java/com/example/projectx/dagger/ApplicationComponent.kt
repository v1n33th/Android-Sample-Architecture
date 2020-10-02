package com.example.projectx.dagger

import android.content.Context
import com.example.projectx.repository.UserRepository
import com.example.projectx.viewmodels.MainActivityDisplayViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * This defines the entry point to your dependencies.
 */
@Component(modules = [SharedPreferenceModule::class,RetroFitModule::class,RetrofitServiceModule::class,SharedPreferenceModule::class])
@Singleton
interface ApplicationComponent {

    @Component.Factory
    interface Factory{
        fun create(
         @BindsInstance applicationContext:Context
        ):ApplicationComponent
    }

    val mainActivityDisplayViewModel:MainActivityDisplayViewModel
}
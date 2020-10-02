@file:Suppress("UNCHECKED_CAST")

package com.example.projectx.dagger

import android.app.Activity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels


val Activity.injector
    get() =
        (application as ComponentProvider).component

/**
 * Can be used inject View models in a fragment class
 */
val Fragment.injector
    get() =
        (requireActivity().application as ComponentProvider).component


/**
 * Function used to initialize view model of activity
 */
inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    crossinline provider: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }
}

inline fun <reified T : ViewModel> Fragment.viewModel(
    crossinline provider: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }
}

inline fun <reified T : ViewModel> Fragment.activityViewModel(
    crossinline provider: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }
}

/**
 * Use this method to inject viewmodels by nav graph.
 * Suggested way of getting VM's
 */
inline fun <reified T : ViewModel> Fragment.navViewModel(
    resId: Int,
    crossinline provider: () -> T
) = navGraphViewModels<T>(resId) {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return provider() as T
        }
    }

}


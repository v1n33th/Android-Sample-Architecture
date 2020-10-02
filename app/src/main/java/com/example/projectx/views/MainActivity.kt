package com.example.projectx.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.projectx.data.User
import com.example.projectx.databinding.ActivityMainBinding
import com.example.projectx.dagger.injector
import com.example.projectx.dagger.viewModel
import com.example.projectx.data.Resource
import com.example.projectx.data.Status
import com.example.projectx.viewmodels.MainActivityDisplayViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity() {
    /**
     * This is used to setup binding.
     */
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    /**
     * Use injection By Nav ViewModels if navigation component is implemented
     * Also better to use navigation scopes to limit the scope of your object than dagger scopes
     */
    private val mainActivityViewModel:MainActivityDisplayViewModel by viewModel{
        injector.mainActivityDisplayViewModel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setDisplay()
    }

    private fun setDisplay() {
        mainActivityViewModel.response.observe(this,loginObserver)
        binding.txtHelloWorld.setOnClickListener {
            mainActivityViewModel.getUser()
        }


    }

    private val loginObserver=Observer<Resource<User>>{
        when(it){
            is Resource.SUCCESS -> {
                binding.txtHelloWorld.text="${it.data?.userId}  ${it.data?.token}"
            }
            is Resource.ERROR -> binding.txtHelloWorld.text=it.errorMessage
            is Resource.LOADING -> binding.txtHelloWorld.text="Loading"
        }
    }
}

package com.example.projectx.viewmodels


import androidx.lifecycle.*
import com.example.projectx.data.Resource
import com.example.projectx.data.User
import com.example.projectx.repository.UserRepository
import javax.inject.Inject

/**
 * Sample view model class
 */
class MainActivityDisplayViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _response: MediatorLiveData<Resource<User>> = MediatorLiveData()

    val response: LiveData<Resource<User>>
        get() = _response

    fun getUser() {
        _response.addSource(
            userRepository
                .getUser()
                .asLiveData(viewModelScope.coroutineContext)
        ) {
            _response.value = it
        }
    }
}
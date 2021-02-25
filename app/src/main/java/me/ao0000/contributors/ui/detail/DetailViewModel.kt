package me.ao0000.contributors.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.ao0000.contributors.model.User
import me.ao0000.contributors.repository.Repository

class DetailViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    fun fetchUser(userName: String) {
        viewModelScope.launch {
            _user.value = repository.getUser(userName)
        }
    }
    
}
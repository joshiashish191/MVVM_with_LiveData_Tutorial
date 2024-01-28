package net.softglobe.mvvmarchitecturetutorial.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.softglobe.mvvmarchitecturetutorial.model.Respository
import net.softglobe.mvvmarchitecturetutorial.model.User
import net.softglobe.mvvmarchitecturetutorial.model.UserResponse

class MainViewModel(context: Context) : ViewModel() {
    private var repository : Respository
    init {
        repository = Respository(context)
    }

    private val _userData = MutableLiveData<UserResponse>()

    val userData : LiveData<UserResponse>
        get() = _userData

    fun getUsers() : LiveData<List<User>> {
        return repository.getUsers()
    }

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)

//            val result = repository.getUsers()
//            _userData.postValue(UserResponse(result))
        }
    }
}
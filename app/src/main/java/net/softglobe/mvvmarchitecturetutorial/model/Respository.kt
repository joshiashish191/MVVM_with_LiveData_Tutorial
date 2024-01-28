package net.softglobe.mvvmarchitecturetutorial.model

import android.content.Context
import androidx.lifecycle.LiveData

class Respository(context: Context) {
    var userDatabase : UserDatabase
    init {
        userDatabase = UserDatabase.getInstance(context)
    }

    fun getUsers() : LiveData<List<User>> {
        return userDatabase.userDao.getUsers()
    }

    suspend fun insertUser(user: User) {
        userDatabase.userDao.insertUser(user)
    }
}
package com.juanlugg8.fitnessmanager.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.juanlugg8.fitnessmanager.entity.User
import com.juanlugg8.fitnessmanager.repository.UserRepository

class UserViewModel : ViewModel() {
    var allUsers = UserRepository.selectAllUserList().asLiveData()
    var id = MutableLiveData<Int>()
    var name = MutableLiveData<String>()
    var phone = MutableLiveData<String>()
    var state = MutableLiveData<UserState>()
    fun validate() {
        when {
            name.value.isNullOrBlank() -> state.value = UserState.NameIsMandatoryError
            else -> state.value = UserState.Success
        }
    }

    fun makeUser() {
        if (id.value == null)
            id.value = UserRepository.selectAllUserListRAW().lastOrNull()?.id?.plus(1)
        val user = User(id.value!!, name.value!!, phone.value!!)
        UserRepository.insertUser(user)
    }

    fun deleteUser(user: User) {
        UserRepository.deleteUser(user)
    }

    fun getState(): LiveData<UserState> {
        return state
    }
}
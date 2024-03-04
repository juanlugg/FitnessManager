package com.juanlugg8.fitnessmanager.usecase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.juanlugg8.fitnessmanager.repository.UserRepository

class UserViewModel : ViewModel() {
    var allUsers = UserRepository.selectAllUserList().asLiveData()
}
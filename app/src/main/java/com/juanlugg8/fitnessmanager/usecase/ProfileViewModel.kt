package com.juanlugg8.fitnessmanager.usecase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.juanlugg8.fitnessmanager.repository.ProfileRepository
import com.juanlugg8.fitnessmanager.repository.UserRepository

class ProfileViewModel : ViewModel() {
    var allProfiles = ProfileRepository.selectAllProfileList().asLiveData()

}
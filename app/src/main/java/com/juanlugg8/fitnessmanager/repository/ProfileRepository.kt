package com.juanlugg8.fitnessmanager.repository

import com.juanlugg8.fitnessmanager.database.FitnessDatabase
import com.juanlugg8.fitnessmanager.entity.Profile
import com.juanlugg8.fitnessmanager.entity.User
import com.juanlugg8.fitnessmanager.utils.Resources
import kotlinx.coroutines.flow.Flow

class ProfileRepository {
    companion object {
        fun insertUser(profile: Profile) {
            try {
                FitnessDatabase.getInstance().profileDao().insert(profile)
                Resources.Success(profile)
            } catch (e: Exception) {
                Resources.Error(e)
            }
        }

        fun selectAllProfileList(): Flow<List<Profile>> {
            return FitnessDatabase.getInstance().profileDao().selectAll()
        }

        fun selectAllProfileListRAW(): List<Profile> {
            return FitnessDatabase.getInstance().profileDao().selectAllRAW()
        }
    }
}
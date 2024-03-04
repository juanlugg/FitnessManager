package com.juanlugg8.fitnessmanager.repository

import com.juanlugg8.fitnessmanager.database.FitnessDatabase
import com.juanlugg8.fitnessmanager.entity.User
import com.juanlugg8.fitnessmanager.utils.Resources
import kotlinx.coroutines.flow.Flow

class UserRepository {
    companion object{
        fun insertUser(user: User){
            try {
                FitnessDatabase.getInstance().userDao().insert(user)
                Resources.Success(user)
            } catch (e: Exception) {
                Resources.Error(e)
            }
        }
        fun selectAllUserList() : Flow<List<User>> {
            return FitnessDatabase.getInstance().userDao().selectAll()
        }
        fun selectAllUserListRAW() : List<User> {
            return FitnessDatabase.getInstance().userDao().selectAllRAW()
        }
    }
}
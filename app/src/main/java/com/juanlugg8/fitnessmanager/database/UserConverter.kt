package com.juanlugg8.fitnessmanager.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.juanlugg8.fitnessmanager.entity.User
import com.juanlugg8.fitnessmanager.repository.UserRepository

@ProvidedTypeConverter
class UserConverter {
    @TypeConverter
    fun toUser(id: Int): User? {
        return UserRepository.selectAllUserListRAW().find { it.id == id }
    }

    @TypeConverter
    fun fromUser(user: User): Int {
        return user.id
    }
}
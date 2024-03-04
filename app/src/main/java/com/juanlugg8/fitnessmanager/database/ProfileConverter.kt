package com.juanlugg8.fitnessmanager.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.juanlugg8.fitnessmanager.entity.Profile

@ProvidedTypeConverter
class ProfileConverter {
    @TypeConverter
    fun toProfile(id : Int): Profile?{
        return null
    }
    @TypeConverter
    fun toId(profile: Profile) : Int{
        return profile.id
    }
}
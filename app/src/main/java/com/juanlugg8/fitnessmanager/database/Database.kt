package com.juanlugg8.fitnessmanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.juanlugg8.fitnessmanager.entity.Profile
import com.juanlugg8.fitnessmanager.entity.User

@Database(entities = [User::class, Profile::class], version = 1, exportSchema = false)
@TypeConverters(ProfileConverter::class)
abstract class Database : RoomDatabase() {
}
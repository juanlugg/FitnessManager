package com.juanlugg8.fitnessmanager.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.juanlugg8.fitnessmanager.database.UserConverter
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String
) : Serializable

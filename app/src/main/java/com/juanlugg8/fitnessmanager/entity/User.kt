package com.juanlugg8.fitnessmanager.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.juanlugg8.fitnessmanager.database.ProfileConverter
import java.io.Serializable

@Entity(
    tableName = "user", foreignKeys = [ForeignKey(
        entity = Profile::class, parentColumns = arrayOf("id"), childColumns = arrayOf("profile"),
        onDelete = ForeignKey.RESTRICT, onUpdate = ForeignKey.CASCADE
    )]
)
data class User(
    @PrimaryKey
    val id: Int,
    @TypeConverters(ProfileConverter::class)
    val profile: Profile,
    val name: String,
    val phone: String
) : Serializable

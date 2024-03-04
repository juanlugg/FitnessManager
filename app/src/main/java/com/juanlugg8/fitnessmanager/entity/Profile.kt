package com.juanlugg8.fitnessmanager.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.juanlugg8.fitnessmanager.database.UserConverter
import java.io.Serializable

@Entity(
    tableName = "profile", foreignKeys = [ForeignKey(
        entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("user"),
        onDelete = ForeignKey.RESTRICT, onUpdate = ForeignKey.CASCADE
    )]
)
data class Profile(
    @PrimaryKey
    val id: Int,
    @TypeConverters(UserConverter::class)
    val user: User,
    val dateStarted: String,
    val height: Double,
    val weight: Double
) : Serializable
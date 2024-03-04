package com.juanlugg8.fitnessmanager.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey
    val id: Int, val dateStarted: String, val height: Double, val weight: Double)
package com.juanlugg8.fitnessmanager.entity

import androidx.room.Dao
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert(onConflict = ForeignKey.RESTRICT)
    fun insert(profile: Profile)
    @Query("SELECT * FROM profile")
    fun selectAll() : Flow<List<Profile>>
    @Query("SELECT * FROM profile")
    fun selectAllRAW() : List<Profile>
}
package com.juanlugg8.fitnessmanager.entity

import androidx.room.Dao
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = ForeignKey.RESTRICT)
    fun insert(user : User)

    @Query("SELECT * FROM user")
    fun selectAll() : Flow<List<User>>
    @Query("SELECT * FROM user")
    fun selectAllRAW() : List<User>
}
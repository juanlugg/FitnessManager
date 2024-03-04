package com.juanlugg8.fitnessmanager

import com.google.common.truth.Truth
import com.juanlugg8.fitnessmanager.entity.Profile
import com.juanlugg8.fitnessmanager.entity.User
import org.junit.Test

class UserTest {
    val user = User(1,"Juanlu","666777888")
    @Test
    fun `user is Instance of User`(){
        Truth.assertThat(user).isInstanceOf(User::class.java)
    }
    @Test
    fun `user create`() {
        val id = 1
        val name = "Juanlu"
        val phone = "666777888"
        Truth.assertThat(user).isEqualTo(User(id,name,phone))
    }
    @Test
    fun `userId is equals 1`(){
        Truth.assertThat(user.id).isEqualTo(1)
    }
}
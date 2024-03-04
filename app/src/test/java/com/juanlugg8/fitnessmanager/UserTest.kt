package com.juanlugg8.fitnessmanager

import com.google.common.truth.Truth
import com.juanlugg8.fitnessmanager.entity.User
import org.junit.Test

class UserTest {
    val user = User(1,"Juanlu","666777888")
    @Test
    fun `user is Instance of User`(){
        Truth.assertThat(user).isInstanceOf(User::class.java)
    }

}
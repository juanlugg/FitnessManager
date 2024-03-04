package com.juanlugg8.fitnessmanager

import com.google.common.truth.Truth
import com.juanlugg8.fitnessmanager.entity.Profile
import com.juanlugg8.fitnessmanager.entity.User
import com.juanlugg8.fitnessmanager.repository.UserRepository
import org.junit.Test

class ProfileTest {
    val user = User(1,"Juanlu","666777888")
    val profile = Profile(1, user, "04/03/2024",180.0,80.0)
    @Test
    fun `profile is Instance of Profile`(){
        Truth.assertThat(profile).isInstanceOf(Profile::class.java)
    }
    @Test
    fun `user is correct`(){
        val user = User(1,"Juanlu","666777888")
        Truth.assertThat(profile.user).isEqualTo(user)
    }
}
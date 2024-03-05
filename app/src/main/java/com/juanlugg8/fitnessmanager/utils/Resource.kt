package com.juanlugg8.fitnessmanager.utils

sealed class Resources {
    data class Success<T>(var data: T?) :
        Resources()

    data class Error(var exception: Exception) : Resources()
}
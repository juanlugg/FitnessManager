package com.juanlugg8.fitnessmanager.usecase

sealed class UserState {
    data object NameIsMandatoryError : UserState()
    data object Success : UserState()
}

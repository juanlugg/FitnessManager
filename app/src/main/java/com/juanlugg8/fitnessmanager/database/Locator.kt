package com.juanlugg8.fitnessmanager.database

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.juanlugg8.fitnessmanager.preferences.PreferencesRepository

object Locator {
    public var application: Application? = null

    public inline val requiredApplication
        get()= application ?: error("Missing call: initwith(application)")

    fun initWith(application:Application){
        this.application = application
    }
    private val Context.userStore by preferencesDataStore(name = "user_preferences")

    private val Context.apiPreferences by preferencesDataStore(name ="settings")

    //by lazy se inicializas la primera vez que la llames
    val PreferencesRepository by lazy {
        PreferencesRepository(requiredApplication.userStore)
    }
    /*val settingsPreferencesRepository by lazy {
        DataStorePreferencesRepository(requiredApplication.apiPreferences)
    }*/
}
package com.juanlugg8.fitnessmanager.database

import android.app.Application

class FitnessAplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Locator.initWith(this)
    }
}
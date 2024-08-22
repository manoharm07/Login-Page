package com.example.login.room

import android.app.Application
import androidx.room.Room

class Main : Application() {

    companion object {
        lateinit var login_db : database
    }

    override fun onCreate() {
        super.onCreate()
        login_db = Room.databaseBuilder(
            applicationContext,
            database :: class.java,
            database.NAME,
        ).build()
    }
}
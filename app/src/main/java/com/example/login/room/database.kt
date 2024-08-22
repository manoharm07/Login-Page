package com.example.login.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [data::class], version = 1)
abstract class database : RoomDatabase() {

    companion object {
        const val NAME = "user_info"
    }

    abstract fun getuserdao() : logindao
}

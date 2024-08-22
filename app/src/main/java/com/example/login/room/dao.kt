package com.example.login.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface logindao{

    @Query("SELECT * FROM data")
    fun getuserinfo() : List<data>

    @Insert
    fun adduser(data : data)
}
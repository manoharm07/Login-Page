package com.example.login.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class data(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val username : String = "",
    val mail : String = "",
    val password : String = ""
)
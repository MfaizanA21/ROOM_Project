package com.example.roomproject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val key: Int,
    val name: String,
    val message: String
)

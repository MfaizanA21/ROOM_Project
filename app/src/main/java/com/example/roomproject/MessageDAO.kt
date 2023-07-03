package com.example.roomproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDAO {

    @Insert
    suspend fun sendMessage(messages: Message)

    @Query("SELECT * FROM Messages")
    fun getMessage(): LiveData<List<Message>>

}
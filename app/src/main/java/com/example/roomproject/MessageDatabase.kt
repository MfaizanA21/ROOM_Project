package com.example.roomproject

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Message::class], version = 1)
 abstract class MessageDatabase(): RoomDatabase() {

     abstract fun messageDao(): MessageDAO

    companion object {

        @Volatile
        private var INSTANCE: MessageDatabase? = null

        fun getDatabase(context: Context?): MessageDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = context?.applicationContext?.let {
                        Room.databaseBuilder(
                            it,
                            MessageDatabase::class.java,
                            "MessagesDB"
                        ).build()
                    }
                }

            }
            return INSTANCE!!

        }
    }
    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }
}
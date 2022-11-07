package com.example.rssfeedapp.data

import androidx.room.Room
import com.example.rssfeedapp.RSSFeedApp

object RSSFeedDatabaseBuilder {
    private var instance: RSSFeedDatabase? = null

    fun getInstance(): RSSFeedDatabase {
        synchronized(RSSFeedDatabase::class) {
            if (instance == null) {
                instance = buildDatabase()
            }
        }
        return instance!!
    }

    private fun buildDatabase(): RSSFeedDatabase {
        return Room.databaseBuilder(
            RSSFeedApp.application,
            RSSFeedDatabase::class.java,
            RSSFeedDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
package com.example.rssfeedapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssfeedapp.model.RSSFeed

@Database(entities = [RSSFeed::class], version = 1)
abstract class RSSFeedDatabase : RoomDatabase() {

    abstract fun rssFeedDao(): RSSFeedDao

    companion object {
        const val NAME = "rssFeeds"
    }
}
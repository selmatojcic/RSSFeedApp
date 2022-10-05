package com.example.rssfeedapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rssfeedapp.model.RSSFeed

@Dao
interface RSSFeedDao {

    @Query("SELECT * FROM rssFeeds")
    fun getFeeds() : List<RSSFeed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rssFeed: RSSFeed)

    @Query("DELETE FROM rssFeeds")
    fun deleteAll()
}
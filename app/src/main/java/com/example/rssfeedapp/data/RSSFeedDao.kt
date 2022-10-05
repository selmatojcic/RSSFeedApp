package com.example.rssfeedapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rssfeedapp.model.RSSFeed

@Dao
interface RSSFeedDao {
    @Query("SELECT * FROM rssFeeds")
    fun getRSSFeeds(): LiveData<List<RSSFeed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRSSFeed(rssFeed: RSSFeed)

    @Query("DELETE FROM rssFeeds")
    fun deleteAll()
}
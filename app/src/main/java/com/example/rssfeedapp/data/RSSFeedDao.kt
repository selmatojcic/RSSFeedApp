package com.example.rssfeedapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rssfeedapp.model.RSSFeed

@Dao
interface RSSFeedDao {
    @Query("SELECT * FROM rssFeeds")
    fun getRSSFeeds(): LiveData<List<RSSFeed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRSSFeed(rssFeed: RSSFeed)

    @Delete
    suspend fun deleteRSSFeed(rssFeed: RSSFeed)
}
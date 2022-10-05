package com.example.rssfeedapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeedapp.data.RSSFeedDatabaseBuilder
import com.example.rssfeedapp.data.RSSFeedRepository
import com.example.rssfeedapp.model.RSSFeed

class RSSFeedViewModel : ViewModel() {
    private val rssFeedDao = RSSFeedDatabaseBuilder.getInstance().rssFeedDao()
    private val rssFeedRepository = RSSFeedRepository(rssFeedDao)

    fun insertRSSFeed(rssFeed: RSSFeed) {
        rssFeedRepository.insertRSSFeed(rssFeed)
    }

    fun getRSSFeeds(): LiveData<List<RSSFeed>> {
        return rssFeedRepository.getRSSFeeds()
    }

    fun deleteAllRSSFeeds() {
        return rssFeedRepository.deleteAllRSSFeeds()
    }
}
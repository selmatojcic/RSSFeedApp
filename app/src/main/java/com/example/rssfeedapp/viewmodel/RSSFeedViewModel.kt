package com.example.rssfeedapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeedapp.data.RSSFeedDatabaseBuilder
import com.example.rssfeedapp.data.RSSFeedRepository
import com.example.rssfeedapp.model.Channel
import com.example.rssfeedapp.model.Item
import com.example.rssfeedapp.model.RSSFeed

class RSSFeedViewModel : ViewModel() {
    private val rssFeedDao = RSSFeedDatabaseBuilder.getInstance().rssFeedDao()
    private val rssFeedRepository = RSSFeedRepository(rssFeedDao)

    fun insertRSSFeed(rssFeed: RSSFeed) {
        rssFeedRepository.insertRSSFeed(rssFeed)
    }

    fun deleteRSSFeed(rssFeed: RSSFeed) {
        rssFeedRepository.deleteRSSFeed(rssFeed)
    }

    fun getRSSFeeds(): LiveData<List<RSSFeed>> {
        return rssFeedRepository.getRSSFeeds()
    }

    fun deleteAllRSSFeeds() {
        return rssFeedRepository.deleteAllRSSFeeds()
    }

    fun loadRSSFeedItems(channel: Channel) : List<Item>{
        val itemList: MutableList<Item> = mutableListOf()
        for (item in channel.items) {
            itemList.add(item)
        }
        Log.e("AAAA", itemList.toString())
        return itemList
    }
}
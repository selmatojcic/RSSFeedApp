package com.example.rssfeedapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.rssfeedapp.data.RSSFeedDatabaseBuilder
import com.example.rssfeedapp.data.RSSFeedRepository
import com.example.rssfeedapp.model.Channel
import com.example.rssfeedapp.model.Item
import com.example.rssfeedapp.model.RSSFeed

class RSSFeedViewModel(androidApplication: Application) : AndroidViewModel(androidApplication) {
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

    fun loadRSSFeedItems(channel: Channel): List<Item> {
        val itemList: MutableList<Item> = mutableListOf()
        for (item in channel.items) {
            itemList.add(item)
        }
        return itemList
    }

    fun changeLinkProtocol(link: String): String {
        val oldPrefix = "http://"
        val newPrefix = "https://"
        var newLink = ""
        if (link.startsWith(oldPrefix)) {
            newLink = link.replace(oldPrefix, newPrefix)
            return newLink
        }
        return link
    }
}
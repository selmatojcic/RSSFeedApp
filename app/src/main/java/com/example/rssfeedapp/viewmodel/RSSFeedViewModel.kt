package com.example.rssfeedapp.viewmodel

import android.app.Application
import android.view.View
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

    fun loadRSSFeeds(link: String) {
//        fragmentRssFeedsBinding.progressBar.visibility = View.VISIBLE
//        fragmentRssFeedsBinding.addLinkTextInputEditText.text?.clear()
//        fragmentRssFeedsBinding.progressBar.visibility = View.GONE
        rssFeedRepository.loadRSSFeeds(link)
    }

    fun loadRSSFeedItems(channel: Channel): List<Item> {
        val itemList: MutableList<Item> = mutableListOf()
        for (item in channel.items) {
            itemList.add(item)
        }
        return itemList
    }
}
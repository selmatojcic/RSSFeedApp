package com.example.rssfeedapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rssfeedapp.data.RSSFeedDatabaseBuilder
import com.example.rssfeedapp.data.RSSFeedRepository
import com.example.rssfeedapp.model.Item
import com.example.rssfeedapp.model.RSSFeed
import kotlinx.coroutines.launch

class RSSFeedViewModel(androidApplication: Application) : AndroidViewModel(androidApplication) {
    private val rssFeedDao = RSSFeedDatabaseBuilder.getInstance().rssFeedDao()
    private val rssFeedRepository = RSSFeedRepository(rssFeedDao)

    fun deleteRSSFeed(rssFeed: RSSFeed) {
        rssFeedRepository.deleteRSSFeed(rssFeed)
    }

    fun getRSSFeeds(): LiveData<List<RSSFeed>> {
        return rssFeedRepository.getRSSFeeds()
    }

    fun loadRSSFeeds(url: String) {
//        fragmentRssFeedsBinding.progressBar.visibility = View.VISIBLE
//        fragmentRssFeedsBinding.addLinkTextInputEditText.text?.clear()
//        fragmentRssFeedsBinding.progressBar.visibility = View.GONE
        viewModelScope.launch {
            rssFeedRepository.loadRSSFeeds(url)
        }
    }

    suspend fun loadRSSFeedItems(url: String): List<Item> {
        return rssFeedRepository.loadRSSFeedItems(url)
    }
}
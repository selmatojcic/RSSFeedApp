package com.example.rssfeedapp.data

import androidx.lifecycle.LiveData
import com.example.rssfeedapp.model.RSSFeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RSSFeedRepository(private val rssFeedDao: RSSFeedDao) {
    fun getRSSFeeds(): LiveData<List<RSSFeed>> {
        return rssFeedDao.getRSSFeeds()
    }

    fun insertRSSFeed(rssFeed: RSSFeed) {
        CoroutineScope(IO).launch {
            rssFeedDao.insertRSSFeed(rssFeed)
        }
    }

    fun deleteAllRSSFeeds() = rssFeedDao.deleteAll()
}
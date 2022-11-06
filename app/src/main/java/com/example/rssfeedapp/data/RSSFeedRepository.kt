package com.example.rssfeedapp.data

import androidx.lifecycle.LiveData
import com.example.rssfeedapp.model.Item
import com.example.rssfeedapp.model.RSSFeed
import com.example.rssfeedapp.networking.RSSFeedApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RSSFeedRepository {
    private val rssFeedDao = RSSFeedDatabaseBuilder.getInstance().rssFeedDao()

    fun getRSSFeeds(): LiveData<List<RSSFeed>> {
        return rssFeedDao.getRSSFeeds()
    }

    private fun insertRSSFeed(rssFeed: RSSFeed) {
        CoroutineScope(IO).launch {
            rssFeedDao.insertRSSFeed(rssFeed)
        }
    }

    fun deleteRSSFeed(rssFeed: RSSFeed) {
        CoroutineScope(IO).launch {
            rssFeedDao.deleteRSSFeed(rssFeed)
        }
    }

    suspend fun loadRSSFeeds(url: String) {
        val api = RSSFeedApi.create()
        val rss = api.getRSS(url)

        insertRSSFeed(
            RSSFeed(
                0,
                rss.channel.title,
                rss.channel.description,
                rss.channel.image.url,
                url
            )
        )
    }

    suspend fun loadRSSFeedItems(url: String): List<Item> {
        val itemList: MutableList<Item> = mutableListOf()

        val api = RSSFeedApi.create()
        val rss = api.getRSS(url)

        for (item in rss.channel.items) {
            itemList.add(item)
        }

        return itemList
    }
}
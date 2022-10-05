package com.example.rssfeedapp.data

import com.example.rssfeedapp.model.RSSFeed

class RSSFeedRepository(private val rssFeedDao: RSSFeedDao) {

    fun getFeeds() : List<RSSFeed> = rssFeedDao.getFeeds()
    fun insertFeed(rssFeed: RSSFeed) = rssFeedDao.insert(rssFeed)
    fun deleteAll() = rssFeedDao.deleteAll()
}
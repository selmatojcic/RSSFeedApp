package com.example.rssfeedapp.listeners

import com.example.rssfeedapp.model.RSSFeed

interface OnRSSFeedClickedListener {
    fun onRSSFeedClicked(rssFeed: RSSFeed)
}
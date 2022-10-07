package com.example.rssfeedapp

import com.example.rssfeedapp.model.RSSFeed

interface OnRSSFeedClickedListener {
    fun onRSSFeedClicked(rssFeed: RSSFeed)
}
package com.example.rssfeedapp

import com.example.rssfeedapp.model.RSSFeed

interface OnRSSFeedSelectedListener {
    fun onRSSFeedSelected(rssFeed: RSSFeed)
}
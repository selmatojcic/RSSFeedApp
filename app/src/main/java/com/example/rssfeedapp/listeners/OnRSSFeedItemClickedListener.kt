package com.example.rssfeedapp.listeners

import com.example.rssfeedapp.model.Item

interface OnRSSFeedItemClickedListener {
    fun onRSSFeedItemClicked(item: Item)
}
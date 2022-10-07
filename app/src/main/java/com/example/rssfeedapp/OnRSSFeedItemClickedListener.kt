package com.example.rssfeedapp

import com.example.rssfeedapp.model.Item

interface OnRSSFeedItemClickedListener {
    fun onRSSFeedItemClicked(item: Item)
}
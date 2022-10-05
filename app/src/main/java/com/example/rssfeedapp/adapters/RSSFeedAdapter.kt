package com.example.rssfeedapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.R
import com.example.rssfeedapp.model.RSSFeed

class RSSFeedAdapter : RecyclerView.Adapter<RSSFeedViewHolder>() {
    private val rssFeeds: MutableList<RSSFeed> = mutableListOf()

    init {
        refreshData(rssFeeds)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSFeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feed, parent, false)
        return RSSFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RSSFeedViewHolder, position: Int) {
        val feed = rssFeeds[position]
        holder.bind(feed)
    }

    override fun getItemCount(): Int = rssFeeds.size

    fun refreshData(feeds: List<RSSFeed>) {
        this.rssFeeds.clear()
        this.rssFeeds.addAll(feeds)
        this.notifyDataSetChanged()
    }
}
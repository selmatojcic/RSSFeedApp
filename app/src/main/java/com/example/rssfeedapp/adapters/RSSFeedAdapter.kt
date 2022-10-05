package com.example.rssfeedapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.R
import com.example.rssfeedapp.model.RSSFeed

class RSSFeedAdapter(feeds : List<RSSFeed>) : RecyclerView.Adapter<RSSFeedViewHolder>() {
    private val feeds: MutableList<RSSFeed> = mutableListOf()
    init{
        refreshData(feeds)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSFeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feed, parent, false)
        return RSSFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RSSFeedViewHolder, position: Int) {
        val feed = feeds[position]
        holder.bind(feed)
    }

    override fun getItemCount(): Int = feeds.size

    private fun refreshData(feeds: List<RSSFeed>) {
        this.feeds.clear()
        this.feeds.addAll(feeds)
        this.notifyDataSetChanged()
    }
}
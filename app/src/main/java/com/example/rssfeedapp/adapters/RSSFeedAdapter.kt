package com.example.rssfeedapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.OnRSSFeedClickedListener
import com.example.rssfeedapp.R
import com.example.rssfeedapp.model.RSSFeed

class RSSFeedAdapter(
    private val onRSSFeedClickedListener: OnRSSFeedClickedListener
) : RecyclerView.Adapter<RSSFeedViewHolder>() {

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
        val rssFeed = rssFeeds[position]
        holder.bind(rssFeed)

        holder.itemView.setOnClickListener {
            onRSSFeedClickedListener.onRSSFeedClicked(rssFeed)
        }
    }

    override fun getItemCount(): Int = rssFeeds.size

    fun refreshData(feeds: List<RSSFeed>) {
        this.rssFeeds.clear()
        this.rssFeeds.addAll(feeds)
        this.notifyDataSetChanged()
    }
}
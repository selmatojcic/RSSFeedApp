package com.example.rssfeedapp.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.R
import com.example.rssfeedapp.listeners.OnRSSFeedClickedListener
import com.example.rssfeedapp.model.RSSFeed
import com.example.rssfeedapp.viewmodel.RSSFeedViewModel
import kotlinx.android.synthetic.main.item_feed.view.*

class RSSFeedAdapter(
    private val onRSSFeedClickedListener: OnRSSFeedClickedListener,
    private val context: Context,
    private val rssFeedViewModel: RSSFeedViewModel
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

        holder.itemView.deleteButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete " + rssFeed.title + "?")
                .setPositiveButton("Yes") { dialog, _ ->
                    rssFeeds.removeAt(position)
                    rssFeedViewModel.deleteRSSFeed(rssFeed)
                    notifyItemRemoved(position)
                    Toast.makeText(context, "Deleted " + rssFeed.title, Toast.LENGTH_SHORT)
                        .show()
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

    }

    override fun getItemCount(): Int = rssFeeds.size

    fun refreshData(feeds: List<RSSFeed>) {
        this.rssFeeds.clear()
        this.rssFeeds.addAll(feeds)
        this.notifyDataSetChanged()
    }
}
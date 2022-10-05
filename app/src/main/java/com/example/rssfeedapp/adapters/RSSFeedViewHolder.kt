package com.example.rssfeedapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rssfeedapp.R
import com.example.rssfeedapp.databinding.ItemFeedBinding
import com.example.rssfeedapp.model.RSSFeed

class RSSFeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(rssFeed: RSSFeed) {
        val itemFeedBinding = ItemFeedBinding.bind(itemView)

        itemFeedBinding.feedTitleTextView.text = rssFeed.title
        itemFeedBinding.feedDescriptionTextView.text = rssFeed.description

        Glide.with(itemView)
            .load(rssFeed.imageURL)
            .placeholder(R.drawable.item_background)
            .override(600, 600)
            .fitCenter()
            .into(itemFeedBinding.feedItemImageView)
    }
}
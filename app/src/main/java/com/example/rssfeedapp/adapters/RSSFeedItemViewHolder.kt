package com.example.rssfeedapp.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.databinding.ItemFeedItemBinding
import com.example.rssfeedapp.model.Item

class RSSFeedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Item) {
        val itemFeedItemBinding = ItemFeedItemBinding.bind(itemView)

        itemFeedItemBinding.itemTitleTextView.text = item.title
        itemFeedItemBinding.itemDescriptionTextView.text = item.description
    }
}
package com.example.rssfeedapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.R
import com.example.rssfeedapp.model.Item

class RSSFeedItemAdapter(items: List<Item>) : RecyclerView.Adapter<RSSFeedItemViewHolder>() {

    private val items: MutableList<Item> = mutableListOf()

    init {
        refreshData(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSSFeedItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_feed_item, parent, false)
        return RSSFeedItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RSSFeedItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun refreshData(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        this.notifyDataSetChanged()
    }
}
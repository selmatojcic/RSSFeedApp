package com.example.rssfeedapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssfeedapp.adapters.RSSFeedAdapter
import com.example.rssfeedapp.data.RSSFeedDatabaseBuilder
import com.example.rssfeedapp.data.RSSFeedRepository
import com.example.rssfeedapp.databinding.FragmentRssFeedsBinding

class RSSFeedsFragment: Fragment()  {
    private lateinit var fragmentRssFeedsBinding: FragmentRssFeedsBinding
    private val rssFeedDao = RSSFeedDatabaseBuilder.getInstance().rssFeedDao()
    private val rssFeedRepository = RSSFeedRepository(rssFeedDao)

    companion object {
        const val TAG = "RSS FEEDS"
        fun create(): RSSFeedsFragment {
            return RSSFeedsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentRssFeedsBinding = FragmentRssFeedsBinding.inflate(
            inflater,
            container,
            false
        )
        setupRecyclerView()

        return fragmentRssFeedsBinding.root
    }

    private fun setupRecyclerView() {
        fragmentRssFeedsBinding.feedsRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        fragmentRssFeedsBinding.feedsRecyclerView.adapter =
            RSSFeedAdapter(RSSFeedRepository(rssFeedDao).getFeeds())
    }
}